package cn.allms.leave.application.command;

import lombok.Getter;
import lombok.Setter;

/**
 * 接收前端输入命令
 */
public class LeaveCmd {
    /**
     * 创建请假单
     */
    @Setter
    @Getter
    public static class CreateCommand {
        private String approvalUsername;
        private String createUsername;
        private String content;
    }


    /**
     * 审批请假单
     */
    @Setter
    @Getter
    public static class ApprovalCommand {
        /**
         * 请假单ID
         */
        private long leaveId;
        /**
         * 拒绝理由
         */
        private String content;
        /**
         * true:同意，false：拒绝
         */
        private boolean approval;
        private String approvalUsername;
    }
}
