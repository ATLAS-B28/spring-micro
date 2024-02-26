package com.example.employeeservice.service;

import com.example.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface ApiClient {
    //declare interface method and it will be dynamically created
    @GetMapping("/api/department/get/{department-code}")
    DepartmentDto getDepartmentByCode(@PathVariable("department-code") String departmentCode);
        //@PathVariable - This annotation is used in Spring
        // Boot to extract data from the URL path.
        // It allows you to define placeholders in
        // your request mapping URL and bind those placeholders
        // to method parameters.

}
