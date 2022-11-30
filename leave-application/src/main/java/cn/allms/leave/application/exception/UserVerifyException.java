package cn.allms.leave.application.exception;

/**
 * 用户校验异常
 */
public class UserVerifyException extends Exception {
    public UserVerifyException(String username) {
        super(String.format("username:%s verify err", username));
    }
}
