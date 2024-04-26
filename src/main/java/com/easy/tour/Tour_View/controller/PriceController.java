package com.easy.tour.Tour_View.controller;

import com.easy.tour.Tour_View.consts.ApiPath;
import com.easy.tour.Tour_View.consts.UrlPath;
import com.easy.tour.Tour_View.dto.PriceDTO;
import com.easy.tour.Tour_View.response.PriceResponseDTO;
import com.easy.tour.Tour_View.service.PriceService;
import com.easy.tour.Tour_View.utils.RestTemplateUtils;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@Slf4j
public class PriceController {

    @Autowired
    RestTemplateUtils restTemplateUtils;

    @Autowired
    PriceService service;

    @GetMapping(value = UrlPath.PRICE_NAV_PAGE)
    public String priceNav(Model model) {

        model.addAttribute("activeNav", "price");
        return "price/priceNav";
    }


    @GetMapping(value = UrlPath.PRICE_CREATE_PAGE)
    public String priceCreatePage(Model model) {
        PriceDTO priceDTO = new PriceDTO();

        model.addAttribute("activeNav", "price");
        model.addAttribute("activeTab", "priceCreate");
        model.addAttribute("priceDto", priceDTO);
        return "price/priceCreate";
    }

    @GetMapping(value = UrlPath.PRICE_VIEW_ALL_PAGE)
    public String viewPricePage(Model model) {
        // Send request to take data from URL with header = null
        PriceResponseDTO response = restTemplateUtils.getData(ApiPath.PRICE_GET_All, PriceResponseDTO.class);

        model.addAttribute("activeNav", "price");
        model.addAttribute("activeTab", "priceView");
        model.addAttribute("priceDtoList", response.getList());
        return "price/priceViewAll";
    }

    @GetMapping(value = UrlPath.PRICE_APPROVE_PAGE)
    public String priceApprove(Model model) {

        model.addAttribute("activeNav", "price");
        model.addAttribute("activeTab", "priceApprove");
        return "price/priceApprove";
    }


    @PostMapping(value = UrlPath.PRICE_CREATE_PAGE, params = "action")
    public String priceCreateSubmit(
            @RequestParam(value="action", required = true) String action,
            Model model,
            @Valid @ModelAttribute("priceDto") PriceDTO priceDto,
            BindingResult result) {

        boolean showProfit = false;
        if (action.equals("cancel")) {
            return "redirect:" + UrlPath.PRICE_VIEW_ALL_PAGE;
        }

        if (result.hasErrors()) {
            return "price/priceCreate";
        }

        // submit calculate
        if(action.equals("calculate")) {
            BigDecimal allocationCost = service.calculateAllocationCost(priceDto);
            BigDecimal individualCost = service.calculateIndividualCost(priceDto);
            BigDecimal percentProfit = service.calculateProfitAdult(priceDto);

            if (percentProfit != null) {
                showProfit = true;
            }

            model.addAttribute("allocationCost", allocationCost);
            model.addAttribute("individualCost", individualCost);
            model.addAttribute("showProfit", showProfit);
            model.addAttribute("percentProfit", "Profit: " + percentProfit + "%");
            model.addAttribute("priceDto", priceDto);
            return "price/priceCreate";
        }

        // submit create price
        if (action.equals("create")) {
            // Create HttpEntity with data = objectDto
            HttpEntity<?> requestEntity = restTemplateUtils.setHeaderDefault(priceDto);

            // Send data to BE and receive response
            PriceResponseDTO response = restTemplateUtils.postData(requestEntity, ApiPath.PRICE_CREATE, PriceResponseDTO.class);
            log.info("message: {}", response.getMessage());
        }
        return "redirect:" + UrlPath.PRICE_VIEW_ALL_PAGE;
    }


    @GetMapping(value = UrlPath.PRICE_PREVIEW)
    public String pricePreview(Model model,
                               @RequestParam("tourCode") String tourCode
                               ) {
        PriceResponseDTO response = restTemplateUtils.getData(ApiPath.PRICE_GET_BY_TOUR_CODE + tourCode, PriceResponseDTO.class);
        PriceDTO priceDto = response.getData();

        BigDecimal allocationCost = service.calculateAllocationCost(priceDto);
        BigDecimal individualCost = service.calculateIndividualCost(priceDto);
        BigDecimal percentProfit = service.calculateProfitAdult(priceDto);

        model.addAttribute("allocationCost", allocationCost);
        model.addAttribute("individualCost", individualCost);
        model.addAttribute("percentProfit", "Profit: " + percentProfit + "%");
        model.addAttribute("priceDto", priceDto);
        return "price/pricePreview";
    }

    @GetMapping(value = UrlPath.PRICE_EDIT)
    public String priceEditPage(Model model,
                                @RequestParam("tourCode") String tourCode
                            ) {
        PriceResponseDTO response = restTemplateUtils.getData(ApiPath.PRICE_GET_BY_TOUR_CODE + tourCode, PriceResponseDTO.class);
        PriceDTO priceDto = response.getData();

        BigDecimal allocationCost = service.calculateAllocationCost(priceDto);
        BigDecimal individualCost = service.calculateIndividualCost(priceDto);
        BigDecimal percentProfit = service.calculateProfitAdult(priceDto);

        model.addAttribute("allocationCost", allocationCost);
        model.addAttribute("individualCost", individualCost);
        model.addAttribute("percentProfit", "Profit: " + percentProfit + "%");
        model.addAttribute("priceDto", priceDto);
        return "price/priceEdit";
    }

    @PostMapping(value = UrlPath.PRICE_EDIT, params = "action")
    public String priceSubmitEdit(
            @RequestParam(value="action", required = true) String action,
            Model model,
            @Valid @ModelAttribute("priceDto") PriceDTO priceDto,
            BindingResult result
            ) {

        // submit delete
        if (action.equals("cancel")) {
            return "redirect:" + UrlPath.PRICE_VIEW_ALL_PAGE;
        }

        // Binding result
        if (result.hasErrors()) {
            return "price/priceEdit";
        }

        // submit calculate
        if(action.equals("calculate")) {
            BigDecimal allocationCost = service.calculateAllocationCost(priceDto);
            BigDecimal individualCost = service.calculateIndividualCost(priceDto);
            BigDecimal percentProfit = service.calculateProfitAdult(priceDto);


            model.addAttribute("allocationCost", allocationCost);
            model.addAttribute("individualCost", individualCost);
            model.addAttribute("percentProfit", "Profit: " + percentProfit + "%");
            model.addAttribute("priceDto", priceDto);
            return "price/priceEdit";
        }

        // submit update price
        if (action.equals("update")) {
            // Create HttpEntity with data = objectDto
            HttpEntity<?> requestEntity = restTemplateUtils.setHeaderDefault(priceDto);

            // Send data to BE and receive response
            PriceResponseDTO response = restTemplateUtils.putData(requestEntity, ApiPath.PRICE_UPDATE + priceDto.getTourCode(), PriceResponseDTO.class);
            log.info("message: {}", response.getMessage());
        }
        return "redirect:" + UrlPath.PRICE_VIEW_ALL_PAGE;
    }


//    @GetMapping(value = UrlPath.PRICE_DELETE)
//    public String deletePrice(
//            @RequestParam("tourCode") String tourCode
//    ) {
//
//            // Create HttpEntity with data = objectDto
//            HttpEntity<?> requestEntity = restTemplateUtils.setHeaderDefault(tourCode);
//
//            // Delete price
//            PriceResponseDTO responseDTO = restTemplateUtils.deleteData(requestEntity, ApiPath.PRICE_DELETE + tourCode, PriceResponseDTO.class);
//            log.info("message: {}", responseDTO.getMessage());
//
//            return "redirect:" + ApiPath.PRICE_VIEW_ALL_PAGE;
//    }

//    @DeleteMapping(value = ApiPath.PRICE_EDIT_DELETE, params = "action")
//    public String deletePrice(
//            Model model,
//            @RequestParam(value="action", required = true) String action,
//            @RequestParam("tourCode") String tourCode,
//            @ModelAttribute("priceDto") PriceDTO priceDto
//        ) {
//
//        log.info("action: {}", action);
//        log.info("tourCode: {}", tourCode);
//        log.info("dto: {}", priceDto);
//
//
//
//        BigDecimal allocationCost = service.calculateAllocationCost(priceDto);
//        BigDecimal individualCost = service.calculateIndividualCost(priceDto);
//        BigDecimal percentProfit = service.calculateProfitAdult(priceDto);
//
//
//        model.addAttribute("allocationCost", allocationCost);
//        model.addAttribute("individualCost", individualCost);
//        model.addAttribute("percentProfit", "Profit: " + percentProfit + "%");
//        model.addAttribute("priceDto", priceDto);
//
//        return "price/priceEdit";
//    }
}
