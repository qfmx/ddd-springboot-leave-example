package cn.allms.leave.domain.entity;


import cn.allms.leave.domain.exception.ParamVerifyException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class UserTest {

    @Tag("更改用户名称测试")
    @Test
    void changeName() {
        User xiaotang = new User("小唐", "123456");
        xiaotang.changeName("神仙");
        assertEquals(xiaotang.getUsername(), "神仙");
    }

    @Tag("参数检测")
    @Test
    void verify() {
        User xiaotang = new User("小唐", "123456");
        assertDoesNotThrow(xiaotang::verify, "参数检验失败");
        User shenxian = new User("神仙", "");
        assertThrows(ParamVerifyException.class, shenxian::verify, "参数检验失败");
        User user = new User("", "x");
        assertThrows(ParamVerifyException.class, user::verify, "参数校验失败");
    }

    @Tag("密码测试，基于csv文件")
    @CsvFileSource(resources = "/user.csv", delimiter = ',', numLinesToSkip = 1)
    @ParameterizedTest
    void matchPwd(long id, String username, String password, String verify, boolean result) {
        log.info("id:{},username:{},password:{}", id, username, password);
        User user = new User(id, username, password);
        assertEquals(user.matchPwd(verify), result, "密码验证失败.");
    }
}