package com.easy.tour.Tour_View.dto;


import com.easy.tour.Tour_View.Enum.ApprovalStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class PriceDTO extends BaseObject {

    private Long priceId;

    @NotEmpty(message = "You must choose Tour Code.")
    private String tourCode;

    private ApprovalStatus approvalStatus;

    @Min(value = 0, message = "The value must be >= 0.")
    @NotNull(message = "The value must be numeric.")
    private BigDecimal coach;

    @Min(value = 0, message = "The value must be >= 0.")
    @NotNull(message = "The value must be numeric.")
    private BigDecimal mainGuider;

    @Min(value = 0, message = "The value must be >= 0.")
    @NotNull(message = "The value must be numeric.")
    private BigDecimal localGuider;

    @Min(value = 0, message = "The value must be >= 0.")
    @NotNull(message = "The value must be numeric.")
    private BigDecimal airTicket;

    @Min(value = 0, message = "The value must be >= 0.")
    @NotNull(message = "The value must be numeric.")
    private BigDecimal food;

    @Min(value = 0, message = "The value must be >= 0.")
    @NotNull(message = "The value must be numeric.")
    private BigDecimal attraction;

    @Min(value = 0, message = "The value must be >= 0.")
    @NotNull(message = "The value must be numeric.")
    private BigDecimal hotel;

    @Min(value = 0, message = "The value must be >= 0.")
    @NotNull(message = "The value must be numeric.")
    private BigDecimal insurance;

    @Min(value = 0, message = "The value must be >= 0.")
    @NotNull(message = "The value must be numeric.")
    private BigDecimal tax;

    @Min(value = 0, message = "The value must be >= 0.")
    @NotNull(message = "The value must be numeric.")
    private BigDecimal otherPrice;

    @Min(value = 0, message = "The value must be >= 0.")
    @NotNull(message = "The value must be numeric.")
    private BigDecimal visaFee;

    @Min(value = 1, message = "The value must be >= 1.")
    @NotNull(message = "The value must be numeric.")
    private BigDecimal adult;

    @Min(value = 1, message = "The value must be >= 1.")
    @NotNull(message = "The value must be numeric.")
    private BigDecimal children;

    private String creator;

    private String createDate;

    private String modifiedDate;

    private String modifiedBy;

    private String approvedBy;

    private String approvalDate;
}
