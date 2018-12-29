package com.tsugaruinfo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.tsugaruinfo.entity.PictureMaster;

@Component
public class MyAlbumDao extends AbstractDataAccessObject<PictureMaster> {

	@PersistenceContext
	private EntityManager entityManager;


    public List<PictureMaster> findLastUploadedPicture(int uploadUserId) {

    	// SQL 作成
    	String SQL = new String();
    	SQL = " SELECT * From picture_master "
        		+ " WHERE upload_userid = " + uploadUserId
        		+ " ORDER BY upload_date"
        		+ " LIMIT 9";

	    @SuppressWarnings("unchecked")
		List<PictureMaster> list = this.entityManager
	    		.createNativeQuery(SQL, PictureMaster.class)
	    		.getResultList();

	    return list;
	}
}
