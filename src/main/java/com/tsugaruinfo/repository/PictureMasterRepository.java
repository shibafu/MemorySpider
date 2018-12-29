package com.tsugaruinfo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tsugaruinfo.entity.PictureMaster;


@Repository
public interface PictureMasterRepository extends JpaRepository<PictureMaster, Integer> {

    public PictureMaster findByPictureId(Long pictureId);
    public PictureMaster findByPictureName(String pictureName);

    public List<PictureMaster> findByUploadUserId(int uploadUserId);

    //Likeであいまい検索
//    public List<UserMaster> findByUsernameLike(String usernamename);
//    public List<UserMaster> findByEmailLike(String email);
}
