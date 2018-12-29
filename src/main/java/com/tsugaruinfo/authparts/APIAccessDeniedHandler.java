package com.tsugaruinfo.authparts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * API用例外ハンドラー
 * 認証済みでセキュリティ権限がない箇所にアクセスした例外を返す
 * @author pratula
 *
 */
public class APIAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		// TODO 自動生成されたメソッド・スタブ

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter()
        .println("{\"msg\":\"Authorization Failed...\"}");
	}

}
