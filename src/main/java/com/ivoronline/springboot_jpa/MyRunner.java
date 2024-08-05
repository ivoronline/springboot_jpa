package com.ivoronline.springboot_jpa;

import com.ivoronline.springboot_jpa.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

  //PROPERTIES
  @Autowired PersonDAO personDAO;

  //=========================================================================================================
  // RUN
  //=========================================================================================================
  @Override
  public void run(String... args) {
    Person person = new Person();
           person.setName("John");
           person.setAge(50);
    personDAO.save(person);
  }
  
}
