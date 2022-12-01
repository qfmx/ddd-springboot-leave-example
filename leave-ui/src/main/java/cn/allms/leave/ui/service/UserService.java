package cn.allms.leave.ui.service;

import cn.allms.leave.application.command.UserCmd;
import cn.allms.leave.application.exception.UserNotFoundException;
import cn.allms.leave.application.exception.UserVerifyException;
import cn.allms.leave.application.executor.UserExecutor;
import cn.allms.leave.rpc.UserRpc;
import cn.allms.leave.ui.core.jwt.Jwt;
import cn.allms.leave.ui.core.jwt.Token;
import com.codingapi.springboot.framework.dto.response.SingleResponse;
import com.codingapi.springboot.framework.exception.LocaleMessageException;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;


@DubboService
@AllArgsConstructor
public class UserService implements UserRpc {

    private final Jwt jwt;

    private final UserExecutor userExecutor;

    public SingleResponse<String> login(UserCmd.LoginCommand command) {
        return SingleResponse.of(login0(command.getUsername(), command.getPassword()));
    }

    @Override
    public String login0(String username, String password) {
        try {
            long userId = userExecutor.login(username, password);
            Token token = jwt.create(userId, username);
            return token.getToken();
        } catch (UserNotFoundException | UserVerifyException e) {
            throw new LocaleMessageException("login.error");
        }
    }
}
