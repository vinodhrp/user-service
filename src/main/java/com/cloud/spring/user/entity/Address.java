package com.cloud.spring.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Address")
@Table(name = "USR_ADDRESS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ADDRS_ID")
	private Long addrsId;

	@Column(name = "STREET_NAME")
	private String streetName;

	@Column(name = "STREET_NO")
	private String streetNo;

	@Column(name = "CITY")
	private String city;

	@Column(name = "ZIP_CODE")
	private String zipcode;

	@Column(name = "COUNTRY")
	private String country;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "USR_ID")
	private UserDetail userDetail;

	public Long getAddrsId() {
		return addrsId;
	}

	public void setAddrsId(Long addrsId) {
		this.addrsId = addrsId;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetNo() {
		return streetNo;
	}

	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}
	
	
	

}
