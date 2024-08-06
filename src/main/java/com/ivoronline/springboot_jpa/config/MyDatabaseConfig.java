package com.ivoronline.springboot_jpa.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
//@EnableJpaRepositories
//@EnableTransactionManagement
public class MyDatabaseConfig {

  //PROPERTIES
  @Autowired private Environment env; //TO RECREATE TABLE

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
  
    //TO RECREATE TABLE
    //Hibernate Properties from application.properties are ignored => We need to set them manually
    HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
                              hibernateJpaVendorAdapter.setGenerateDdl(true);
                              hibernateJpaVendorAdapter.setShowSql(true);
                              
    LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactory.setDataSource         (dataSource());
    entityManagerFactory.setPackagesToScan     ("com.ivoronline.springboot_jpa.entity");
  //entityManagerFactory.setJpaVendorAdapter   (new HibernateJpaVendorAdapter());
    entityManagerFactory.setJpaVendorAdapter   (hibernateJpaVendorAdapter); //TO RECREATE TABLE
    entityManagerFactory.setJpaProperties(additionalProperties());          //TO RECREATE TABLE
    entityManagerFactory.setPersistenceUnitName("myunit");
      
    return entityManagerFactory;
    
  }
  
  //=========================================================================================================
  // ADDITIONAL PROPERTIES
  //=========================================================================================================
  // TO RECREATE TABLE =>  Reading Hibernate Properties from application.properties
  private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
		properties.setProperty("hibernate.show_sql"    , env.getProperty("spring.jpa.show-sql"          ));
		return properties;
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



