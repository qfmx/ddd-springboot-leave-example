package cn.allms.leave.domain.repository;

import cn.allms.leave.domain.entity.User;

public interface UserRepository {

    /**
     * 保存用户
     *
     * @param user 用户
     */
    void save(User user);

    /**
     * 根据用户名称查询用户信息
     *
     * @param username 用户名
     * @return User
     */
    User getUserByUsername(String username);
}
