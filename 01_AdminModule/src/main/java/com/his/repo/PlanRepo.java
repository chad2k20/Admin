package com.his.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.his.entitty.Plan;

public interface PlanRepo extends JpaRepository<Plan, Integer> {

}
