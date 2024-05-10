package com.easy.tour.Tour_View.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;
import java.util.Set;


@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
@Data
public class UserDTO extends BaseObject {

    @NotEmpty(message = "You must enter Last Name field.")
    private String lastName;

    @NotEmpty(message = "You must enter First Name field.")
    private String firstName;

    @NotEmpty(message = "You must enter Email field.")
    private String email;

    private String password;

    private Boolean gender;

    private Integer phoneNumber;

    private Set<String> roles;

    private Date createdDate;

    private Date modifiedDate;

    private String createdBy;

    private String modifiedBy;
}
