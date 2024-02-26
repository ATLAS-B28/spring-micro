package com.example.departmentservice.controller;


import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController//able to handle HTTP requests
@RequestMapping("/api/department")
@AllArgsConstructor
public class DepartmentController {

    //inject dependency
    private DepartmentService departmentService;

    //build rest api for saving department
    @PostMapping("/save")
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto) {
         //@Request body extracts the data from the request body using internal Http convertors
        DepartmentDto savedDepartmentDto = departmentService.saveDepartment(departmentDto);

        return new ResponseEntity<>(savedDepartmentDto, HttpStatus.CREATED);
    }

    //build rest api for getting department by code
    @GetMapping("/get/{department-code}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable("department-code") String departmentCode) {
        //@PathVariable - This annotation is used in Spring
        // Boot to extract data from the URL path.
        // It allows you to define placeholders in
        // your request mapping URL and bind those placeholders
        // to method parameters.
        DepartmentDto departmentDto = departmentService.getDepartmentByCode(departmentCode);

        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

}
