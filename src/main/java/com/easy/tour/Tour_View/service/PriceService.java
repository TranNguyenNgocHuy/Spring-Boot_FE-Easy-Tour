package com.easy.tour.Tour_View.service;

import com.easy.tour.Tour_View.dto.PriceDTO;

import java.math.BigDecimal;

public interface PriceService extends BaseService<PriceDTO>{

    public BigDecimal calculateProfitAdult(PriceDTO priceDTO);

    public BigDecimal calculateAllocationCost(PriceDTO priceDTO);

    public BigDecimal calculateIndividualCost(PriceDTO priceDTO);
}
