package com.tsugaruinfo.config;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * getHeader と addHeaderを追加してサブドメインのヘッダーの操作を加えたサブクラス
 * @author nozawa
 *
 */
public class SubDomainHttpServletRequestWrapper extends HttpServletRequestWrapper {

	//ヘッダー情報はこのマップにコピーされる
	private Map<String, String> headers = new HashMap<String, String>();

	public SubDomainHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);

		//リクエストヘッダーを取得
		Enumeration<String> headers_head = request.getHeaderNames();

		//ヘッダー名を取得
				while(headers_head.hasMoreElements()){
					String header = headers_head.nextElement();
					Enumeration<String> values = request.getHeaders(header);
		//ヘッダー値をセット
				while(values.hasMoreElements()) {
					String value = values.nextElement();
					headers.put(header, value);
					}
				}
	}


	/**
	 * Header追加
	 * @param name
	 * @param value
	 */
	public void addHeader(String name, String value){
		headers.put(name, value);
	}

	/**
	 * Header取得
	 */
	public String getHeader(String name) {
		if(headers.containsKey(name)) {
			return headers.get(name);
		}

		return ((HttpServletRequest)getRequest()).getHeader(name);

	}
}
