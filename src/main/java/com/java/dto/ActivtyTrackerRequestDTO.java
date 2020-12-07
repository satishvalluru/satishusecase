package com.java.dto;

import javax.validation.constraints.NotEmpty;

public class ActivtyTrackerRequestDTO {

	private Long empCode;

	@NotEmpty(message = "Please provide activity description")
	private String activityDescription;

	@NotEmpty(message = "Please provide activity status")
	private String activityStatus;

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

	public Long getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Long empCode) {
		this.empCode = empCode;
	}

	@Override
	public String toString() {
		return "ActivtyTrackerRequestDTO [empCode=" + empCode + ", activityDescription=" + activityDescription
				+ ", activityStatus=" + activityStatus + "]";
	}

}
