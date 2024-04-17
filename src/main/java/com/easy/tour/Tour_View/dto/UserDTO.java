package com.easy.tour.Tour_View.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;



@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
@Data
public class UserDTO extends BaseObject {

    private String lastName;

    private String firstName;

    private String email;

    private String password;

    private int roleName; // (1 admin, 2 user, 3 manager)
}
