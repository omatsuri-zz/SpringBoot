/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.repositories;

import com.bootcamp.cobaspringapplication.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author arman
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String>{
    
    @Query(value = "SELECT * FROM TB_M_Employee WHERE id = ?1", nativeQuery = true)
    public Employee getById(String id);
}
