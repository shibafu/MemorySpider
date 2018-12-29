package com.tsugaruinfo.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tsugaruinfo.entity.UserMaster;


/**
 * ログイン認証に必要なクラス
 * @author Work
 *
 */
public class LoginUserDetails implements UserDetails {

	private final UserMaster user;
	private final Collection<GrantedAuthority> authorities;

	public LoginUserDetails(
			UserMaster user, Collection<GrantedAuthority> authorities) {
			this.user = user;
			this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO 自動生成されたメソッド・スタブ
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO 自動生成されたメソッド・スタブ
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO 自動生成されたメソッド・スタブ
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return user.getEnabled();
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO 自動生成されたメソッド・スタブ
		return user.getEnabled();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return user.getEnabled();
	}

	@Override
	public boolean isEnabled() {
		// TODO 自動生成されたメソッド・スタブ
		return user.getEnabled();
	}

	public Integer getUserId(){
		// TODO 自動生成されたメソッド・スタブ
		return user.getUserId();
	}

	public String getRole() {
		// TODO 自動生成されたメソッド・スタブ
		return user.getRole();
	}

	public String getEmail() {
		// TODO 自動生成されたメソッド・スタブ
		return user.getEmail();
	}

}
