package com.tsugaruinfo.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;


/**
 * The persistent class for the news_company database table.
 * 
 */
@Data
@Entity
@Table(name="news_company")
@NamedQuery(name="NewsCompany.findAll", query="SELECT n FROM NewsCompany n")
public class NewsCompany implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="company_id")

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "companyid_seq")
    @SequenceGenerator(name = "companyid_seq", sequenceName = "companyid_seq", allocationSize = 1)
	private Integer companyId;

	@Column(name="company_name")
	private String companyName;

	public NewsCompany() {
	}
}