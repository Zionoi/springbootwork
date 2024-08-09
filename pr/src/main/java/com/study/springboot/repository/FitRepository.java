package com.study.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.study.springboot.domain.Fit;

public interface FitRepository extends JpaRepository<Fit, Long>{

}
