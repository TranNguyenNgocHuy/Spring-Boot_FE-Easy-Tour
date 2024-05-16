package com.easy.tour.Tour_View.controller;

import com.easy.tour.Tour_View.consts.ApiPath;
import com.easy.tour.Tour_View.consts.UrlPath;
import com.easy.tour.Tour_View.dto.DepartureDateDTO;
import com.easy.tour.Tour_View.dto.PriceDTO;
import com.easy.tour.Tour_View.dto.TourDTO;
import com.easy.tour.Tour_View.dto.TourRequestDTO;
import com.easy.tour.Tour_View.response.DepartureDateResponseDTO;
import com.easy.tour.Tour_View.response.PriceResponseDTO;
import com.easy.tour.Tour_View.response.ResponseDTO;
import com.easy.tour.Tour_View.response.TourResponseDTO;
import com.easy.tour.Tour_View.service.TourService;
import com.easy.tour.Tour_View.utils.RestTemplateUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

import java.math.BigDecimal;
import java.util.List;

@Controller
@Slf4j
public class TourController {

    @Autowired
    RestTemplateUtils restTemplateUtils;

    @Autowired
    TourService tourService;

    String tourCodeInput = null;
    String tourRequestCodeInput = null;


    //Showcase nav bar
    @GetMapping(value = UrlPath.TOUR_NAV_PAGE)
    public String tourNav(Model model) {
        model.addAttribute("activeNav", "tour");
        return "tour/tourNav";
    }

    @GetMapping(value = UrlPath.TOUR_CREATE_PAGE)
    public String tourCreate(Model model) {
        TourDTO tourDto = new TourDTO();
        model.addAttribute("activeNav", "tour");
        model.addAttribute("activeTab", "tourCreate");
        model.addAttribute("tourDto", tourDto);
        return "tour/tourCreate";
    }

    @PostMapping(value = UrlPath.TOUR_CREATE_PAGE, params = "action")
    public String tourCreate(Model model,
                             HttpServletRequest request,
                             @RequestParam(value = "action", required = true)
                             String action,
                             @Valid @ModelAttribute("tourDto") TourDTO tourDto,
                             BindingResult result) {


        if (action.equals("cancel")) {
            return "redirect:" + UrlPath.TOUR_VIEW_ALL_PAGE;
        }

        if (result.hasErrors()) {
            return "tour/tourCreate";
        }

        if (action.equals("create")) {

            TourResponseDTO response = restTemplateUtils.postData(tourDto, ApiPath.TOUR_CREATE, request, TourResponseDTO.class);
            log.info("message: {}", response.getMessage());
        }
        return "redirect:" + UrlPath.TOUR_VIEW_ALL_PAGE;
    }

    @GetMapping(value = UrlPath.TOUR_VIEW_ALL_PAGE)
    public String tourView(Model model,
                           HttpServletRequest request
    ) {
        TourResponseDTO response = restTemplateUtils.getData(ApiPath.TOUR_GET_All, request, TourResponseDTO.class);
        model.addAttribute("activeNav", "tour");
        model.addAttribute("activeTab", "viewTour");
        model.addAttribute("tourDtoList", response.getList());
        return "tour/tourView";
    }

    @GetMapping(value = UrlPath.TOUR_APPROVE_PAGE)
    public String tourApprove(Model model) {

        model.addAttribute("activeNav", "tour");
        model.addAttribute("activeTab", "approveTour");
        return "tour/tourApprove";
    }


    @GetMapping
    public String getTourForm(Model model) {
        model.addAttribute("tour", new TourDTO());
        return "tour-form";
    }

    @PostMapping
    public String addTour(@ModelAttribute("tour") TourDTO tour) {
        return "redirect:/tours";
    }

    @GetMapping(value = UrlPath.TOUR_EDIT)
    public String tourEditPage(Model model,
                               HttpServletRequest request,
                               @RequestParam("tourCode") String tourCode
    ) {
        TourResponseDTO response = restTemplateUtils.getData(ApiPath.TOUR_GET_BY_TOUR_CODE + tourCode, request, TourResponseDTO.class);
        TourDTO tourDto = response.getData();

        model.addAttribute("tourDto", tourDto);
        return "tour/tourEdit";
    }

    @PostMapping(value = UrlPath.TOUR_EDIT, params = "action")
    public String tourSubmitEdit(
            @RequestParam(value = "action", required = true) String action,
            Model model,
            HttpServletRequest request,
            @Valid @ModelAttribute("tourDto") TourDTO tourDto,
            BindingResult result
    ) {

        // submit delete
        if (action.equals("cancel")) {
            return "redirect:" + UrlPath.TOUR_VIEW_ALL_PAGE;
        }

        // Binding result
        if (result.hasErrors()) {
            return "tour/tourEdit";
        }

        // submit calculate
        if (action.equals("update")) {

            // Send data to BE and receive response
            TourResponseDTO response = restTemplateUtils.putData(tourDto, ApiPath.TOUR_UPDATE + tourDto.getTourCode(), request, TourResponseDTO.class);
            log.info("message: {}", response.getMessage());
        }
        return "redirect:" + UrlPath.TOUR_VIEW_ALL_PAGE;
    }

    @GetMapping(value = UrlPath.DEPARTURE_DATE_CREATE_PAGE)
    public String departureDateView(Model model,
                                    HttpServletRequest request,
                                    HttpSession session
    ) {
        DepartureDateDTO departureDateDto = new DepartureDateDTO();
        ResponseDTO<String> response = restTemplateUtils.getData(ApiPath.TOUR_ONLY_GET_ALL, request, ResponseDTO.class);
        session.setAttribute("tourCodeList", response.getList());

        model.addAttribute("activeNav", "tour");
        model.addAttribute("activeTab", "departureDate");
        model.addAttribute("tourCodeList", response.getList());
        model.addAttribute("departureDateDto", departureDateDto);
        return "departuredate/departureDateCreate";
    }

    @PostMapping(value = UrlPath.DEPARTURE_DATE_CREATE_PAGE, params = "action")
    public String departureDateCreateSubmit(@RequestParam(value="action", required = true) String action,
                                    Model model,
                                    HttpServletRequest request,
                                    HttpSession session,
                                    @Valid @ModelAttribute("departureDateDto") DepartureDateDTO departureDateDto,
                                    BindingResult result
    ) {
        if (action.equals("cancel")) {
            return "redirect:" + UrlPath.TOUR_VIEW_ALL_PAGE;
        }

        if (result.hasErrors()) {
            List<String> tourCodeList = (List<String>) session.getAttribute("tourCodeList");
            model.addAttribute("tourCodeList", tourCodeList);
            return "departuredate/departureDateCreate";
        }

        // submit create price
        if (action.equals("create")) {
            DepartureDateResponseDTO response = restTemplateUtils.postData(departureDateDto, ApiPath.DEPARTURE_DATE_CREATE, request, DepartureDateResponseDTO.class);
            log.info("message: {}", response.getMessage());
        }
        return "redirect:" + UrlPath.DEPARTURE_DATE_CREATE_PAGE;
    }

}
