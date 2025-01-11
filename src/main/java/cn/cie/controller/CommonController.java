package cn.cie.controller;

import cn.cie.entity.User;
import cn.cie.services.GameService;
import cn.cie.services.UserService;
import cn.cie.utils.MsgCenter;
import cn.cie.utils.Result;
import cn.cie.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@Controller
public class CommonController extends AbstractController {

    @Autowired
    private UserHolder userHolder;
    @Autowired
    private UserService userService;
    @Autowired
    private GameService gameService;

    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping(value = "login")
    public String login() {
        String referer = getReferer();
        // If the user is already logged in and their status is normal, then redirect them to the previous page
        if (userHolder.getUser() != null && userHolder.getUser().getStat().equals(User.STAT_OK)) {
            return "redirect:" + referer;
        }
        return "login";
    }

    @PostMapping(value = "login")
    @ResponseBody
    public Result login(String username, String password,
                        @RequestParam(value = "remember", defaultValue = "false", required = false) boolean remember,
                        HttpServletResponse response) {
        String referer = getReferer();
        // If the user is already logged in, then redirect them to the previous page
        if (userHolder.getUser() != null && userHolder.getUser().getStat().equals(User.STAT_OK)) {
            return Result.fail(MsgCenter.OK, referer);
        }
        Result result = userService.login(username, password, remember, this.getRemoteIp(), this.getUserAgent());
        if (result.isSuccess()) {
            Map<String, String> data = new HashMap<String, String>();
            data.put("referer", referer);
            // In the response, add a cookie, which will be included with every subsequent request
            Cookie cookie = new Cookie("token", (String) result.getData());
            cookie.setPath("/");
            if (remember) {
                cookie.setMaxAge(60 * 60 * 24 * 7);
            } else {
                cookie.setMaxAge(60 * 60 * 24);
            }
            response.addCookie(cookie);
            return Result.success(data);
        } else {
            return result;
        }
    }

    @PostMapping(value = "logout")
    @ResponseBody
    public Result logout() {
        String token = null;
        if (this.getRequest().getCookies() != null) {
            for (Cookie cookie : this.getRequest().getCookies()) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                }
            }
        }
        return userService.logout(token);
    }

    @GetMapping(value = "register")
    public String register() {
        String referer = getReferer();
        if (userHolder.getUser() != null) {
            return "redirect:" + referer;
        }
        return "register";
    }

    @PostMapping(value = "register")
    @ResponseBody
    public Result register(User user, HttpServletResponse response) {
        Result result = userService.register(user);
        String pwd = user.getPassword();

        if (result.isSuccess()) {
            login(user.getUsername(), pwd, false, response);
            return Result.success();
        }
        return result;
    }

    /**
     * fetches daily recommendations by randomly selecting 5 games, system that generates this list once per day
     *
     * @return
     */
    @PostMapping(value = "everyday")
    @ResponseBody
    public Result everyday() {
        return gameService.getRandomGames();
    }


    @GetMapping(value = "shoppingcart")
    public String shoppingcart() {
        return "shoppingcart";
    }

    /**
     * The latest 5 games, sorted by release date, stored in the cache
     *
     * @return
     */
    @PostMapping(value = "newestgames")
    @ResponseBody
    public Result newestGames() {
        return gameService.newestGames();
    }

    /**
     * The latest 5 unreleased games, sorted by time, stored in the cache
     *
     * @return
     */
    @PostMapping(value = "preupgames")
    @ResponseBody
    public Result preUpGames() {
        return gameService.preUpGames();
    }

    @PostMapping(value = "freegames")
    @ResponseBody
    public Result getFreeGames() {
        return gameService.getFreeGames();
    }

    @GetMapping(value = "search")
    public String search() {
        return "search";
    }

    @PostMapping(value = "search")
    @ResponseBody
    public Result search(String info) {
        return gameService.search(info);
    }

    /**
     * Checks if the user is logged in. If logged in, returns the page to redirect to;
     * otherwise, proceeds with the next logic.
     *
     * Before every login, the referer link (the page the user came from) is retrieved
     * from the request headers. If the referer is null (e.g., the user came from another
     * website), the redirection should go to the homepage.
     *
     * During the login process, this method is called for the first time to get the
     * redirect link. If the login fails, the referer is stored in the session.
     *
     * If the user came from the login page, it might indicate a login error. The original
     * referer (before the user was redirected to the login page) is stored in the session
     * and retrieved from there.
     *
     * If the session contains a referer, upon successful login, redirect to the referer
     * and then remove the referer from the session.
     *
     * @return the page to redirect to after checking login status
     */
    private String getReferer() {
        String referer = null;
        String tmp = this.getRequest().getHeader("Referer");
        // If null, and the user did not come from this site, the redirection should go to the homepage
        if (tmp == null) {
            referer = "/";
        } else if (tmp.endsWith("/login")) {
            referer = (String) this.getSession().getAttribute("Referer");
        } else {
            referer = tmp;
        }
        this.getSession().setAttribute("Referer", referer);
        return referer;
    }

}
