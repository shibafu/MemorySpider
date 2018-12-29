package com.tsugaruinfo.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tsugaruinfo.entity.UserMaster;

/**
 * フォーム認証用LoginUserDetails実装
 * ユーザーネームとEmailで名前を検索する。
 * 見つかった場合、加えてユーザー情報を返却する
 * @author pratula
 *
 */
@Primary
@Service
public class LoginUserDetailsServiceImpl implements LoginUserDetailService {

	@Autowired
	UserService uService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO 自動生成されたメソッド・スタブ

		//Eメールからユーザー検索
		UserMaster user = Optional.ofNullable(uService.searchByEmail(username))
				.orElseGet(() -> null);


		//ユーザー名からユーザー検索
		if(user == null) {
		user = Optional.ofNullable(uService.searchByUsername(username))
				.orElseThrow(() -> new UsernameNotFoundException("user not found. "));
		}

		//ユーザー情報をSpringSecurityに渡す。
		return new LoginUserDetails(user, getAuthorities(user));
	}

		private Collection<GrantedAuthority> getAuthorities(UserMaster user) {
			if(user.getRole().equals("ROLE_ADMIN")) {
				return AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
			} else {
				return AuthorityUtils.createAuthorityList("ROLE_USER");
			}
		}

	}
