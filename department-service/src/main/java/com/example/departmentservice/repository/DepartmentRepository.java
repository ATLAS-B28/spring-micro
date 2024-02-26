package com.example.departmentservice.repository;

import com.example.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    //long is data type of primary key

    //find by department code
    //implement a custom method in repository
    Department findByDepartmentCode(String departmentCode);
}
