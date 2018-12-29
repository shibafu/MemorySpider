package com.tsugaruinfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsugaruinfo.entity.UserMaster;
import com.tsugaruinfo.repository.UserMasterRepository;


/**
 * ユーザー情報をアクセスするクラスの実装
 * @author pratula
 *
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMasterRepository userMasterRepositry;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	//既登録しているユーザーか、判定結果
	private final static int REGISTERD_USER_FAILED = 1;
	private final static int REGISTERD_USER_SUCSEED = 0;

	/*
	 * ユーザーを登録する
	 * (非 Javadoc)
	 * @see com.TsugaruInfo.service.UserService#register(com.TsugaruInfo.entity.UserMaster)
	 */
	@Override
	@Transactional
	public void register(UserMaster user) {
			//パスワードをハッシュ化
			user.setPassword(passwordEncoder.encode(user.getPassword()));

			userMasterRepositry.saveAndFlush(user);
	}

	/*
	 * ユーザー名をあいまい検索し、ユーザー情報一覧を返す
	 * (非 Javadoc)
	 * @see com.TsugaruInfo.service.UserService#refferenceByName(java.lang.String)
	 */
	@Override
	public List<UserMaster> refferenceByName(String username) {
		// TODO 自動生成されたメソッド・スタブ
		return userMasterRepositry.findByUsernameLike(username);
	}

	/*
	 * ユーザー名完全一致検索
	 * (非 Javadoc)
	 * @see com.TsugaruInfo.service.UserService#refferenceByName(java.lang.String)
	 */
	@Override
	public UserMaster searchByUsername(String username) {
		// TODO 自動生成されたメソッド・スタブ
		return userMasterRepositry.findByUsername(username);
	}

	/*
	 * E-メールを完全一意検索
	 * (非 Javadoc)
	 * @see com.TsugaruInfo.service.UserService#refferenceByName(java.lang.String)
	 */
	@Override
	public UserMaster searchByEmail(String email) {
		// TODO 自動生成されたメソッド・スタブ
		return userMasterRepositry.findByEmail(email);
	}



	/*
	 * 既に登録されているユーザー化チェックする
	 * (非 Javadoc)
	 * @see com.TsugaruInfo.service.UserService#judgeRegisterdUser(com.TsugaruInfo.entity.UserMaster)
	 */
	@Override
	public int judgeRegisterdUser(UserMaster userMaster) {
		// TODO 自動生成されたメソッド・スタブ
		if(userMasterRepositry.findByUsername(userMaster.getUsername()) != null){
			return REGISTERD_USER_FAILED;
		}else if(userMasterRepositry.findByEmail(userMaster.getEmail()) != null) {
			return REGISTERD_USER_FAILED;
		}
		return REGISTERD_USER_SUCSEED;
	}

	@Override
	public String dummyPassword(String rawPassword) {
		// TODO 自動生成されたメソッド・スタブ
		StringBuilder sb = new StringBuilder();

		for(int i = 0; i <= rawPassword.length(); i++) {
			sb.append("*");
		}

		return sb.toString();
	}
}
