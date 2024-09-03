package com.study.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.springboot.domain.Diary;
import com.study.springboot.domain.Member;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    Optional<Diary> findBydNum(Long dNum);
}
