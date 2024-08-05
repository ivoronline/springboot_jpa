package com.ivoronline.springboot_jpa;

import com.ivoronline.springboot_jpa.entity.Person;
import com.ivoronline.springboot_jpa.repository.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

  //PROPERTIES
  @Autowired
  PersonDAO personDAO;

  //=========================================================================================================
  // RUN
  //=========================================================================================================
  @Override
  public void run(String... args) {
    PersonDAO personDAO = new PersonDAOImpl();
    personDAO.save(new Person(0, "John" , 20));
  }
  
}
