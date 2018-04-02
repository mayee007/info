package com.mine.info.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "TECHNOLOGY")
@XmlRootElement 
public class Technology {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	int technologyId;
	
	@Column 
	String technologyType;
	
	@Column
	String category;
		
	public Technology() {
	}
	
	public Technology(int technologyId, String technologyType, String category) {
		super();
		this.technologyId = technologyId;
		this.technologyType = technologyType;
		this.category = category;
	}
	
	public int getTechnologyId() {
		return technologyId;
	}
	public void setTechnologyId(int technologyId) {
		this.technologyId = technologyId;
	}
	public String getTechnologyType() {
		return technologyType;
	}
	public void setTechnologyType(String technologyType) {
		this.technologyType = technologyType;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
