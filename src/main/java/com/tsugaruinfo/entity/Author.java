package com.tsugaruinfo.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;


/**
 * The persistent class for the "Author" database table.
 * 
 */
@Data
@Entity
@Table(name="\"Author\"")
@NamedQuery(name="Author.findAll", query="SELECT a FROM Author a")
public class Author implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="author_id")

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authorid_seq")
    @SequenceGenerator(name = "authorid_seq", sequenceName = "authorid_seq", allocationSize = 1)
	private Integer authorId;

	@Column(name="author_name")
	private String authorName;

	private String authority;

	private String email;

	private Boolean enabled;

	private String password;

	public Author() {
	}
}