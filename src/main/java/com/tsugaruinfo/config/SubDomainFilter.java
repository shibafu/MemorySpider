package com.tsugaruinfo.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

/**
 * サブドメインフィルター
 * http://api.xxxx/
 * で始まるアクセスにヘッダーを付ける
 * @author pratula
 *
 */
public class SubDomainFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		// TODO 自動生成されたメソッド・スタブ

		//サブドメインラッパーを実体化
		SubDomainHttpServletRequestWrapper httpRequest =
				new SubDomainHttpServletRequestWrapper((HttpServletRequest) request);

		//サブドメインを取得
		String subDomain = getSubDomain(httpRequest);

		//apiサブドメインの取得に成功したらフィルターを実行して終了
	if(subDomain != null) {
			httpRequest.addHeader("X-SUBDOMAIN", subDomain);
		}
	else {
		//サブドメインのアクセスではない時、他のヘッダー
		httpRequest.addHeader("X-SUBDOMAIN", "topDomain");
	}

	filterChain.doFilter(httpRequest, response);
	}

	/**
	 * ヘッダー判定部 apiとしてのアクセスかを判定する
	 * @param httpRequest
	 * @return
	 */
	private String getSubDomain(HttpServletRequest httpRequest) {
		//サブドメインが「api.localhostで始まるときapi文字列を返す
				if(httpRequest.getHeader("host").matches("api.*")) {
					return "api";
				}

				return null;
	}
}
