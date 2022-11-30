package cn.allms.leave.ui.service;

import cn.allms.leave.application.command.UserCmd;
import cn.allms.leave.application.exception.UserNotFoundException;
import cn.allms.leave.application.exception.UserVerifyException;
import cn.allms.leave.application.executor.UserExecutor;
import cn.allms.leave.ui.core.jwt.Jwt;
import cn.allms.leave.ui.core.jwt.Token;
import com.codingapi.springboot.framework.dto.response.SingleResponse;
import com.codingapi.springboot.framework.exception.LocaleMessageException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final Jwt jwt;

    private final UserExecutor userExecutor;

    public SingleResponse<String> login(UserCmd.LoginCommand command) {
        try {
            long userId = userExecutor.login(command);
            Token token =  jwt.create(userId,command.getUsername());
            return SingleResponse.of(token.getToken());
        } catch (UserNotFoundException | UserVerifyException e) {
            throw new LocaleMessageException("login.error");
        }
    }
}
