package cn.allms.leave.ui.core.config;

import cn.allms.leave.ui.core.config.properties.JwtProperties;
import cn.allms.leave.ui.core.jwt.Jwt;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtAutoConfig {
    @Bean
    @ConfigurationProperties(prefix = "leave.properties")
    public JwtProperties todoProperties(){
        return new JwtProperties();
    }

    @Bean
    @ConditionalOnMissingBean
    public Jwt jwt(JwtProperties properties){
        return new Jwt(properties.getJwtSecretKey(),properties.getJwtTime(),properties.getJwtRestTime());
    }
}
