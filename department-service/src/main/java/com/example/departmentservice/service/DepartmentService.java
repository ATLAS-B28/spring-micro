package com.example.departmentservice.service;

import com.example.departmentservice.dto.DepartmentDto;

public interface DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto departmentDto);
    //we are passing the dto coming from the client to the service

    DepartmentDto getDepartmentByCode(String departmentCode);
}
