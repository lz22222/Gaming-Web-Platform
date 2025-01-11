package cn.cie.utils;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * Mail Tools
 */
public class MailUtil {

    private final static String USERNAME = "13522599381@163.com";
    private final static String PASSWORD = "VXWCOJZYMQQNDCBO";

    /**
     * Send an email to a specified person, subject and content are required
     * @param user
     * @param title
     * @param content
     */
    public static void sendMail(String user, String title, String content) {
        SimpleEmail email = new SimpleEmail();
        email.setCharset("UTF8");
        email.setHostName("smtp.163.com");
        email.setAuthentication(USERNAME, PASSWORD);
        try {
            email.setFrom(USERNAME);
            email.addTo(user);
            email.setSubject(title);
            email.setMsg(content);
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    /**
     * send email to users
     * @param user
     * @param code
     */
    public static void sendValidateMail(String user, String code) {

        String title = "WePlay Registration verification code";
        String content = "Thank you for registering WePlay, your verification code is \n" + code + "\n，Please save this verification code. It is valid for 10 minutes and can only be used once.。";

        sendMail(user, title, content);
    }

    /**
     * Send verification email to users when they forget their password
     * @param user
     * @param code
     */
    public static void sendFetchPwdMail(String user, String code) {

        String title = "WePlay Reset Password";
        String content = "This email is to verify your WePlay email address and is used to retrieve your password. If this is not your operation, please ignore this email.\nYour verification code is \n" + code + "\nPlease save it carefully. This verification code is valid for 10 minutes and can only be used once.";

        sendMail(user, title, content);
    }

}
