package cn.allms.leave.application.executor;

import cn.allms.leave.application.command.LeaveCmd;
import cn.allms.leave.domain.entity.LeaveOrder;
import cn.allms.leave.domain.entity.User;
import cn.allms.leave.domain.entity.vo.Comment;
import cn.allms.leave.domain.exception.LeaveApprovalNotMatchException;
import cn.allms.leave.domain.exception.ParamVerifyException;
import cn.allms.leave.domain.repository.LeaveOrderRepository;
import cn.allms.leave.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
public class LeaveOrderExecutor {
    private final UserRepository userRepository;

    private final LeaveOrderRepository leaveOrderRepository;


    /**
     * 请假单创建
     *
     * @param command 请假单命令参数
     * @throws ParamVerifyException 参数验证异常
     */
    public void createLeave(LeaveCmd.CreateCommand command) throws ParamVerifyException {
        // 获取请假用户
        User createUser = userRepository.getUserByUsername(command.getCreateUsername());
        // 获取审批用户
        User approvalUser = userRepository.getUserByUsername(command.getApprovalUsername());
        // 创建请假单
        LeaveOrder leaveOrder = new LeaveOrder(command.getContent(), createUser, approvalUser);
        // 请假单检查
        leaveOrder.verify();
        // 请假单创建完成，发送事件
        leaveOrder.createFinish();
        // 入库保存
        leaveOrderRepository.save(leaveOrder);
    }

    /**
     * 审批
     *
     * @param command 审批命令参数
     * @throws LeaveApprovalNotMatchException 审批信息不匹配异常
     * @throws ParamVerifyException           参数验证异常
     */
    public void approval(LeaveCmd.ApprovalCommand command) throws LeaveApprovalNotMatchException, ParamVerifyException {
        User approvalUser = userRepository.getUserByUsername(command.getApprovalUsername());
        LeaveOrder leaveOrder = leaveOrderRepository.getById(command.getLeaveId());
        // 审批创建
        Comment comment = new Comment(command.getContent(), new Date(), approvalUser, command.isApproval());
        comment.cleanRejectContent();
        // 审批
        leaveOrder.approval(comment);
        // 入库
        leaveOrderRepository.save(leaveOrder);
    }
}
