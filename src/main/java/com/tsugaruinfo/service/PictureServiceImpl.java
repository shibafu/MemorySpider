package com.tsugaruinfo.service;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsugaruinfo.dao.MyAlbumDao;
import com.tsugaruinfo.entity.PictureMaster;
import com.tsugaruinfo.repository.PictureMasterRepository;


/**
 * 画像の読み書き用サービス実装
 * @author pratula
 *
 */
@Service
public  class PictureServiceImpl implements PictureService {

	@Autowired
	PictureMasterRepository pRepository;

	@Autowired
	MyAlbumDao albumDao;

	/**
	 * 画像を登録する。
	 */
	@Override
	public Long addPicture(byte[] picturedata, String pictureName, String originalName, Integer uploadUserId, Date uploadDate) {
		// TODO 自動生成されたメソッド・スタブ
		PictureMaster input = new PictureMaster();

		input.setPictureData(picturedata);
		input.setPictureName(pictureName);
		input.setOriginalName(originalName);
		input.setUploadUserId(uploadUserId);
		input.setUploadDate(uploadDate);

		pRepository.saveAndFlush(input);

		return input.getPictureId();
	}

	/**
	 * 画像を登録する(API)
	 */
	@Override
	public Long addPicture(PictureMaster pictureMaster) {
		// TODO 自動生成されたメソッド・スタブ
		pictureMaster.setPictureData(Base64.getDecoder().decode(pictureMaster.getBase64string()));

		pRepository.saveAndFlush(pictureMaster);

		return pictureMaster.getPictureId();
	}

	/**
	 * ユーザー名に基づく登録画像を返す
	 */
	@Override
	public List<PictureMaster> readUserPicture(Integer uploadUserId) {

	List<PictureMaster> searchResult = pRepository.findByUploadUserId(uploadUserId);

	return searchResult;

	}

	/**
	 * 直近9件の登録画像を返却する
	 */
	@Override
	public List<PictureMaster> lastAlbumUserPicture(Integer uploadUserId) {

	List<PictureMaster> searchResult = albumDao.findLastUploadedPicture(uploadUserId);

	return searchResult;

	}

}
