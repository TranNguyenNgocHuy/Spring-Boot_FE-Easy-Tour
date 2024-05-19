package com.easy.tour.Tour_View.service.Impl;

import com.easy.tour.Tour_View.dto.TourDTO;
import com.easy.tour.Tour_View.service.TourService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Slf4j
public class TourServiceImpl implements TourService {
    @Override
    public String normalizeString(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

    @Override
    public Page<TourDTO> convertTourListToPage(List<TourDTO> tourDTOList, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), tourDTOList.size());

        List<TourDTO> pageContent = tourDTOList.subList(start, end);

        return new PageImpl<>(pageContent, pageable, tourDTOList.size());
    }
}
