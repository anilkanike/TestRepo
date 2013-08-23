package com.transgrid.mib.ellipse.hr.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 
 * @author Anil Kanike
 * 
 */
public class EllipseResource {

	private String id = "";
	private String empNonEmpIndicator = "";
	private String firstName = "";
	private String lastName = "";
	private String resourceType = "";
	private String resourceTypeDesc = "";
	private String competencyLevel = "";
	private String effectiveDate = "";
	private String gainedDate = "";
	private String expiryDate = "";
	private boolean empPayrollInd;
	private String resourceClass = "";
	private String resourceCode = "";
	private boolean isTypeDelete;
	private String authorisedBy;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmpNonEmpIndicator() {
		return empNonEmpIndicator;
	}

	public void setEmpNonEmpIndicator(String empNonEmpIndicator) {
		this.empNonEmpIndicator = empNonEmpIndicator;
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

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getResourceTypeDesc() {
		return resourceTypeDesc;
	}

	public void setResourceTypeDesc(String resourceTypeDesc) {
		this.resourceTypeDesc = resourceTypeDesc;
	}

	public String getCompetencyLevel() {
		return competencyLevel;
	}

	public void setCompetencyLevel(String competencyLevel) {
		this.competencyLevel = competencyLevel;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getGainedDate() {
		return gainedDate;
	}

	public void setGainedDate(String gainedDate) {
		this.gainedDate = gainedDate;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getResourceClass() {
		return resourceClass;
	}

	public void setResourceClass(String resourceClass) {
		this.resourceClass = resourceClass;
	}

	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	public boolean isEmpPayrollInd() {
		return empPayrollInd;
	}

	public void setEmpPayrollInd(boolean empPayrollInd) {
		this.empPayrollInd = empPayrollInd;
	}
	
	public boolean isTypeDelete() {
		return isTypeDelete;
	}

	public void setTypeDelete(boolean isTypeDelete) {
		this.isTypeDelete = isTypeDelete;
	}

	public String getAuthorisedBy() {
		return authorisedBy;
	}

	public void setAuthorisedBy(String authorisedBy) {
		this.authorisedBy = authorisedBy;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
