package org.example.businesspack.repositories;

import org.example.businesspack.entities.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRepository extends JpaRepository<Long, Maintenance> {
}
