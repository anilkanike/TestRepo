package com.transgrid.mib.ellipse.hr.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
/**
 * 
 * @author Anil Kanike
 *
 */
public class EllipseEmployee {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id = "";
	private String firstName = "";
	private String lastName = "";
	private String orgDesc = "";
	private String emailAddr = "";
	private String workPhone = "";
	private String locationId = "";
	private String locationDesc = "";
	private String staffCategoryCode = "";
	private String staffCategoryDesc = "";
	private String businessUnitDesc = "";
	private String groupDesc = "";
	private String branchDesc = "";
	private String positionId = "";
	private String positionTitle = "";
	private String startDate;
	private String endDate;
	private String statusCode;
	private String statusDesc;
	private String primRepCode;
	private String photoPath;
	private boolean payrollInd;

	// Employee Supervisor Details
	private String superPositionId = "";
	private String superPositionTitle = "";
	private String superEmpId = "";

	// Employee position hierarchy details
	private String hierDetails = ""; // combination of hierarchy Type & Version

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

	public String getOrgDesc() {
		return orgDesc;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

	public String getEmailAddr() {
		return emailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocationDesc() {
		return locationDesc;
	}

	public void setLocationDesc(String locationDesc) {
		this.locationDesc = locationDesc;
	}

	public String getStaffCategoryCode() {
		return staffCategoryCode;
	}

	public void setStaffCategoryCode(String staffCategoryCode) {
		this.staffCategoryCode = staffCategoryCode;
	}

	public String getStaffCategoryDesc() {
		return staffCategoryDesc;
	}

	public void setStaffCategoryDesc(String staffCategoryDesc) {
		this.staffCategoryDesc = staffCategoryDesc;
	}

	public String getBusinessUnitDesc() {
		return businessUnitDesc;
	}

	public void setBusinessUnitDesc(String businessUnitDesc) {
		this.businessUnitDesc = businessUnitDesc;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public String getBranchDesc() {
		return branchDesc;
	}

	public void setBranchDesc(String branchDesc) {
		this.branchDesc = branchDesc;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getPositionTitle() {
		return positionTitle;
	}

	public void setPositionTitle(String positionTitle) {
		this.positionTitle = positionTitle;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getSuperPositionId() {
		return superPositionId;
	}

	public void setSuperPositionId(String superPositionId) {
		this.superPositionId = superPositionId;
	}

	public String getSuperPositionTitle() {
		return superPositionTitle;
	}

	public void setSuperPositionTitle(String superPositionTitle) {
		this.superPositionTitle = superPositionTitle;
	}

	public String getSuperEmpId() {
		return superEmpId;
	}

	public void setSuperEmpId(String superEmpId) {
		this.superEmpId = superEmpId;
	}

	public String getHierDetails() {
		return hierDetails;
	}

	public void setHierDetails(String hierDetails) {
		this.hierDetails = hierDetails;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public boolean isPayrollInd() {
		return payrollInd;
	}

	public void setPayrollInd(boolean payrollInd) {
		this.payrollInd = payrollInd;
	}

	public String getPrimRepCode() {
		return primRepCode;
	}

	public void setPrimRepCode(String primRepCode) {
		this.primRepCode = primRepCode;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
