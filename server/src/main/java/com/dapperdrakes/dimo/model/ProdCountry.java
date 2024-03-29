package com.dapperdrakes.dimo.model;

import java.util.Set;

/**
 * ProdCountry generated by hbm2java
 */

public class ProdCountry implements java.io.Serializable {

	private String iso_3166_1;
	private String name;

	public ProdCountry() {
	}

	
	public String getIso_3166_1() {
		return iso_3166_1;
	}


	public void setIso_3166_1(String iso_3166_1) {
		this.iso_3166_1 = iso_3166_1;
	}


	public ProdCountry(String iso_3166_1, String name) {
		super();
		this.iso_3166_1 = iso_3166_1;
		this.name = name;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

}
