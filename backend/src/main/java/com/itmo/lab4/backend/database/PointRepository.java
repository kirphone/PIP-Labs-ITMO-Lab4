package com.itmo.lab4.backend.database;

import com.itmo.lab4.backend.database.entities.PointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<PointEntity, Long> {


}
