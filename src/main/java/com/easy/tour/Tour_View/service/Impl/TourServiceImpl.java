package com.easy.tour.Tour_View.service.Impl;

import com.easy.tour.Tour_View.dto.DestinationDTO;
import com.easy.tour.Tour_View.service.TourService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.ArrayList;
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
}
