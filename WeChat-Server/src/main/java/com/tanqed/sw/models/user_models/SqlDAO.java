package com.tanqed.sw.models.user_models;

import org.springframework.data.jpa.repository.JpaRepository;

/* Custom interface that extends JpaRepository to use basic CRUD operations 
 and JPA featured methods against MySql database. 
 Here we can define custom methods that obtain specific data or 
 do CRUD operations in the way we want. 
 Ref: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/ */

public interface SqlDAO  extends JpaRepository<SqlUser,Long>{

    public SqlUser findByUsername(String username);
}
