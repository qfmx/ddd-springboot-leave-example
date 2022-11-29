package cn.allms.leave.infrastructure.db.jpa.repository;

import cn.allms.leave.infrastructure.db.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity,Long> {

    /**
     * 查询用户信息
     *
     * @param username 用户名
     * @return UserEntity
     */
    UserEntity getByUsername(String username);

}