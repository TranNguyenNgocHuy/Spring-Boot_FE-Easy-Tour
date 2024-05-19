package com.easy.tour.Tour_View.service.Impl;

import com.easy.tour.Tour_View.dto.DepartureDateDTO;
import com.easy.tour.Tour_View.service.DepartureDateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DepartureDateServiceImpl implements DepartureDateService {

    @Override
    public Page<DepartureDateDTO> convertTourListToPage(List<DepartureDateDTO> departureDateDTOList, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), departureDateDTOList.size());

        List<DepartureDateDTO> pageContent = departureDateDTOList.subList(start, end);

        return new PageImpl<>(pageContent, pageable, departureDateDTOList.size());
    }
}
