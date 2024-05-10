package com.easy.tour.Tour_View.controller;

import com.easy.tour.Tour_View.consts.ApiPath;
import com.easy.tour.Tour_View.consts.UrlPath;
import com.easy.tour.Tour_View.dto.TourRequestDTO;
import com.easy.tour.Tour_View.response.ResponseDTO;
import com.easy.tour.Tour_View.response.TourRequestResponseDTO;
import com.easy.tour.Tour_View.utils.RestTemplateUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
public class TourRequestController {
    @Autowired
    RestTemplateUtils restTemplateUtils;

    @GetMapping(value = UrlPath.TOUR_CREATE_REQUEST_PAGE)
    public String showCreateTourRequestPage(Model model) {
        TourRequestDTO tourRequestDTO = new TourRequestDTO();

        model.addAttribute("activeNav", "tour");
        model.addAttribute("activeTab", "createRequest");
        model.addAttribute("tourRequestDTO", tourRequestDTO);
        return "tour/tourCreateRequest";
    }

    @PostMapping(value = UrlPath.TOUR_CREATE_REQUEST_PAGE, params = "action")
    public String createTourRequest(HttpServletRequest request,
                                    @RequestParam(value="action", required = true) String action,
                                    @Valid @ModelAttribute("tourRequestDTO") TourRequestDTO tourRequestDTO,
                                    BindingResult result
    ) {
        System.out.println(tourRequestDTO);
        // Send data to BE and receive response
        TourRequestResponseDTO response = restTemplateUtils.postData(tourRequestDTO, ApiPath.TOUR_REQUEST_CREATE, request, TourRequestResponseDTO.class);
        log.info("message: {}", response.getMessage());
        return "redirect:" + UrlPath.TOUR_VIEW_REQUEST_PAGE;
    }


    @GetMapping(value = UrlPath.TOUR_VIEW_REQUEST_PAGE)
    public String viewAllTourRequest(Model model,
                                     HttpServletRequest request
    ) {
        TourRequestResponseDTO response = restTemplateUtils.getData(ApiPath.TOUR_REQUEST_GET_All, request, TourRequestResponseDTO.class);
        List<TourRequestDTO> list = response.getList();
        model.addAttribute("activeNav", "tour");
        model.addAttribute("activeTab", "viewRequest");
        model.addAttribute("tourRequestList", list);
        return "tour/tourViewRequest";
    }


    @GetMapping(value = UrlPath.TOUR_EDIT_REQUEST_PAGE)
    public String tourRequestEditPage(
            @RequestParam("uuid") String uuid,
            Model model,
            HttpServletRequest request
    ) {
        TourRequestResponseDTO response = restTemplateUtils.getData(ApiPath.TOUR_REQUEST_GET_BY_ID + uuid, request, TourRequestResponseDTO.class);
        model.addAttribute("tourRequestDTO",  response.getData());
        return "tour/tourRequestEdit";
    }

    @PostMapping(value = UrlPath.TOUR_EDIT_REQUEST_PAGE)
    public String updateTourRequest(HttpServletRequest request,
                                    @RequestParam(value="action", required = true) String action,
                                    @RequestParam("uuid") String uuid,
                                    @Valid @ModelAttribute("tourDetailDto") TourRequestDTO tourRequestDTO,
                                    BindingResult result
    ) {
        TourRequestResponseDTO response = restTemplateUtils.putData(tourRequestDTO, ApiPath.TOUR_REQUEST_UPDATE + uuid, request, TourRequestResponseDTO.class);
        return "redirect:" + UrlPath.TOUR_VIEW_REQUEST_PAGE;
    }

    @GetMapping(value = UrlPath.TOUR_DELETE_REQUEST)
    public String deleteTourRequest(@RequestParam("uuid") String uuid,
                                    HttpServletRequest request) {
        TourRequestResponseDTO response = restTemplateUtils.deleteData(uuid, ApiPath.TOUR_REQUEST_DELETE + uuid, request, TourRequestResponseDTO.class);
        return "redirect:" + UrlPath.TOUR_VIEW_REQUEST_PAGE;
    }
}
