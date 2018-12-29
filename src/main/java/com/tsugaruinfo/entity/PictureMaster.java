package com.tsugaruinfo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;


/**
 * The persistent class for the picture_master database table.
 *
 */
@Data
@Entity
@Table(name="picture_master")
@NamedQuery(name="PictureMaster.findAll", query="SELECT p FROM PictureMaster p")
public class PictureMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="picture_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pictid_seq")
    @SequenceGenerator(name = "pictid_seq", sequenceName = "pictid_seq", allocationSize = 1)
	private Long pictureId;

	@Column(name="upload_UserId")
	private int uploadUserId;

	@Column(name="upload_date")
	private Date uploadDate;

	@Column(name="picture_name")
	private String pictureName;

	@Column(name="original_name")
	private String originalName;

	@Column(name="picture_data")
	private byte[] pictureData;

	@Column(name="extension")
	private String extension;

	@Column(name="base64string", length = 10485760)
	private String base64string;


	public PictureMaster() {
	}
}