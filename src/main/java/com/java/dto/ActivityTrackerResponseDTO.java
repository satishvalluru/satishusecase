package com.java.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ActivityTrackerResponseDTO {

	private Long empCode;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date activityDate;

	private String activityDescription;

	private String activityStatus;

	public Long getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Long empCode) {
		this.empCode = empCode;
	}

	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	public String getActivityDescription() {
		return activityDescription;
	}

	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}

	public String getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}
}
