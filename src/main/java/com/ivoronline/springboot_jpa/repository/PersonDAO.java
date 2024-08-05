package com.ivoronline.springboot_jpa.repository;

import com.ivoronline.springboot_jpa.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDAO extends JpaRepository<Person, Integer> { }
