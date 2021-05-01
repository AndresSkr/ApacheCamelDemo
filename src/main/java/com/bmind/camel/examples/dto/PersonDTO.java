package com.bmind.camel.examples.dto;

public class PersonDTO {
	
	private Long id;
	
	private String idType;
	
	private String idNumber;
	
	private String fisrtnames;
	
	private String middlename;
	
	private String lastnames;
	
	private String surnames;
	
	private String address;
	
	private String phoneNumber;
	
	public PersonDTO() {
		super();
	}
	public PersonDTO(Long id, String idType, String idNumber, String fisrtnames, String middlename, String lastnames,
			String surnames, String address, String phoneNumber) {
		super();
		this.id = id;
		this.idType = idType;
		this.idNumber = idNumber;
		this.fisrtnames = fisrtnames;
		this.middlename = middlename;
		this.lastnames = lastnames;
		this.surnames = surnames;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFisrtnames() {
		return fisrtnames;
	}

	public void setFisrtnames(String fisrtnames) {
		this.fisrtnames = fisrtnames;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastnames() {
		return lastnames;
	}

	public void setLastnames(String lastnames) {
		this.lastnames = lastnames;
	}

	public String getSurnames() {
		return surnames;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}
	
	

}
