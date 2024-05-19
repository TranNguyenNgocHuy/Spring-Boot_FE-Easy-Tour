package com.easy.tour.Tour_View.service;

import com.easy.tour.Tour_View.dto.DepartureDateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartureDateService {
    Page<DepartureDateDTO> convertTourListToPage(List<DepartureDateDTO> departureDateDTOList, Pageable pageable);
}
