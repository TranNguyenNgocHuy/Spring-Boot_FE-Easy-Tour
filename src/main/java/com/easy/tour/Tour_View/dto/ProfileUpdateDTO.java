package com.easy.tour.Tour_View.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
@Data
public class ProfileUpdateDTO extends BaseObject {
    @NotEmpty(message = "You must enter Last Name field.")
    private String lastName;

    @NotEmpty(message = "You must enter First Name field.")
    private String firstName;

    private Boolean gender;

    private Integer phoneNumber;

    private String uuid;

    private String avatarImg;
}
