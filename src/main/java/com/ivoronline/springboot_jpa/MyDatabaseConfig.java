package com.ivoronline.springboot_jpa;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.ivoronline.springboot_jpa.repository")
@EnableTransactionManagement
public class MyDatabaseConfig {

  //=========================================================================================================
  // DATA SOURCE
  //=========================================================================================================
  @Bean
  public DataSource dataSource() {
  
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
                            dataSource.setUrl            ("jdbc:oracle:thin:@localhost:1522/orcl");
                            dataSource.setUsername       ("TEST");
                            dataSource.setPassword       ("LETMEIN");
                          //dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
                          
    return dataSource;
    
  }
  
  //=========================================================================================================
  // ENTITY MANAGER FACTORY
  //=========================================================================================================
  @Bean
  LocalContainerEntityManagerFactoryBean entityManagerFactory() {
  
    LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactory.setDataSource         (dataSource());
    entityManagerFactory.setPackagesToScan     ("com.ivoronline.springboot_jpa.entity");
    entityManagerFactory.setJpaVendorAdapter   (new HibernateJpaVendorAdapter());
    entityManagerFactory.setPersistenceUnitName("myunit");
      
    return entityManagerFactory;
    
  }
  
  //=========================================================================================================
  // TRANSACTION MANAGER
  //=========================================================================================================
  @Bean
  PlatformTransactionManager myTransactionManager(EntityManagerFactory emf) {
    JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
                          jpaTransactionManager.setEntityManagerFactory(emf);
    return jpaTransactionManager;
  }
  
}



