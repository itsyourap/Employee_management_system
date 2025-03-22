package net.javaguides.ems.controller;

import lombok.AllArgsConstructor;
import net.javaguides.ems.exception.ResourceNotFoundException;
import net.javaguides.ems.entity.Attendance;
import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.repository.AttendanceRepository;
import net.javaguides.ems.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/attendance/")
public class AttendanceController {
  private EmployeeRepository employeeRepository;
  private AttendanceRepository attendanceRepository;

  // Get All Attendances List
  @GetMapping("/attendance")
  public List<Attendance> getAllTasks() {
    return attendanceRepository.findAll();
  }

  // Add Attendance Data
  @PostMapping("/attendance")
  public ResponseEntity<Attendance> createAttendance(@RequestBody Attendance attendance) {
    for (Employee employee : attendance.getPresentEmployees()) {
      Optional<Employee> employeeInDb = employeeRepository.findById(employee.getId());
      if (employeeInDb.isEmpty())
        return ResponseEntity.badRequest().body(attendance);
    }

    if (attendance.getPresentEmployees().isEmpty())
      return ResponseEntity.badRequest().body(attendance);

    return ResponseEntity.ok(attendanceRepository.save(attendance));
  }

  // Get Attendance by ID
  @GetMapping("/attendance/{id}")
  public ResponseEntity<Attendance> getAttendanceById(@PathVariable Long id) {
    Attendance attendance = attendanceRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Attendance Record does not exist with id:" + id));

    return ResponseEntity.ok(attendance);
  }

  // Get Attendance by Date
  @GetMapping("/attendance/date/{date}")
  public ResponseEntity<Attendance> getAttendanceByDate(@PathVariable String date) {
    Attendance attendance = attendanceRepository.findByDate(LocalDate.parse(date))
        .orElseThrow(() -> new ResourceNotFoundException("Attendance Record does not exist with date:" + date));

    return ResponseEntity.ok(attendance);
  }

  // Update Attendance by ID
  @PutMapping("/attendance/{id}")
  public ResponseEntity<Attendance> updateAttendanceRecord(@PathVariable Long id, @RequestBody Attendance attendanceDetails) {
    Attendance attendance = attendanceRepository.findById(id).orElse(attendanceDetails);

    for (Employee employee : attendanceDetails.getPresentEmployees()) {
      Optional<Employee> employeeInDb = employeeRepository.findById(employee.getId());
      if (employeeInDb.isEmpty())
        return ResponseEntity.badRequest().body(attendance);
    }

    attendance.setPresentEmployees(attendanceDetails.getPresentEmployees());

    if (attendance.getPresentEmployees().isEmpty()){
      attendanceRepository.delete(attendance);
      return ResponseEntity.ok(attendanceDetails);
    }

    Attendance updateAttendance = attendanceRepository.save(attendance);
    return ResponseEntity.ok(updateAttendance);
  }

  // Update Attendance by Date
  @PutMapping("/attendance/date/{date}")
  public ResponseEntity<Attendance> updateAttendanceRecord(@PathVariable String date, @RequestBody Attendance attendanceDetails) {
    Attendance attendance = attendanceRepository.findByDate(LocalDate.parse(date)).orElse(attendanceDetails);

    for (Employee employee : attendanceDetails.getPresentEmployees()) {
      Optional<Employee> employeeInDb = employeeRepository.findById(employee.getId());
      if (employeeInDb.isEmpty())
        return ResponseEntity.badRequest().body(attendance);
    }

    attendance.setPresentEmployees(attendanceDetails.getPresentEmployees());

    if (attendance.getPresentEmployees().isEmpty()){
      attendanceRepository.delete(attendance);
      return ResponseEntity.ok(attendanceDetails);
    }

    Attendance updateAttendance = attendanceRepository.save(attendance);
    return ResponseEntity.ok(updateAttendance);
  }

  // Delete Attendance Records by ID
  @DeleteMapping("/attendance/{id}")
  public Map<String, Boolean> deleteAttendance(@PathVariable Long id) {
    Attendance attendance = attendanceRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Attendance Record does not exist with id:" + id));

    attendanceRepository.delete(attendance);
    Map<String, Boolean> response = new HashMap<>();
    response.put("Deleted", Boolean.TRUE);
    return response;
  }

  // Delete Attendance Records by Date
  @DeleteMapping("/attendance/date/{date}")
  public Map<String, Boolean> deleteAttendance(@PathVariable String date) {
    Attendance attendance = attendanceRepository.findByDate(LocalDate.parse(date))
        .orElseThrow(() -> new ResourceNotFoundException("Attendance Record does not exist with date:" + date));

    attendanceRepository.delete(attendance);
    Map<String, Boolean> response = new HashMap<>();
    response.put("Deleted", Boolean.TRUE);
    return response;
  }


  // Mark Employee Attendance Status by Attendance Date
  @PutMapping("/attendance/{attendanceDate}/employees")
  public ResponseEntity<Attendance> markEmployeeAttendanceStatus(@PathVariable String attendanceDate, @RequestBody Map<String, Object> data) {
    long employeeId = (long) data.getOrDefault("employeeId", -1);
    boolean isPresent = ((int) data.getOrDefault("employeeId", 0)) == 1;

    Attendance attendance = attendanceRepository.findByDate(LocalDate.parse(attendanceDate))
        .orElse(new Attendance(0, LocalDate.parse(attendanceDate), null));

    Employee employee = employeeRepository.findById(employeeId)
        .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id:" + employeeId));

    boolean marked = isPresent ? attendance.markEmployeeAsPresent(employee) : attendance.markEmployeeAsAbsent(employee);
    Attendance updatedAttendance = attendanceRepository.save(attendance);
    if (marked)
      return ResponseEntity.ok(updatedAttendance);
    else
      return ResponseEntity.badRequest().body(updatedAttendance);
  }
}
