package cn.allms.leave.domain.entity;

import cn.allms.leave.domain.entity.vo.Comment;
import cn.allms.leave.domain.exception.ParamVerifyException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class LeaveOrderTest {

    @Tag("请假参数检测测试")
    @Test
    void verify() {
        String content = "老大，明天请假一天";
        User createUser = new User(1L, "小唐", "123456");
        User approvalUser = new User(2L, "神仙", "123456");

        LeaveOrder leaveOrder = new LeaveOrder(content, createUser, approvalUser);
        assertDoesNotThrow(leaveOrder::verify, "请假参数校验失败");
        // 完成请假
        leaveOrder.createFinish();
    }

    @Tag("请假通过审批测试")
    @Test
    void approvalPass() throws ParamVerifyException {
        String content = "老大，明天请假一天";
        User createUser = new User(1L, "小唐", "123456");
        User approvalUser = new User(2L, "神仙", "123456");
        LeaveOrder leaveOrder = new LeaveOrder(content, createUser, approvalUser);
        leaveOrder.verify();
        // 审批，同意
        Comment comment = new Comment(approvalUser);
        assertDoesNotThrow(() -> leaveOrder.approval(comment), "请假通过审批业务失败。");
    }

    @Tag("请假不通过审批测试")
    @Test
    void approvalNotPass() throws ParamVerifyException {
        String content = "老大，明天请假一天";
        User createUser = new User(1L, "小唐", "123456");
        User approvalUser = new User(2L, "神仙", "123456");
        LeaveOrder leaveOrder = new LeaveOrder(content, createUser, approvalUser);
        leaveOrder.verify();
        // 审批，同意
        Comment comment = new Comment("不行，你明天必须来加班", approvalUser);
        assertDoesNotThrow(() -> leaveOrder.approval(comment), "请假通过审批业务失败。");
    }
}