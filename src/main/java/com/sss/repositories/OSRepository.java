package com.sss.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sss.domain.OS;

@Repository
public interface OSRepository extends JpaRepository<OS, Integer>{

}
