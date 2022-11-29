package cn.allms.leave.infrastructure.db.jpa.repository;

import cn.allms.leave.infrastructure.db.jpa.entity.LeaveOrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveOrderEntityRepository extends JpaRepository<LeaveOrderEntity, Long> {

    Page<LeaveOrderEntity> findByCreateUserId(long userId, PageRequest pageRequest);


    Page<LeaveOrderEntity> findByApprovalUserIdAndState(long userId,
                                                        int state,
                                                        PageRequest pageRequest);
}