package com.example.employeeservice.service.impl;

import com.example.employeeservice.dto.APIResponseDto;
import com.example.employeeservice.dto.DepartmentDto;
import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.service.ApiClient;
import com.example.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    //private RestTemplate restTemplate;

    //private WebClient webClient;

    private ApiClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
          employeeDto.getId(),
          employeeDto.getFirstName(),
          employeeDto.getLastName(),
          employeeDto.getEmail(),
          employeeDto.getDepartmentCode()
        );

        Employee savedEmployee = employeeRepository.save(employee);

        return new EmployeeDto(
          savedEmployee.getId(),
          savedEmployee.getFirstName(),
          savedEmployee.getLastName(),
          savedEmployee.getEmail(),
          savedEmployee.getDepartmentCode()
        );

    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {
       Employee employee = employeeRepository.findById(employeeId).get();
        //.get(): This method is chained after findById(employeeId) and is
        // used to retrieve the actual employee object from the
        // Optional<Employee> returned by the findById method.
        // The Optional type is used to handle cases where the employee
        // record may not exist in the database for the given ID. Calling
        // .get() on the Optional returns the employee object if it exists,
        // or throws a NoSuchElementException if it doesn't.

        //we need to inject a RestTemplate
        //and we need to make a REST api
        //call to department service
        //from the getEmployeeById method

        //we need to make a REST api call from department service
        //for we use getForEntity
        // String url,
        // Class<T> responseType,
        // Object... uriVariables

        //ResponseEntity<DepartmentDto>  responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/"+employee.get().getDepartmentCode(), DepartmentDto.class);


        //System.out.println(employee.get().getDepartmentCode());
        //DepartmentDto departmentDto = responseEntity.getBody();

        //APIResponseDto apiResponseDto = new APIResponseDto();
        //apiResponseDto.setEmployee(employeeDto);
        //apiResponseDto.setDepartment(departmentDto);

        //return apiResponseDto;

        //ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/"+employee.getDepartmentCode(), DepartmentDto.class);

       /* DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8080/api/department/get/"+employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();*/

        DepartmentDto departmentDto = apiClient.getDepartmentByCode(employee.getDepartmentCode());

        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );

       // DepartmentDto departmentDto = responseEntity.getBody();

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;

    }
}
