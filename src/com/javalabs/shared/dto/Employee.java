package com.javalabs.shared.dto;

import java.util.Date;

import javax.ws.rs.QueryParam;

/**
 * Employee DTO
 * 
 * @author PATavares
 * @since Jan 2021
 */
public class Employee implements Model {

	private static final long serialVersionUID = 8716209460400644479L;

	@QueryParam("id")
	private Long id;
	@QueryParam("name")
	private String name;
	@QueryParam("surname")
	private String surname;
	@QueryParam("dob")
	private Date dob;
	@QueryParam("city")
	private String city;
	@QueryParam("state")
	private String state;
	@QueryParam("country")
	private String country;
	@QueryParam("us_citizen")
	private Boolean usCitizen;
	@QueryParam("phone")
	private String phone;
	@QueryParam("email")
	private String email;
	@QueryParam("preferred_contact")
	private String preferredContact;
	@QueryParam("occupation")
	private String occupation;
	@QueryParam("website")
	private String website;
	@QueryParam("instagram")
	private String instagram;
	@QueryParam("photo_1")
	private String photo1;
	@QueryParam("photo_2")
	private String photo2;
	@QueryParam("photo_3")
	private String photo3;
	@QueryParam("photo_4")
	private String photo4;
	@QueryParam("photo_5")
	private String photo5;
	@QueryParam("photo_6")
	private String photo6;
	@QueryParam("photo_7")
	private String photo7;
	@QueryParam("photo_8")
	private String photo8;
	@QueryParam("photo_9")
	private String photo9;
	@QueryParam("photo_10")
	private String photo10;
	@QueryParam("government_issued_id_type")
	private String governmentIssuedIdType;
	@QueryParam("front_of_photo_id")
	private String frontOfPhotoId;
	@QueryParam("digital_signature")
	private String digitalSignature;
	@QueryParam("digital_signature_date")
	private Date digitalSignatureDate;
	
	public Employee() {
		this("", "", "");
	}
	
	public Employee(String email) {
		this.email = email;
	}

	public Employee(String name, String surname, String email) {
		this.name = name;
		this.surname = surname;
		this.email = email;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Boolean getUsCitizen() {
		return usCitizen;
	}

	public void setUsCitizen(Boolean usCitizen) {
		this.usCitizen = usCitizen;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPreferredContact() {
		return preferredContact;
	}

	public void setPreferredContact(String preferredContact) {
		this.preferredContact = preferredContact;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getPhoto1() {
		return photo1;
	}

	public void setPhoto1(String photo1) {
		this.photo1 = photo1;
	}

	public String getPhoto2() {
		return photo2;
	}

	public void setPhoto2(String photo2) {
		this.photo2 = photo2;
	}

	public String getPhoto3() {
		return photo3;
	}

	public void setPhoto3(String photo3) {
		this.photo3 = photo3;
	}

	public String getPhoto4() {
		return photo4;
	}

	public void setPhoto4(String photo4) {
		this.photo4 = photo4;
	}

	public String getPhoto5() {
		return photo5;
	}

	public void setPhoto5(String photo5) {
		this.photo5 = photo5;
	}

	public String getPhoto6() {
		return photo6;
	}

	public void setPhoto6(String photo6) {
		this.photo6 = photo6;
	}

	public String getPhoto7() {
		return photo7;
	}

	public void setPhoto7(String photo7) {
		this.photo7 = photo7;
	}

	public String getPhoto8() {
		return photo8;
	}

	public void setPhoto8(String photo8) {
		this.photo8 = photo8;
	}

	public String getPhoto9() {
		return photo9;
	}

	public void setPhoto9(String photo9) {
		this.photo9 = photo9;
	}

	public String getPhoto10() {
		return photo10;
	}

	public void setPhoto10(String photo10) {
		this.photo10 = photo10;
	}

	public String getGovernmentIssuedIdType() {
		return governmentIssuedIdType;
	}

	public void setGovernmentIssuedIdType(String governmentIssuedIdType) {
		this.governmentIssuedIdType = governmentIssuedIdType;
	}

	public String getFrontOfPhotoId() {
		return frontOfPhotoId;
	}

	public void setFrontOfPhotoId(String frontOfPhotoId) {
		this.frontOfPhotoId = frontOfPhotoId;
	}

	public String getDigitalSignature() {
		return digitalSignature;
	}

	public void setDigitalSignature(String digitalSignature) {
		this.digitalSignature = digitalSignature;
	}
	
	public Date getDigitalSignatureDate() {
		return digitalSignatureDate;
	}

	public void setDigitalSignatureDate(Date digitalSignatureDate) {
		this.digitalSignatureDate = digitalSignatureDate;
	}
	
}
