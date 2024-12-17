package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.entitty.Plan;
import com.his.entitty.PlanCategory;
import com.his.repo.PlanCategoryRepo;
import com.his.repo.PlanRepo;

@Service
public class PlanServiceImpl implements PlanService{
	
	@Autowired
	private PlanRepo repo;
	
	@Autowired
	private PlanCategoryRepo planCatRepo;

	@Override
	public Map<Integer, String> getPlanCategories() {
		
		List<PlanCategory> categories = planCatRepo.findAll();

		Map<Integer, String> categoryMap = new HashMap<>();

		categories.forEach(category -> {
			categoryMap.put(category.getCategoryId(), category.getCategoryName());
		});
		return categoryMap;
	}

	@Override
	public boolean savePlan(Plan plan) {
		
		Plan saved = repo.save(plan);
		
		
		
		return saved.getPlanId()!=null;
	}

	@Override
	public List<Plan> getAllPlans() {
		
		
		return repo.findAll();
	}

	@Override
	public Plan getPlanById(Integer planId) {
		
		Optional<Plan> findById = repo.findById(planId);
		if(findById.isPresent()) {
			return findById.get();
		}
		
		return null;
	}

	@Override
	public boolean updatePlan(Plan plan) {
		
		repo.save(plan);
		
		return plan.getPlanId()!=null;
	}

	@Override
	public boolean deletePlanById(Integer planId) {
		
		boolean status=false;
		try {
		repo.deleteById(planId);
			status=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean planStatusChange(Integer planId, String status) {
		Optional<Plan> findById = repo.findById(planId);
		if(findById.isPresent()) {
			Plan plan = findById.get();
			plan.setActiveSw(status);
			repo.save(plan);
			return true;
		}
		return false;
	}

}
