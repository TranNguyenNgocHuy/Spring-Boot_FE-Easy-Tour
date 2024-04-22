package com.easy.tour.Tour_View.controller;

import com.easy.tour.Tour_View.consts.ApiPath;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
@Slf4j
public class PriceController {

    @Autowired
    RestTemplateUtils restTemplateUtils;

    @Autowired
    PriceService service;

    @GetMapping(value = ApiPath.PRICE_NAV_PAGE)
    public String priceNav() {
        return "price/priceNav";
    }


    @GetMapping(value = ApiPath.PRICE_CREATE_PAGE)
    public String priceCreatePage(Model model) {
        PriceDTO priceDTO = new PriceDTO();
        model.addAttribute("priceDto", priceDTO);
        return "price/priceCreate";
    }

//    @PostMapping(value = ApiPath.PRICE_CREATE_PAGE, params = "calculate")
//    public String priceCalculate(
//            @Valid @ModelAttribute("priceDto") PriceDTO priceDto,
//            BindingResult result,
//            Model model
//    ) {
//        boolean showProfit = false;
//        if (result.hasErrors()) {
//            return "price/priceCreate";
//        }
//
//        BigDecimal percentProfit = service.calculateProfitAdult(priceDto);
//        if (percentProfit != null) {
//            showProfit = true;
//        }
//
//        model.addAttribute("showProfit", showProfit);
//        model.addAttribute("percentProfit", "Profit: " + percentProfit + "%");
//        model.addAttribute("priceDto", priceDto);
//        return "price/priceCreate";
//    }

    @PostMapping(value = ApiPath.PRICE_CREATE_PAGE, params = "action")
    public String priceCreateSubmit(
            @RequestParam(value="action", required = true) String action,
            Model model,
            @Valid @ModelAttribute("priceDto") PriceDTO priceDto,
            BindingResult result) {

        boolean showProfit = false;
        if (action.equals("cancel")) {
            return "redirect:" + ApiPath.PRICE_VIEW_ALL_PAGE;
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
        return "redirect:" + ApiPath.PRICE_VIEW_ALL_PAGE;
    }



    @GetMapping(value = ApiPath.PRICE_VIEW_ALL_PAGE)
    public String getAllPriceList(Model model) {
        // Send request to take data from URL with header = null
        PriceResponseDTO response = restTemplateUtils.getData(ApiPath.PRICE_GET_All, PriceResponseDTO.class);
        model.addAttribute("priceDtoList", response.getList());
        return "price/priceViewAll";
    }

    @GetMapping(value = ApiPath.PRICE_APPROVE_PAGE)
    public String priceView() {
        return "price/priceApprove";
    }
    //////////////////////////////////////////
}
