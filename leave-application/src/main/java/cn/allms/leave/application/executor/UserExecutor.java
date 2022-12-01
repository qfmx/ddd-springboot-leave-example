package cn.allms.leave.application.executor;

import cn.allms.leave.application.command.UserCmd;
import cn.allms.leave.application.exception.UserNotFoundException;
import cn.allms.leave.application.exception.UserVerifyException;
import cn.allms.leave.domain.entity.User;
import cn.allms.leave.domain.exception.ParamVerifyException;
import cn.allms.leave.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UserExecutor {

    private final UserRepository userRepository;

    /**
     * 用户创建
     *
     * @param command 创建参数
     * @return 用户ID
     * @throws ParamVerifyException 参数验证异常
     */
    public long createUser(UserCmd.CreateCommand command) throws ParamVerifyException {
        User user = new User(command.getUsername(), command.getPassword());
        user.verify();

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new ParamVerifyException("username had exist.");
        }
        // 创建成功返回用户id
        return user.getId();
    }

    /**
     * 用户login
     *
     * @return 用户ID
     * @throws UserNotFoundException 用户不存在异常
     * @throws UserVerifyException   用户检验异常
     */
    public long login(String username, String password) throws UserNotFoundException, UserVerifyException {
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            throw new UserNotFoundException(username);
        }
        if (user.matchPwd(password)) {
            // 密码匹配，返回用户id，用于生成token
            return user.getId();
        }
        throw new UserVerifyException(username);
    }
}