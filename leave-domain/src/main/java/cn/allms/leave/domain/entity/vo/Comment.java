package cn.allms.leave.domain.entity.vo;

import cn.allms.leave.domain.entity.User;
import cn.allms.leave.domain.exception.ParamVerifyException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * 值对象：审核意见
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private String content;
    private Date createTime;
    private User user;
    private boolean flag;

    /**
     * 清理拒绝内容
     */
    public void cleanRejectContent() {
        // 同意时清理拒绝理由
        if (this.flag) {
            this.content = null;
        }
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
