/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services.impl;

import com.bootcamp.cobaspringapplication.entities.Department;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author arman
 */
@Service
public class DepartmentRest {

    String url = "http://192.168.128.114:8083/springbootapi/api/dept";
    private static RestTemplate restTemplate = new RestTemplate();

    public String delete(String id) {
        String uri = url + "/{id}";
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        restTemplate.delete(uri, params);
        return "Delete Berhasil!";
    }

    public Department save(Department department) {
        return restTemplate.postForObject(url, department, Department.class);
    }

    public Department getById(String id) {
        String uri = url + "/{id}";
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        Department department = restTemplate.getForObject(uri, Department.class, params);
        return department;
    }

    public List<Department> getAll() {
        ResponseEntity<List<Department>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Department>>() {

        });
        List<Department> departments = response.getBody();
        return departments;
    }
}
