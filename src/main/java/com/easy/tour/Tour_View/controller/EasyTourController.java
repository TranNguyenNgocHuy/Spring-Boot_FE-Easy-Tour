package com.easy.tour.Tour_View.controller;


import com.easy.tour.Tour_View.consts.ApiPath;
import com.easy.tour.Tour_View.response.PriceResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@Slf4j
public class EasyTourController {

    @Autowired
    RestTemplate restTemplate;


    /* PING */
    @GetMapping(value = ApiPath.PING)
    public String ping() {
        return "start easy tour!!!!";
    }
    ///////////////////////////////////


    /* DASH BOARD */
    @GetMapping(value = ApiPath.API_BASE_URL)
    public String show() {
        return "dashboard";
    }

    @GetMapping(value = ApiPath.DASH_BOARD)
    public String homePage() {
        return "dashboard";
    }
    //////////////////////////////////////



    /* USER */
    //////////////////////////////////////


    /* TOUR */
    //////////////////////////////////////



    /* PRICE */
    @GetMapping(value = ApiPath.PRICE_NAV_PAGE)
    public String priceNav() {
        return "price/priceNav";
    }

    @GetMapping(value = ApiPath.PRICE_CREATE_PAGE)
    public String priceCreate() {
        return "price/priceCreate";
    }

//    ApiPath.PRICE_VIEW_ALL_PAGE
    @GetMapping(value = ApiPath.PRICE_VIEW_ALL_PAGE)
    public String getAllPriceList(Model model) {
        ResponseEntity<PriceResponseDTO> response = restTemplate.getForEntity(ApiPath.PRICE_GET_All, PriceResponseDTO.class);
        System.out.println(response.getBody().getList());
        model.addAttribute("priceDtoList", response.getBody().getList());
        return "price/priceViewAll";
    }

    @GetMapping(value = ApiPath.PRICE_APPROVE_PAGE)
    public String priceView() {
        return "price/priceApprove";
    }
    //////////////////////////////////////////

}
