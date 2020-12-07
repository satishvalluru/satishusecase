package com.java.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.ActivityTrackerResponseDTO;
import com.java.dto.ActivtyTrackerRequestDTO;
import com.java.exceptions.DailyActivityNotFoundException;
import com.java.model.ActivityTracker;
import com.java.repository.ActivityTrackerRepository;
import com.java.service.ActivityTrackerService;

@Service
public class ActivityTrackerServiceImpl implements ActivityTrackerService {
	private static final Logger logger = LoggerFactory.getLogger(ActivityTrackerServiceImpl.class);

	@Autowired
	ActivityTrackerRepository activityTrackerRepository;

	@Override
	public List<ActivityTrackerResponseDTO> viewDailyActivity(Long empCode) throws DailyActivityNotFoundException {
		logger.debug("Started view daily activity");
		List<ActivityTracker> activityDetails = activityTrackerRepository.findByempCode(empCode);
		if (activityDetails.isEmpty()) {
			throw new DailyActivityNotFoundException("daily activity code not found");
		}
		List<ActivityTrackerResponseDTO> activityTrackerResponseDTO = new ArrayList<>();
		ActivityTrackerResponseDTO activityDetailsDTO = null;
		for (ActivityTracker dailyActivityDetails : activityDetails) {
			activityDetailsDTO = new ActivityTrackerResponseDTO();
			BeanUtils.copyProperties(dailyActivityDetails, activityDetailsDTO);
			activityTrackerResponseDTO.add(activityDetailsDTO);
		}

		return activityTrackerResponseDTO;
	}

	@Override
	public boolean modifyDailyActivity(Map<String, Object> updates, Long empCode)
			throws DailyActivityNotFoundException {
		ActivityTracker activityTracker = new ActivityTracker();
		Optional<ActivityTracker> optionalActivityTracker = activityTrackerRepository.findById(empCode);

		if (!optionalActivityTracker.isPresent()) {
			throw new DailyActivityNotFoundException("dialy activity code not found");
		}

		if (!optionalActivityTracker.isPresent())
			return false;
		activityTracker = optionalActivityTracker.get();

		for (Map.Entry<String, Object> entry : updates.entrySet()) {
			try {
				org.apache.commons.beanutils.BeanUtils.setProperty(activityTracker, entry.getKey(), entry.getValue());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		activityTrackerRepository.save(activityTracker);
		return true;
	}

	@Override
	public boolean deleteDailyActivity(Long empCode) throws DailyActivityNotFoundException {

		List<ActivityTracker> activityTracker = activityTrackerRepository.findByempCode(empCode);

		if (activityTracker.size() == 0) {
			throw new DailyActivityNotFoundException("daily activity code not found");
		}
		for (ActivityTracker activitytrackers : activityTracker) {
			activitytrackers.setFlag(true);
			activityTrackerRepository.saveAll(activityTracker);

		}
		return true;

	}

	@Override
	public String saveActivity(List<ActivtyTrackerRequestDTO> dailyActivtyRequestDTO) {
		List<ActivityTracker> activtyTrackerRequestDTO = new ArrayList<>();
		
		System.out.println( "dailyActivtyRequestDTO" + dailyActivtyRequestDTO.toString());
		dailyActivtyRequestDTO.stream().forEach(activtyRequestDTO -> {
			ActivityTracker activity = new ActivityTracker();
			BeanUtils.copyProperties(activtyTrackerRequestDTO, activity);
			LocalDate date = LocalDate.now();
			activity.setActivityDate(date);
			activity.setFlag(false);
			activity.setActivityDescription(activtyRequestDTO.getActivityDescription());
			activity.setActivityStatus(activtyRequestDTO.getActivityStatus());
			activity.setEmpCode(activtyRequestDTO.getEmpCode());
			System.out.println( "activity  -----" + activity.toString());

			activityTrackerRepository.save(activity);

		});
		return "Success";
	}


}
