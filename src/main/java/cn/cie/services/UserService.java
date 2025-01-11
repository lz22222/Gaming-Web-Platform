package cn.cie.services;

import cn.cie.entity.User;
import cn.cie.utils.Result;

public interface UserService {

    /**
     * Register a new user
     * @param user User entity
     * @return Result of the registration
     */
    Result register(User user);

    /**
     * Send a verification code to the user's registered email
     * @param user User entity
     * @return Result of the operation
     */
    Result sendMail(User user);

    /**
     * Email verification
     * @param uid User ID
     * @param code Verification code
     * @return Result of the verification
     */
    Result validate(Integer uid, String code);

    /**
     * Login
     * If the "Remember Me" option is selected during login, the token will be valid for 7 days; otherwise, it will be valid for 1 day.
     * On successful login, the token is stored in the database and cached with an expiration time of 1 day.
     * During each request, the interceptor checks the cache first; if not found, it retrieves the token from the database.
     * @param username Username
     * @param password Password
     * @param remember Whether to remember the login (token lifespan is 7 days)
     * @param ip IP address of the user
     * @param device Device information
     * @return Result of the login operation
     */
    Result login(String username, String password, boolean remember, String ip, String device);

    /**
     * Logout
     * @param token User token
     * @return Result of the logout operation
     */
    Result logout(String token);

    /**
     * Update user information
     * @param user User entity with updated information
     * @return Result of the update
     */
    Result updateUserInfo(User user);

    /**
     * Update password
     * @param password New password
     * @return Result of the update
     */
    Result updatePassword(String password);

    /**
     * Reset password
     * @param password New password
     * @param email User's email address
     * @param code Verification code
     * @return Result of the reset operation
     */
    Result forgetPassword(String password, String email, String code);

    /**
     * Send a verification code to the email for resetting the password
     * @param email User's email address
     * @return Result of the operation
     */
    Result sendFetchPwdMail(String email);

    /**
     * Delete users who have not been verified
     */
    void delNotValidateUser();

    /**
     * Delete expired tokens
     */
    void expireToken();
}
