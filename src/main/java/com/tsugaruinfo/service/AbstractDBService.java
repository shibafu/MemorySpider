package com.tsugaruinfo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * DB用のサービス。
 * あんまいらない・・・？
 * @author pratula
 *
 */
@Service
public abstract class AbstractDBService {

	public AbstractDBService() {
		SpringBeanAutowiringSupport
			.processInjectionBasedOnCurrentContext(this);
	}
}
