package com.app.sanket.ws.Spring_Crud_Example.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Employee")
public class EmployeeEntity {

    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";

    @Id
    private long id;

    @NotBlank
    @Size(max=100)
    @Indexed(unique = true)
    private String firstname;
    private String lastname;

    @NotBlank
    @Size(max=100)
    @Indexed(unique = true)
    private String emailId;

    public EmployeeEntity() {
    }

    public EmployeeEntity(String firstname, String lastname, String emailId) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailId = emailId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", emailId='" + emailId + '\'' +
                '}';
    }
}
