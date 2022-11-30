package cn.allms.leave.domain.entity;

import cn.allms.leave.domain.event.UserNameChangeEvent;
import cn.allms.leave.domain.exception.ParamVerifyException;
import com.codingapi.springboot.generator.IdGenerate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User implements IdGenerate {
    private Long id;
    private String username;
    private String password;

    public User(Long id){
        this.id = id;
    }

    public User(String username, String password) {
        // test时自己把id换成时间戳，generateLongId只能在spring bean环境下使用
        this.id = generateLongId();
        this.username = username;
        this.password = password;
    }

    public void changeName(String username) {
        // 旧名称
        String oldName = this.username;
        // 新名称
        this.username = username;
        // 发送事件
        new UserNameChangeEvent(oldName, username);
    }

    public void verify() throws ParamVerifyException {
        if (!StringUtils.hasLength(username)) {
            throw new ParamVerifyException("username mast not null.");
        }

        if (!StringUtils.hasLength(password)) {
            throw new ParamVerifyException("password mast not null.");
        }
    }

    public boolean matchPwd(String pwd) {
        return password.equals(pwd);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User target = (User) obj;
            return target.getId().equals(id);
        }
        return super.equals(obj);
    }


}
