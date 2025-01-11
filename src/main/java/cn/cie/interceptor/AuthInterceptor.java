package cn.cie.interceptor;

import cn.cie.entity.Token;
import cn.cie.entity.User;
import cn.cie.mapper.TokenMapper;
import cn.cie.mapper.UserMapper;
import cn.cie.utils.RedisUtil;
import cn.cie.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Interceptor to retrieve user identity based on the token.
 * First, check the cache; if not found, fetch from the database.
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenMapper tokenMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserHolder userHolder;

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // Pass through if the request is for static resources
        if (httpServletRequest.getRequestURI().matches("^/img/[\\S]+\\.(png|jpg)$") || httpServletRequest.getRequestURI().matches("^/css/[\\S]+\\.css$")
                || httpServletRequest.getRequestURI().matches("^/js/[\\S]+\\.js$")) {
            return true;
        }
        String token = null;
        if (httpServletRequest.getCookies() != null) {
            for (Cookie cookie : httpServletRequest.getCookies()) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                }
            }
        }
        if (token != null) {
            String userid = redisUtil.get(token);
            int uid = 0;
            if (userid == null) {
                Token t = tokenMapper.selectByTokenAndStat(token, Token.STAT_OK);
                Date now = new Date();
                if (t == null || t.getExpiredTime().before(now)) {
                    return true;
                }
                uid = t.getUid();
                long oneday = 86400000L;
                long expired = t.getExpiredTime().getTime() - now.getTime();
                if (expired > oneday) {
                    redisUtil.putEx(token, String.valueOf(uid), 60 * 60 * 24);
                } else {
                    redisUtil.putEx(token, String.valueOf(uid), (int) (expired / 1000));
                }
            } else {
                uid = Integer.valueOf(userid);
            }
            // If the token is valid, temporarily store the current user, and it can be accessed later via dependency-injected UserHolder
            User user = userMapper.selectById(uid);
            if (user == null) {
                return true;
            }
            userHolder.setUser(user);
// Skip POST methods
            if (httpServletRequest.getMethod().equals("POST")) {
                return true;
            }
            // If the user has logged in but has not verified their email, redirect to the email verification page (prevent infinite redirects if the requested URL is the verification page)
            if (user.getStat().equals(User.STAT_NOT_VALIDATE) && !httpServletRequest.getRequestURI().equals("/user/validate")) {
                httpServletResponse.sendRedirect("/user/validate");
                return false;
            } else if (user.getStat().equals(User.STAT_DEL)) {
                userHolder.remove();
            }
        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if (modelAndView == null) {
            return;
        }
        if (httpServletRequest.getMethod().equals("GET") && userHolder.getUser() != null) {
            modelAndView.getModel().put("user", userHolder.getUser().getNickname());
        }
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        userHolder.remove();
    }
}
