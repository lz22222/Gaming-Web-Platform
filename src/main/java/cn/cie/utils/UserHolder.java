package cn.cie.utils;

import cn.cie.entity.User;
import org.springframework.stereotype.Component;

/**
 * Store requester information on each request
 */
@Component
public class UserHolder {

    private static ThreadLocal<User> users = new ThreadLocal<User>();

    public User getUser() {
        return users.get();
    }

    public void setUser(User user) {
        users.set(user);
    }

    public void remove() {
        users.remove();
    }

}
