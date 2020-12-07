package com.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.model.ActivityTracker;

@Repository
public interface ActivityTrackerRepository extends JpaRepository<ActivityTracker, Long> {

	List<ActivityTracker> findByempCode(Long empCode);


}
