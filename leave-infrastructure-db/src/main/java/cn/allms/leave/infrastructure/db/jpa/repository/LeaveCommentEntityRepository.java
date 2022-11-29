package cn.allms.leave.infrastructure.db.jpa.repository;

import cn.allms.leave.infrastructure.db.jpa.entity.LeaveCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveCommentEntityRepository extends JpaRepository<LeaveCommentEntity, Integer> {
    /**
     * 查询指定请假单的审批内容
     * @param leaveId 请假单id
     * @return List<LeaveCommentEntity>
     */
    List<LeaveCommentEntity> findByLeaveId(long leaveId);
}
