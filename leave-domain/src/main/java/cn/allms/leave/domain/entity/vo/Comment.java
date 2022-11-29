package cn.allms.leave.domain.entity.vo;

import cn.allms.leave.domain.entity.User;
import cn.allms.leave.domain.exception.ParamVerifyException;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * 值对象：审核意见
 */
@Getter
public class Comment {

    private String content;
    private Date createTime;
    private User user;
    private boolean flag;

    public Comment(User user) {
        this.user = user;
        this.flag = true;
        this.createTime = new Date();
    }

    public Comment(String content, User user) {
        this.content = content;
        this.user = user;
        this.flag = false;
        this.createTime = new Date();
    }

    public void verify() throws ParamVerifyException {
        if (!flag && !StringUtils.hasText(content)) {
            throw new ParamVerifyException("not pass content must not null.");
        }

        if (user == null) {
            throw new ParamVerifyException("user must not null.");
        }
    }

}
