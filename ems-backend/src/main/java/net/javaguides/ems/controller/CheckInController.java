package net.javaguides.ems.controller;

import lombok.AllArgsConstructor;
import net.javaguides.ems.entity.CheckIn;
import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.repository.CheckInRepository;
import net.javaguides.ems.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/checkin/")
public class CheckInController {
  private CheckInRepository checkInRepository;

  private EmployeeRepository employeeRepository;

  // Get All Check-Ins
  @GetMapping("/check-in")
  public List<CheckIn> getAllCheckIns() {
    return checkInRepository.findAll();
  }

  // Create New Check In Entry
  @PostMapping("/check-in")
  public ResponseEntity<CheckIn> createTask(@RequestBody CheckIn checkIn) {
    Optional<Employee> employeeInDb = employeeRepository.findById(checkIn.getEmployee().getId());
    if (employeeInDb.isEmpty())
      return ResponseEntity.badRequest().body(checkIn);
    return ResponseEntity.ok(checkInRepository.save(checkIn));
  }
}