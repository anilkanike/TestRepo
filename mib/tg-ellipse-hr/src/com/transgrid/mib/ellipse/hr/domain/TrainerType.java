package com.transgrid.mib.ellipse.hr.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
/**
 * 
 * @author Anil Kanike
 *
 */
public class TrainerType {

	private String id = "";
	private String firstName = "";
	private String lastName = "";
	private String empNonEmpIndicator;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmpNonEmpIndicator() {
		return empNonEmpIndicator;
	}

	public void setEmpNonEmpIndicator(String empNonEmpIndicator) {
		this.empNonEmpIndicator = empNonEmpIndicator;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
