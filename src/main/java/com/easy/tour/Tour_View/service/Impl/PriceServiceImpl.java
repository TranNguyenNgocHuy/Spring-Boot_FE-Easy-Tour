package com.easy.tour.Tour_View.service.Impl;

import com.easy.tour.Tour_View.dto.PriceDTO;
import com.easy.tour.Tour_View.service.PriceService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@Slf4j
public class PriceServiceImpl implements PriceService {

    public BigDecimal calculateProfitAdult(PriceDTO priceDTO) {
        BigDecimal allocationCost = calculateAllocationCost(priceDTO);
        BigDecimal individualCost = calculateIndividualCost(priceDTO);
        BigDecimal profit = priceDTO.getAdult().subtract(allocationCost.add(individualCost));

        // percentProfit = [(Revenue - cost) / Revenue] * 100
        BigDecimal percentProfit =
                (profit.divide(priceDTO.getAdult(), 2, RoundingMode.HALF_UP))
                        .multiply(BigDecimal.valueOf(100));
        return percentProfit;
    }

    @Override
    public BigDecimal calculateAllocationCost(PriceDTO priceDTO) {
        //(suppose total tour size = 20 slot)
        // group fee of one person = Total group fee / 20
        BigDecimal totalGroupFee = priceDTO.getCoach().add(priceDTO.getMainGuider()).add(priceDTO.getLocalGuider());

        if (totalGroupFee.compareTo(BigDecimal.ZERO) == 0) {
            return  BigDecimal.ZERO;
        } else {
            // group fee of one person = Total group fee / 20
            BigDecimal groupFee = totalGroupFee.divide(BigDecimal.valueOf(20));
            return groupFee;
        }
    }

    @Override
    public BigDecimal calculateIndividualCost(PriceDTO priceDTO) {
        // Personal fee
        BigDecimal personalFee = priceDTO.getAirTicket().add(priceDTO.getFood()).add(priceDTO.getAttraction())
                .add(priceDTO.getInsurance()).add(priceDTO.getHotel()).add(priceDTO.getTax())
                .add(priceDTO.getOtherPrice()).add(priceDTO.getVisaFee());
        return personalFee;
    }
}
