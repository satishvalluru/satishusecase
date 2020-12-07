package com.java.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.dto.ActivityTrackerResponseDTO;
import com.java.dto.ActivtyTrackerRequestDTO;
import com.java.exceptions.DailyActivityNotFoundException;
import com.java.service.ActivityTrackerService;

@Validated
@RestController
public class ActivityController {

	@Autowired
	ActivityTrackerService activityTrackerService;
	
	@PostMapping("/saveDailyActivity")
	public ResponseEntity<String> addDailyActivity(@Valid @RequestBody List<ActivtyTrackerRequestDTO> dailyActivtyRequestDTO) throws Exception {
		activityTrackerService.saveActivity(dailyActivtyRequestDTO);
		return new ResponseEntity<>("daily activity added successfully", HttpStatus.CREATED);

	}
	
	@GetMapping("/viewDailyActivity/{empCode}")
	public ResponseEntity<List<ActivityTrackerResponseDTO>> viewActivityWithEmpCode(@RequestParam Long empCode) throws DailyActivityNotFoundException {
		List<ActivityTrackerResponseDTO> details = activityTrackerService.viewDailyActivity(empCode);
		return new ResponseEntity<>(details, HttpStatus.CREATED);

	}
	
	@PatchMapping("/update/{empCode}")
	public ResponseEntity<String> modifyDailyActivity(@RequestBody Map<String, Object> updates, @PathVariable Long empCode) throws DailyActivityNotFoundException {
		System.out.println(updates.size());

		boolean isUpdated = activityTrackerService.modifyDailyActivity(updates, empCode);
		if (isUpdated)
			return new ResponseEntity<>("Successfully modified daily activity", HttpStatus.OK);

		return new ResponseEntity<>("Failed to modify daily activity", HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/deleteEmployee/{empCode}")
	public ResponseEntity<String> deleteDailyActivity(@PathVariable Long empCode) throws DailyActivityNotFoundException {
		activityTrackerService.deleteDailyActivity(empCode);
		return new ResponseEntity<>("daily activity details deleted successfully", HttpStatus.CREATED);

	}
}
