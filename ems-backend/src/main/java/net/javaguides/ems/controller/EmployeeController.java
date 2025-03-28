package net.javaguides.ems.controller;


import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
  private EmployeeService employeeService;

  // add employee rest api
  @PostMapping
  public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
    EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
    return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
  }

  // get employee rest api
  @GetMapping("{id}")
  public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId) {
    EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
    return ResponseEntity.ok(employeeDto);
  }

  // get all employees rest api
  @GetMapping
  public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
    List<EmployeeDto> employees = employeeService.getAllEmployees();
    return ResponseEntity.ok(employees);
  }

  // update employee rest api
  @PutMapping("{id}")
  public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId,
                                                    @RequestBody EmployeeDto updatedEmployee) {
    EmployeeDto employeeDto = employeeService.updateEmployee(employeeId, updatedEmployee);
    return ResponseEntity.ok(employeeDto);
  }

  // delete employee rest api
  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId) {
    employeeService.deleteEmployee(employeeId);
    return ResponseEntity.ok("Employee deleted successfully!");
  }
}
