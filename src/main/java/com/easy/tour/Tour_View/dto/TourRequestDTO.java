package com.easy.tour.Tour_View.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
@Getter
@Setter
@Data
public class TourRequestDTO extends BaseObject {
    private UUID uuid;

    @NotEmpty(message = "Description cannot be empty.")
    private String description;

    private String createdBy;

    private Date createdDate;
}
