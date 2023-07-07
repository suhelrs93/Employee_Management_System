package com.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.entity.HolidayList;

public interface HolidayRepository extends JpaRepository<HolidayList, Integer>{

}
