package com.easy.tour.Tour_View.service;

public interface TourService {
    public String generateTourCode(String tourName);

    //Create for testing
    // public static void main(String[] args) {
    // TourServiceImpl tourService = new TourServiceImpl();
    // System.out.println(tourService.generateTourCode("Ha Noi 3 ngay 2 dem"));
    // }
    String normalizeString(String str);
}
