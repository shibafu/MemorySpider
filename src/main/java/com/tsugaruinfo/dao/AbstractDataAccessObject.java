package com.tsugaruinfo.dao;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * Dao用の抽象クラス
 * @author pratula
 *
 * @param <T>
 */
public class AbstractDataAccessObject<T> {

	/**
	 * 初期化メソッド
	 * Autowiringを実行可能にする。
	 */
	public void init() {
		SpringBeanAutowiringSupport
			.processInjectionBasedOnCurrentContext(this);
	}
}
