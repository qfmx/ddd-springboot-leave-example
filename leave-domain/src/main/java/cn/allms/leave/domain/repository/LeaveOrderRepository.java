package cn.allms.leave.domain.repository;

import cn.allms.leave.domain.entity.LeaveOrder;

public interface LeaveOrderRepository {
    /**
     * 保存请假单
     *
     * @param leaveOrder 请假单
     */
    void save(LeaveOrder leaveOrder);

    /**
     * 获取请假单详情
     *
     * @param leaveId 请假单id
     * @return LeaveOrder
     */
    LeaveOrder getById(long leaveId);
}
