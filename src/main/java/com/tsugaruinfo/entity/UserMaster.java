package com.tsugaruinfo.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;


/**
 * The persistent class for the user_master database table.
 * 
 */
@Data
@Entity
@Table(name="user_master")
@NamedQuery(name="UserMaster.findAll", query="SELECT u FROM UserMaster u")
public class UserMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userid_seq")
    @SequenceGenerator(name = "userid_seq", sequenceName = "userid_seq", allocationSize = 1)
	private Integer userId;

	private String email;

	private Boolean enabled;

	private String password;

	private String username;
	
	private String role;

	public UserMaster() {
	}
}