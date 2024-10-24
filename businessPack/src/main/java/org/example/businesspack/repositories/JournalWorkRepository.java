package org.example.businesspack.repositories;

import org.example.businesspack.entities.JournalWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalWorkRepository extends JpaRepository<JournalWork, Long> {
}
