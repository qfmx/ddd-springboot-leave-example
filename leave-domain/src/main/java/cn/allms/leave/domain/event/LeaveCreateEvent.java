package cn.allms.leave.domain.event;

import com.codingapi.springboot.framework.event.IEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

/**
 * 请假单创建事件
 */
@Getter
@AllArgsConstructor
public class LeaveCreateEvent implements IEvent {
    /**
     * 请假单ID
     */
    private Long leaveId;
    /**
     * 请假内容
     */
    private String content;
    /**
     * 请假人ID
     */
    private Long createUserId;
    private Date createTime;

}
