package cn.allms.leave.domain.entity;

import cn.allms.leave.domain.entity.vo.Comment;
import cn.allms.leave.domain.event.LeaveApprovalEvent;
import cn.allms.leave.domain.event.LeaveCreateEvent;
import cn.allms.leave.domain.exception.LeaveApprovalNotMatchException;
import cn.allms.leave.domain.exception.ParamVerifyException;
import com.codingapi.springboot.framework.event.EventPusher;
import com.codingapi.springboot.generator.IdGenerate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 请假单
 */
@Getter
@AllArgsConstructor
public class LeaveOrder implements IdGenerate {

    private Long id;
    private String content;
    private Date createTime;
    private User createUser;
    private User approvalUser;
    private State state;
    private List<Comment> comments;

    /**
     * 提交请假单
     *
     * @param content      请假内容
     * @param createUser   请假人
     * @param approvalUser 审核人
     */
    public LeaveOrder(String content, User createUser, User approvalUser) {
        // test时自己把id换成时间戳，generateLongId只能在spring bean环境下使用
        this.id = generateLongId();
        this.comments = new ArrayList<>();
        this.approvalUser = approvalUser;
        this.createUser = createUser;
        this.createTime = new Date();
        this.state = State.CREATE;
        this.content = content;
    }


    /**
     * 请假单状态枚举
     */
    public enum State {
        /**
         * 创建
         */
        CREATE(0),
        /**
         * 审核
         */
        APPROVAL(1),
        /**
         * 完成
         */
        FINISH(2);

        private int code;

        State(int code) {
            this.code = code;
        }

        public static State parse(int code) {
            for (State state : values()) {
                if (state.getCode() == code) {
                    return state;
                }
            }
            return null;
        }

        public int getCode() {
            return code;
        }
    }

    /**
     * 请假单参数检验
     *
     * @throws ParamVerifyException 参数异常
     */
    public void verify() throws ParamVerifyException {
        if (!StringUtils.hasText(content)) {
            throw new ParamVerifyException("content must not null.");
        }

        if (createUser == null) {
            throw new ParamVerifyException("createUser must not null.");
        }

        if (approvalUser == null) {
            throw new ParamVerifyException("approvalUser must not null.");
        }

        if (state == State.CREATE && comments.size() > 0) {
            throw new ParamVerifyException("create state comments must was null.");
        }

        if (state != State.CREATE && comments.size() == 0) {
            throw new ParamVerifyException("approval,finish state comments must not null.");
        }
        // 审批人不能是自己
        if(createUser.getUsername().equalsIgnoreCase(approvalUser.getUsername())){
            throw new ParamVerifyException("approvalUser must not is self.");
        }
    }

    /**
     * 审批
     *
     * @param comment 审批内容
     * @throws LeaveApprovalNotMatchException 请假单审批不匹配异常
     * @throws ParamVerifyException 参数异常
     */
    public void approval(Comment comment) throws LeaveApprovalNotMatchException, ParamVerifyException {
        if (comment == null) {
            throw new ParamVerifyException("comment must not null.");
        }

        if (!comment.getUser().equals(approvalUser)) {
            throw new ParamVerifyException("approval user must was setting user.");
        }

        if (state != State.CREATE) {
            throw new LeaveApprovalNotMatchException("approval state error.");
        }

        comment.verify();
        this.comments.add(comment);
        state = State.APPROVAL;

        // push LeaveApprovalEvent
        EventPusher.push(new LeaveApprovalEvent(id, content, createUser.getId(),
                createTime, comment.isFlag(), comment.getContent()));
    }

    public void createFinish() {
        // push LeaveCreateEvent
        EventPusher.push(new LeaveCreateEvent(id, content, createUser.getId(), createTime));
    }
}
