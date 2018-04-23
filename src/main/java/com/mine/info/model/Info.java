package com.mine.info.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "INFO")
@XmlRootElement 
public class Info {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	int id; 
	
	@Column
	String subject; 
	
	@Column
	String description; 
	
	@Column
	Date submitDate; 
	
	@Column
	Date modifiedDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TECHNOLOGY", referencedColumnName = "ID")
	Technology technology;
	
	public Info() {
	}
	
	public Info(int id, String subject, String description, Date submitDate, Date modifiedDate, Technology technology) {
		super();
		this.id = id;
		this.subject = subject;
		this.description = description;
		this.submitDate = submitDate;
		this.modifiedDate = modifiedDate;
		this.technology = technology;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Technology getTechnology() {
		return technology;
	}
	public void setTechnology(Technology technology) {
		this.technology = technology;
	} 
	
	
}
