package com.onkonfeton.avia.repository;

import com.onkonfeton.avia.model.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneRepository extends JpaRepository<Plane,Integer> {
}
