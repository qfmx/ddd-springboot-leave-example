package cn.allms.leave.ui.service;

import cn.allms.leave.application.command.LeaveCmd;
import cn.allms.leave.application.executor.LeaveOrderExecutor;
import cn.allms.leave.ui.core.jwt.TokenContext;
import cn.allms.leave.domain.exception.LeaveApprovalNotMatchException;
import cn.allms.leave.domain.exception.ParamVerifyException;
import cn.allms.leave.ui.dto.LeaveDTO;
import com.codingapi.springboot.framework.dto.response.Response;
import com.codingapi.springboot.framework.exception.LocaleMessageException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LeaveService {

    private final LeaveOrderExecutor leaveOrderExecutor;

    public Response approval(LeaveDTO.ApprovalRequest request) {
        try {
            LeaveCmd.ApprovalCommand command = new LeaveCmd.ApprovalCommand();
            command.setApproval(request.isApproval());
            command.setContent(request.getContent());
            command.setLeaveId(request.getLeaveId());
            command.setApprovalUsername(TokenContext.getInstance().currentUserName());
            // 审批
            leaveOrderExecutor.approval(command);
        } catch (LeaveApprovalNotMatchException e) {
            throw new LocaleMessageException("leave.approval.error", e);
        } catch (ParamVerifyException e) {
            throw new LocaleMessageException("leave.verify.error", e);
        }
        return Response.buildSuccess();
    }
}
