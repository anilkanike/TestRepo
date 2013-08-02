package com.transgrid.mib.ellipse.hr.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
/**
 * 
 * @author Anil Kanike
 *
 */
public class EllipseTrainingResults {

	private String empId = "";
	private String attendeeType = "";
	private String courseId = "";
	private String courseType = "";
	private String courseTypeDesc = "";
	private String sessionNo = "";
	private String attendeeResult = "";
	private String courseMajorRev = "";
	private String completionDate;
	private String requalDate;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getAttendeeType() {
		return attendeeType;
	}

	public void setAttendeeType(String attendeeType) {
		this.attendeeType = attendeeType;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getCourseTypeDesc() {
		return courseTypeDesc;
	}

	public void setCourseTypeDesc(String courseTypeDesc) {
		this.courseTypeDesc = courseTypeDesc;
	}

	public String getSessionNo() {
    	return sessionNo;
    }

	public void setSessionNo(String sessionNo) {
    	this.sessionNo = sessionNo;
    }

	public String getAttendeeResult() {
		return attendeeResult;
	}

	public void setAttendeeResult(String attendeeResult) {
		this.attendeeResult = attendeeResult;
	}

	public String getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}

	public String getRequalDate() {
		return requalDate;
	}

	public void setRequalDate(String requalDate) {
		this.requalDate = requalDate;
	}

	public String getCourseMajorRev() {
		return courseMajorRev;
	}

	public void setCourseMajorRev(String courseMajorRev) {
		this.courseMajorRev = courseMajorRev;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
