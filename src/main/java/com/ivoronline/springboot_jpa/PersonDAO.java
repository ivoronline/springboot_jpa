package com.ivoronline.springboot_jpa;

import com.ivoronline.springboot_jpa.entity.Person;

public interface PersonDAO {
  void save(Person person);
}
