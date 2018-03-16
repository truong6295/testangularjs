package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Personal;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Integer> {

}
