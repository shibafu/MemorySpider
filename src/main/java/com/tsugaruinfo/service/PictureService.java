package com.tsugaruinfo.service;

import java.util.Date;
import java.util.List;

import com.tsugaruinfo.entity.PictureMaster;


/**
 * 画像の読み書き用サービス
 * @author pratula
 *
 */
public interface PictureService {
	public Long addPicture(PictureMaster pictureMaster);
	public Long addPicture(byte[] picturedata, String pictureName,String originalName, Integer uploadUserId, Date uploadDate);
	public List<PictureMaster> readUserPicture(Integer uploadUserId);
	public List<PictureMaster> lastAlbumUserPicture(Integer uploadUserId);
//	public int readPicture(String pictureName, Integer uploadUserId);
	//	public int deletePicture(String pictureName, Integer uploadUserId);
	//	public int editName(String pictureName, Integer uploadUserId);
}
