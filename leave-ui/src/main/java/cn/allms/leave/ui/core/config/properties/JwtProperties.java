package cn.allms.leave.ui.core.config.properties;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtProperties {

    /**
     * JWT密钥
     * 需大于32位的字符串
     */
    private String jwtSecretKey = "Q2EDw2xtNINTEu3J5ijluLn2M6HboXpiNbefCKrl";

    /**
     * JWT 有效时间(毫秒)
     * 15分钟有效期 1000*60*15=900000
     */
    private int jwtTime = 900000;

    /**
     * JWT 更换令牌时间(毫秒)
     * 10分钟后更换令牌 1000*60*10=600000
     */
    private int jwtRestTime = 600000;
}
