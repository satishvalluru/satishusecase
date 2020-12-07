package com.java.service;

import java.util.List;
import java.util.Map;

import com.java.dto.ActivityTrackerResponseDTO;
import com.java.dto.ActivtyTrackerRequestDTO;
import com.java.exceptions.DailyActivityNotFoundException;

public interface ActivityTrackerService {

	List<ActivityTrackerResponseDTO> viewDailyActivity(Long empCode) throws DailyActivityNotFoundException;

	boolean modifyDailyActivity(Map<String, Object> updates, Long empCode) throws DailyActivityNotFoundException;

	boolean deleteDailyActivity(Long empCode) throws DailyActivityNotFoundException;

	String saveActivity(List<ActivtyTrackerRequestDTO> dailyActivtyRequestDTO);

}
