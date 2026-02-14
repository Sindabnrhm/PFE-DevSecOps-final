package tn.arabsoft.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.arabsoft.auth.entity.Sanction;

public interface SanctionRepository extends JpaRepository<Sanction, Long> {

}
