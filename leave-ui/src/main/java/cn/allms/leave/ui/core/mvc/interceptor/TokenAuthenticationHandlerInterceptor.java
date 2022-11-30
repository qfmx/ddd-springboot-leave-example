package cn.allms.leave.ui.core.mvc.interceptor;

import cn.allms.leave.ui.core.jwt.Jwt;
import cn.allms.leave.ui.core.jwt.Token;
import cn.allms.leave.ui.core.jwt.TokenContext;
import com.alibaba.fastjson.JSONObject;
import com.codingapi.springboot.framework.dto.response.Response;
import com.codingapi.springboot.framework.exception.LocaleMessageException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * token认证拦截器
 */

@AllArgsConstructor
@Slf4j
@Component
public class TokenAuthenticationHandlerInterceptor implements HandlerInterceptor {

    private final static String TOKEN_KEY = "Authorization";

    private final Jwt jwt;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        TokenContext.getInstance().clear();
        String sign = request.getHeader(TOKEN_KEY);

        if (!StringUtils.hasLength(sign)) {
            writeResponse(response, Response.buildFailure("token.null", "token was null"));
            return false;
        }
        Token token = jwt.parser(sign);
        if (token.canRestToken()) {
            Token newSign = jwt.create(token.getUserId(), token.getUsername());
            log.info("reset token ");
            response.setHeader(TOKEN_KEY, newSign.getToken());
        }
        try {
            token.verify();
        } catch (LocaleMessageException e) {
            writeResponse(response, Response.buildFailure(e.getErrCode(), e.getErrMessage()));
            return false;
        }
        TokenContext.getInstance().push(token);
        return true;
    }

    private void writeResponse(HttpServletResponse servletResponse, Response returnResponse) throws IOException {
        String content = JSONObject.toJSONString(returnResponse);
        IOUtils.write(content, servletResponse.getOutputStream(), StandardCharsets.UTF_8);
    }


}
