package cn.allms.leave.infrastructure.db.repository.impl;

import cn.allms.leave.domain.entity.User;
import cn.allms.leave.domain.repository.UserRepository;
import cn.allms.leave.infrastructure.db.jpa.entity.UserEntity;
import cn.allms.leave.infrastructure.db.jpa.repository.UserEntityRepository;
import com.codingapi.springboot.framework.convert.BeanConvertor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    final UserEntityRepository userEntityRepository;

    @Override
    public void save(User user) {
        UserEntity entity = BeanConvertor.convert(user, UserEntity.class);
        userEntityRepository.save(entity);
    }

    @Override
    public User getUserByUsername(String username) {
        UserEntity entity = userEntityRepository.getByUsername(username);
        if (Objects.isNull(entity)) {
            return null;
        }
        return new User(entity.getId(), entity.getUsername(), entity.getPassword());
    }
}
