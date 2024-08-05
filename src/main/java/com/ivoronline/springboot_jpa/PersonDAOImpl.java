package com.ivoronline.springboot_jpa;

import com.ivoronline.springboot_jpa.entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PersonDAOImpl implements PersonDAO {

  //PROPERTIES
  //@Autowired EntityManager entityManager;
 	@PersistenceContext(name = "myunit") EntityManager entityManager;
  
  //=========================================================================================================
  // SAVE
  //=========================================================================================================
  @Override
  @Transactional
  public void save(Person person) {
    entityManager.persist(person);
  }
  
}


