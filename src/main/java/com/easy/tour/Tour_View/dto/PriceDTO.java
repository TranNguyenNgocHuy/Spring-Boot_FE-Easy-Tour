package com.easy.tour.Tour_View.dto;


import com.easy.tour.Tour_View.Enum.ApprovalStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
@Data
public class PriceDTO extends BaseObject {

    private Long priceId;
    private String tourCode;
    private String creator;
    private Date createDate;
    private String approvedBy;
    private Date approvalDate;
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING_OP;
    private BigDecimal Coach;
    private BigDecimal mainGuider;
    private BigDecimal localGuider;
    private BigDecimal airTicket;
    private BigDecimal food;
    private BigDecimal attraction;
    private BigDecimal hotel;
    private BigDecimal insurance;
    private BigDecimal tax;
    private BigDecimal otherPrice;
    private BigDecimal visaFee;
    private BigDecimal adult;
    private BigDecimal children;
}
