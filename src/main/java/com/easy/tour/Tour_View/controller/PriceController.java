package com.easy.tour.Tour_View.controller;

import com.easy.tour.Tour_View.consts.ApiPath;
import com.easy.tour.Tour_View.consts.UrlPath;
import com.easy.tour.Tour_View.dto.PriceDTO;
import com.easy.tour.Tour_View.response.PriceResponseDTO;
import com.easy.tour.Tour_View.service.PriceService;
import com.easy.tour.Tour_View.utils.ParserDateTimeUtils;
import com.easy.tour.Tour_View.utils.RestTemplateUtils;
import jakarta.servlet.http.HttpServletRequest;
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
    ParserDateTimeUtils parserDateTime;

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
    public String viewPricePage(Model model,
                                HttpServletRequest request
    ) {
        // Send request to take data from URL with header = null
        PriceResponseDTO response = restTemplateUtils.getData(ApiPath.PRICE_GET_All, request, PriceResponseDTO.class);

        for (PriceDTO priceDTO: response.getList()) {
           priceDTO.setCreateDate(parserDateTime.format(priceDTO.getCreateDate()));
           priceDTO.setModifiedDate(parserDateTime.format((priceDTO.getModifiedDate())));
           priceDTO.setApprovalDate(parserDateTime.format((priceDTO.getApprovalDate())));
        }

        model.addAttribute("activeNav", "price");
        model.addAttribute("activeTab", "priceView");
        model.addAttribute("priceDtoList", response.getList());
        System.out.println(response.getList());
        return "price/priceViewAll";
    }

    @GetMapping(value = UrlPath.PRICE_APPROVE_PAGE)
    public String priceApprove(Model model) {

        model.addAttribute("activeNav", "price");
        model.addAttribute("activeTab", "priceApprove");
        return "price/priceApprove";
    }


    @PostMapping(value = UrlPath.PRICE_CREATE_PAGE, params = "action")
    public String priceCreateSubmit(@RequestParam(value="action", required = true) String action,
                                    Model model,
                                    HttpServletRequest request,
                                    @Valid @ModelAttribute("priceDto") PriceDTO priceDto,
                                    BindingResult result
    ) {
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
            // Send data to BE and receive response
            PriceResponseDTO response = restTemplateUtils.postData(priceDto, ApiPath.PRICE_CREATE, request, PriceResponseDTO.class);
            log.info("message: {}", response.getMessage());
        }
        return "redirect:" + UrlPath.PRICE_VIEW_ALL_PAGE;
    }


    @GetMapping(value = UrlPath.PRICE_PREVIEW)
    public String pricePreview(Model model,
                               HttpServletRequest request,
                               @RequestParam("tourCode") String tourCode
                               ) {
        PriceResponseDTO response = restTemplateUtils.getData(ApiPath.PRICE_GET_BY_TOUR_CODE + tourCode, request, PriceResponseDTO.class);
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
                                HttpServletRequest request,
                                @RequestParam("tourCode") String tourCode
                            ) {
        PriceResponseDTO response = restTemplateUtils.getData(ApiPath.PRICE_GET_BY_TOUR_CODE + tourCode, request, PriceResponseDTO.class);
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
    public String priceSubmitEdit(@RequestParam(value="action", required = true) String action,
                                  Model model,
                                  HttpServletRequest request,
                                  @Valid @ModelAttribute("priceDto") PriceDTO priceDto,
                                  BindingResult result
            ) {

        // Cancel Edit
        if (action.equals("cancel")) {
            return "redirect:" + UrlPath.PRICE_VIEW_ALL_PAGE;
        }

        // Binding result
        if (result.hasErrors()) {
            return "price/priceEdit";
        }

        // Calculate Edit
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
            // Send data to BE and receive response
            PriceResponseDTO response = restTemplateUtils.putData(priceDto, ApiPath.PRICE_UPDATE + priceDto.getTourCode(), request, PriceResponseDTO.class);
        }
        return "redirect:" + UrlPath.PRICE_VIEW_ALL_PAGE;
    }


    @GetMapping(value = UrlPath.PRICE_DELETE)
    public String deletePrice(
            @RequestParam("tourCode") String tourCode,
            HttpServletRequest request
    ) {
        PriceResponseDTO responseDTO = restTemplateUtils
                .deleteData(tourCode, ApiPath.PRICE_DELETE + tourCode, request, PriceResponseDTO.class);
        return "redirect:" + UrlPath.PRICE_VIEW_ALL_PAGE;
    }
}
