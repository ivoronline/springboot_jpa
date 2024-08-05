package com.ivoronline.springboot_jpa;

import com.ivoronline.springboot_jpa.entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDAOImpl implements PersonDAO {

  //PROPERTIES
  //@Autowired
 	@PersistenceContext(name = "myunit")
 	EntityManager entityManager;
  
  //=========================================================================================================
  // SAVE
  //=========================================================================================================
  @Override
  public void save(Person person) {
    entityManager.persist(person);
  }
  
}


