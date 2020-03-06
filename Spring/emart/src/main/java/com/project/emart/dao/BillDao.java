package com.project.emart.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.emart.entity.BillEntity;

public interface BillDao extends JpaRepository<BillEntity,Integer> {

}
