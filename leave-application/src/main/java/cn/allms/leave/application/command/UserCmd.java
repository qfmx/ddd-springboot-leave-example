package cn.allms.leave.application.command;

import lombok.Getter;
import lombok.Setter;

/**
 * 接收前端输入命令
 */
public class UserCmd {

    /**
     * 创建用户
     */
    @Setter
    @Getter
    public static class CreateCommand {
        private String username;
        private String password;
    }


    /**
     * 登录用户
     */
    @Setter
    @Getter
    public static class LoginCommand {
        private String username;
        private String password;
    }

}
