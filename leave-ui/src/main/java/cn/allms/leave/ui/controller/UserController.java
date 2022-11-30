package cn.allms.leave.ui.controller;

import cn.allms.leave.application.command.UserCmd;
import cn.allms.leave.application.executor.UserExecutor;
import cn.allms.leave.domain.exception.ParamVerifyException;
import cn.allms.leave.ui.dto.UserDTO;
import cn.allms.leave.infrastructure.db.jpa.entity.UserEntity;
import cn.allms.leave.infrastructure.db.jpa.repository.UserEntityRepository;
import cn.allms.leave.ui.service.UserService;
import com.codingapi.springboot.framework.dto.response.MultiResponse;
import com.codingapi.springboot.framework.dto.response.SingleResponse;
import com.codingapi.springboot.framework.exception.LocaleMessageException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/open/user")
@AllArgsConstructor
public class UserController {
    private final UserExecutor userExecutor;
    private final UserEntityRepository userEntityRepository;

    private final UserService userService;

    @PostMapping("/create")
    public SingleResponse<Long> createUser(@RequestBody UserCmd.CreateCommand command){
        try {
            return SingleResponse.of(userExecutor.createUser(command));
        } catch (ParamVerifyException e) {
            throw new LocaleMessageException("user.verify.error");
        }
    }

    @GetMapping("/list")
    public MultiResponse<UserEntity> list(UserDTO.ListRequest request){
        return MultiResponse.of(userEntityRepository.findAll(request.toPageRequest()));
    }


    @PostMapping("/login")
    public SingleResponse<String> login(@RequestBody UserCmd.LoginCommand command){
        return userService.login(command);
    }

}
