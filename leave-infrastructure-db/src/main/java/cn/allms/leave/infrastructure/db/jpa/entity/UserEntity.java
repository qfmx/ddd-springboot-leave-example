package cn.allms.leave.infrastructure.db.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "t_user")
public class UserEntity {

    @Id
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

}

