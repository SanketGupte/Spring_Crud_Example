package com.app.sanket.ws.Spring_Crud_Example.Controller;

import com.app.sanket.ws.Spring_Crud_Example.Entity.EmployeeEntity;
import com.app.sanket.ws.Spring_Crud_Example.Exceptions.ResourceNotFoundException;
import com.app.sanket.ws.Spring_Crud_Example.Repository.EmployeeRepository;
import com.app.sanket.ws.Spring_Crud_Example.Services.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("/employee")
    public List<EmployeeEntity> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable(value = "id") Long employeeId,
                                                          @Valid @RequestBody EmployeeEntity employeeDetails) throws ResourceNotFoundException {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        employeeEntity.setEmailId(employeeDetails.getEmailId());
        employeeEntity.setLastname(employeeDetails.getLastname());
        employeeEntity.setFirstname(employeeDetails.getFirstname());
        final  EmployeeEntity entity = employeeRepository.save(employeeEntity);
        return ResponseEntity.ok(entity);
    }

    @PostMapping("/employees")
    public EmployeeEntity createEmployee(@Valid @RequestBody EmployeeEntity employee) {
        employee.setId(sequenceGeneratorService.generateSequence(EmployeeEntity.SEQUENCE_NAME));
        return employeeRepository.save(employee);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<EmployeeEntity> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                         @Valid @RequestBody EmployeeEntity employeeDetails) throws ResourceNotFoundException {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employeeEntity.setEmailId(employeeDetails.getEmailId());
        employeeEntity.setLastname(employeeDetails.getLastname());
        employeeEntity.setFirstname(employeeDetails.getFirstname());
        final EmployeeEntity updatedEmployee = employeeRepository.save(employeeEntity);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/employee/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value="id") Long employeeId)
            throws ResourceNotFoundException{
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not Found for the id :: " + employeeId));

        employeeRepository.delete(employeeEntity);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
