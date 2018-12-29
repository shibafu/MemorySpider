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

import com.tsugaruinfo.service.LoginUserDetailService;


/**
 * フォーム認証用セキュリティコンフィグ
 * @author pratula
 *
 */
@EnableWebSecurity
@Configuration
@Order(2)
public class UIWebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	LoginUserDetailService lService;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	/**
	 * Web全般のセキュリティ
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
		http.authorizeRequests()
				.antMatchers("/adminproc/*")
				.authenticated()
				.and()
				.csrf()
				.disable()

				.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/top")
				.failureUrl("/login?error")
				.permitAll()
				.and()
				.rememberMe() //RemenberMe認証を行う。現状は１週間有効
				.tokenValiditySeconds(604800);

		// ログアウト設定
		http.logout().logoutUrl("auth/logout").permitAll();

	}

	/**
	 * AuthenticationProviderとかの設定。
	 * UserDetailsService、passwordEncoderをセットする
	 */
	@Autowired
	void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(lService).passwordEncoder(passwordEncoder);
	}
}
