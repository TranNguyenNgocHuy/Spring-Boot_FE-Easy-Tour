package com.easy.tour.Tour_View.service.Impl;

import com.easy.tour.Tour_View.dto.DestinationDTO;
import com.easy.tour.Tour_View.service.TourService;
import com.easy.tour.Tour_View.utils.TourCodeGeneratorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TourServiceImpl implements TourService {
    TourCodeGeneratorUtils tourCodeGeneratorUtils = new TourCodeGeneratorUtils();
    @Override
    public String generateTourCode(String tourName) {
        String tourCode = null;
        List<DestinationDTO> destinations = new ArrayList<>();
        DestinationDTO hanoi = new DestinationDTO("1", "Ha Noi");
        DestinationDTO vungtau = new DestinationDTO("2", "Vung Tau");
        destinations.add(vungtau);
        destinations.add(hanoi);
        tourCode = tourCodeGeneratorUtils.createTourCode(tourCodeGeneratorUtils.destinationCheck(tourName, destinations));
        return tourCode;
    }

//Create for testing
// public static void main(String[] args) {
// TourServiceImpl tourService = new TourServiceImpl();
// System.out.println(tourService.generateTourCode("Ha Noi 3 ngay 2 dem"));
// }

}
