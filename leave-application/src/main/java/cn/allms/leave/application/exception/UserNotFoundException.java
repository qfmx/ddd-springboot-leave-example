package cn.allms.leave.application.exception;

/**
 * 用户未找到异常
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String username) {
        super(String.format("user:%s not found", username));
    }
}
