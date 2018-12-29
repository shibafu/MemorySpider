package com.tsugaruinfo.authparts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * API用例外ハンドラー
 * 未認証の時エラーを返却するAuthenticationEntryPoint
 * @author pratula
 *
 */
public class APISimpleAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		//401（未認証エラーを返却する
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter()
        .println("{\"msg\":\"API User needs Login. Please Register and Try Again...\"}");

	}

}
