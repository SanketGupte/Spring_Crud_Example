package com.app.sanket.ws.Spring_Crud_Example.Repository;

import com.app.sanket.ws.Spring_Crud_Example.Entity.EmployeeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<EmployeeEntity, Long> {
}
