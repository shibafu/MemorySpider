package com.tsugaruinfo.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.Date;


/**
 * The persistent class for the news_article_master database table.
 * 
 */
@Data
@Entity
@Table(name="news_article_master")
@NamedQuery(name="NewsArticleMaster.findAll", query="SELECT n FROM NewsArticleMaster n")
public class NewsArticleMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="article_id")

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "articleid_seq")
    @SequenceGenerator(name = "articleid_seq", sequenceName = "articleid_seq", allocationSize = 1)
	private Integer articleId;

	@Column(name="article_header")
	private String articleHeader;

	@Column(name="article_sentence")
	private String articleSentence;

	@Column(name="company_name")
	private String companyName;

	@Temporal(TemporalType.DATE)
	private Date date;

	public NewsArticleMaster() {
	}
}