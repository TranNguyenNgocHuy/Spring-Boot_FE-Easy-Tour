package com.easy.tour.Tour_View.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
@Data
public class AuthenticationDTO extends BaseObject {
    @NotEmpty(message = "You must enter Email field.")
    private String email;

    @NotEmpty(message = "You must enter password field.")
    private String password;
}
