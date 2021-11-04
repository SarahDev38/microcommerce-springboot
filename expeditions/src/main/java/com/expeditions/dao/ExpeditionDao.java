package com.expeditions.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expeditions.model.Expedition;

@Repository
public interface ExpeditionDao extends JpaRepository<Expedition, Integer> {
}
