package com.easy.tour.Tour_View.service;

import com.easy.tour.Tour_View.dto.TourDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TourService {
    String normalizeString(String str);

    Page<TourDTO> convertTourListToPage(List<TourDTO> tourDTOList, Pageable pageable);
}
