package com.dapperdrakes.dimo.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Person generated by hbm2java
 */
@Entity
@Table(name="person")
public class Person implements java.io.Serializable {

	private int id;
	private String name;
	private Character gender;


	public Person() {
	}

	public Person(int id) {
		this.id = id;
	}

	public Person(int id, String name, Character gender) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Character getGender() {
		return this.gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	

}
