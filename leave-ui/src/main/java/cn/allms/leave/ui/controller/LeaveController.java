package cn.allms.leave.ui.controller;

import cn.allms.leave.application.command.LeaveCmd;
import cn.allms.leave.application.executor.LeaveOrderExecutor;
import cn.allms.leave.ui.core.jwt.TokenContext;
import cn.allms.leave.domain.entity.LeaveOrder;
import cn.allms.leave.domain.exception.ParamVerifyException;
import cn.allms.leave.domain.repository.LeaveOrderRepository;
import cn.allms.leave.ui.dto.LeaveDTO;
import cn.allms.leave.infrastructure.db.jpa.entity.LeaveOrderEntity;
import cn.allms.leave.infrastructure.db.jpa.repository.LeaveOrderEntityRepository;
import cn.allms.leave.ui.service.LeaveService;
import com.codingapi.springboot.framework.dto.request.PageRequest;
import com.codingapi.springboot.framework.dto.response.MultiResponse;
import com.codingapi.springboot.framework.dto.response.Response;
import com.codingapi.springboot.framework.dto.response.SingleResponse;
import com.codingapi.springboot.framework.exception.LocaleMessageException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leave")
@AllArgsConstructor
public class LeaveController {
    private final LeaveOrderExecutor leaveOrderExecutor;

    private final LeaveOrderEntityRepository leaveOrderEntityRepository;

    private final LeaveService leaveService;
    private final LeaveOrderRepository leaveOrderRepository;

    @PostMapping("/createLeave")
    public Response createLeave(@RequestBody LeaveDTO.CreateRequest request){
        try {
            LeaveCmd.CreateCommand command = new LeaveCmd.CreateCommand();

            command.setCreateUsername(TokenContext.getInstance().currentUserName());
            command.setContent(request.getContent());
            command.setApprovalUsername(request.getApproval());

            leaveOrderExecutor.createLeave(command);
        } catch (ParamVerifyException e) {
            throw new LocaleMessageException("leave.verify.error",e);
        }
        return Response.buildSuccess();
    }


    @GetMapping("/myLeave")
    public MultiResponse<LeaveOrderEntity> myLeave(PageRequest request){
        long userId = TokenContext.getInstance().currentUserId();
        return MultiResponse.of(leaveOrderEntityRepository.findByCreateUserId(userId,request));
    }

    @GetMapping("/todoLeave")
    public MultiResponse<LeaveOrderEntity> todoLeave(PageRequest request){
        long userId = TokenContext.getInstance().currentUserId();
        return MultiResponse.of(leaveOrderEntityRepository
                .findByApprovalUserIdAndState(userId, LeaveOrder.State.CREATE.getCode(), request));
    }


    @PostMapping("/approval")
    public Response approval(@RequestBody LeaveDTO.ApprovalRequest request){
        return leaveService.approval(request);
    }

    @GetMapping("/detail")
    public SingleResponse<LeaveOrder> detail(@RequestParam("id") long id){
        return SingleResponse.of(leaveOrderRepository.getById(id));
    }

}