package com.easy.tour.Tour_View.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
@Data
public class ProfileChangePasswordDTO {
    @NotEmpty(message = "You must enter your password field.")
    private String password;

    @NotEmpty(message = "You must enter new password field.")
    private String newPassword;

    @NotEmpty(message = "You must enter confirm new password field.")
    private String confirmNewPassword;

    private String uuid;
}
