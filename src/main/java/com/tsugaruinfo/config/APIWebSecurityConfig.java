package com.tsugaruinfo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tsugaruinfo.authparts.APIAccessDeniedHandler;
import com.tsugaruinfo.authparts.APISimpleAuthenticationEntryPoint;
import com.tsugaruinfo.service.LoginUserDetailService;

/**
 * API用セキュリティコンフィグ
 * @author pratula
 *
 */
@EnableWebSecurity
@Configuration
@Order(1)
public class APIWebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	LoginUserDetailService lService;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	/**
	 * Web全般のセキュリティ
	 * リソースを許可
	 */
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/resources/**");
	}

	/**
	 * Http通信のセキュリティ
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// セキュリティで管理されたページの設定
		http.antMatcher("/api/**")
		.exceptionHandling()
		.authenticationEntryPoint(authenticationEntryPoint()) // APIがアクセス拒否された時の例外を返す
		.accessDeniedHandler(accessDeniedHandler());;

		http.authorizeRequests().anyRequest().authenticated(); // 適用された全リクエストに対して認証を要求


		http.httpBasic(); // Basic認証
		http.csrf().disable();
	}

	/**
	 * AuthenticationProviderとかの設定。
	 * UserDetailsService、passwordEncoderをセットする
	 */
	@Autowired
	void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(lService).passwordEncoder(passwordEncoder);
	}

	/**
	 * 未認証エラーを返す。
	 * 詳細は実装を参照
	 * @return
	 */
	private APISimpleAuthenticationEntryPoint authenticationEntryPoint() {
		return new APISimpleAuthenticationEntryPoint();
	}
	/**
	 * 認証済みでアクセス権限が足りないエラーを返す。
	 * 詳細は実装を参照
	 * @return
	 */
	private APIAccessDeniedHandler accessDeniedHandler() {
		return new APIAccessDeniedHandler();
	}
}
