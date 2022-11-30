package cn.allms.leave.ui.core.mvc;

import cn.allms.leave.ui.core.jwt.Jwt;
import cn.allms.leave.ui.core.mvc.interceptor.TokenAuthenticationHandlerInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@AllArgsConstructor
@Component
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    private final Jwt jwt;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 自定义拦截器
        registry.addInterceptor(new TokenAuthenticationHandlerInterceptor(jwt))
                .addPathPatterns("/api/**");
    }
}
