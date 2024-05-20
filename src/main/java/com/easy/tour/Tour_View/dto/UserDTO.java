package com.easy.tour.Tour_View.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
@Data
public class UserDTO extends BaseObject {

    private String uuid;

    @NotEmpty(message = "You must enter Last Name field.")
    private String lastName;

    @NotEmpty(message = "You must enter First Name field.")
    private String firstName;

    @NotEmpty(message = "You must enter Email field.")
    private String email;

    private String password;

    private Boolean gender;

    private Integer phoneNumber;

    private String avatarImg;

    @NotEmpty(message = "You must choose a role.")
    private String role;

    private Set<String> roles = new HashSet<>();

    private Date createdDate;

    private Date modifiedDate;

    private String createdBy;

    private String modifiedBy;
}
