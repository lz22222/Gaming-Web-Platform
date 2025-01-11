package cn.cie.controller;

import cn.cie.entity.User;
import cn.cie.services.UserService;
import cn.cie.utils.MsgCenter;
import cn.cie.utils.Result;
import cn.cie.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@Controller
@RequestMapping(value = "user")
public class UserController extends AbstractController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserHolder userHolder;

    @GetMapping(value = "validate")
    public String validate() {
        String referer = getReferer();
        if (userHolder.getUser().getStat().equals(User.STAT_OK)) {
            return "redirect:" + referer;
        }
        return "validate";
    }

    @PostMapping(value = "validate")
    @ResponseBody
    public Result validate(String code) {
        if (userHolder.getUser() == null) {
            return Result.fail(MsgCenter.USER_NOT_LOGIN);
        }
        if (userHolder.getUser().getStat().equals(User.STAT_OK)) {    // The user has already been verified
            return Result.fail(MsgCenter.USER_VALIDATED);
        }
        Result result = userService.validate(userHolder.getUser().getId(), code);
        if (result.isSuccess()) {
            return Result.success("/");
        }
        return result;
    }

    @PostMapping(value = "sendMail")
    @ResponseBody
    public Result sendMail() {
        if (userHolder.getUser() == null) {
            return Result.fail(MsgCenter.USER_NOT_LOGIN);
        }
        return userService.sendMail(userHolder.getUser());
    }

    @GetMapping(value = "personal")
    public String personal() {
        return "personal";
    }

    @PostMapping(value = "personal")
    @ResponseBody
    public Result getPersonInfo() {
        User user = userHolder.getUser();
        if (user == null) {
            return Result.fail(MsgCenter.USER_NOT_LOGIN);
        }
        user.setPassword(null);
        return Result.success(user);
    }

    @GetMapping(value = "update")
    public String update() {
        return "updateUserInfo";
    }

    @PostMapping(value = "update")
    @ResponseBody
    public Result update(User user) {
        return userService.updateUserInfo(user);
    }

    @GetMapping(value = "updatepassword")
    public String updatePassword() {
        return "updatepassword";
    }

    @PostMapping(value = "updatepassword")
    @ResponseBody
    public Result updatePassword(String password) {
        return userService.updatePassword(password);
    }

    @GetMapping(value = "findpassword")
    public String findPassword() {
        return "findpassword";
    }

    @PostMapping(value = "sendfetchpwdmail")
    @ResponseBody
    public Result sendFetchPwdMail(String email) {
        return userService.sendFetchPwdMail(email);
    }

    @PostMapping(value = "findpassword")
    @ResponseBody
    public Result findPassword(String password, String email, String code) {
        return userService.forgetPassword(password, email, code);
    }

    /**
     * Checks if the user is logged in. If logged in, returns the page to redirect to;
     * otherwise, proceeds with the next logic.
     *
     * Before each login attempt, retrieves the referer link (the page the user came from)
     * from the request header. If the referer is null (e.g., the user came from another website),
     * the redirection should go to the homepage.
     *
     * During the login process, this method is called for the first time to determine the
     * redirection link. If the login fails, the referer is saved in the session.
     *
     * If the user came from the login page, it might indicate a login error. In this case,
     * the referer (the page before the user was redirected to the login page) is retrieved
     * from the session.
     *
     * If the session contains a referer, upon successful login, redirect to the referer
     * and then remove the referer from the session.
     *
     * @return the page to redirect to after checking the login status
     */
    private String getReferer() {
        String referer = null;
        String tmp = this.getRequest().getHeader("Referer");
        // If it's null and not redirected from this site, it should redirect to the homepage
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
