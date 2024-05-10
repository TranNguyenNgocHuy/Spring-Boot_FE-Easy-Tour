package com.easy.tour.Tour_View.utils;

import com.easy.tour.Tour_View.dto.DestinationDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TourCodeGeneratorUtils {
    public String destinationCheck(String tourName, List<DestinationDTO> destinationList) {
        String name = null;
        for (DestinationDTO destination : destinationList) {
            if (destinationCheck(tourName, destination)) {
                name = destination.getDestinationName();
            }
        }
        return name;
    }
    public boolean destinationCheck(String tourName, DestinationDTO destination) {
        List<Character> tourNameList = new ArrayList<>();
        List<Character> destinationNameList = new ArrayList<>();
        int i = 0;
        int j = 0;
        String destinationName = destination.getDestinationName();


        for (char c : tourName.toCharArray()) {
            if (c != ' ') {
                tourNameList.add(c);
            }
        }

        for (char c : destinationName.toCharArray()) {
            if (c != ' ') {
                destinationNameList.add(c);
            }
        }
        do {
            if (Character.toLowerCase(destinationNameList.get(j)) == Character.toLowerCase(tourNameList.get(i))) {
                j++;
            } else {
                j = 0;
            }
            i++;
        } while ((i != tourNameList.size() && j != destinationNameList.size()));
        return j == destinationNameList.size();
    }



    public String createTourCode(String input) {
        StringBuilder tourCode = new StringBuilder();
        long currentTimeSeconds = System.currentTimeMillis() / 1000;
        System.out.println(input);
        long firstThreeDigits = currentTimeSeconds % 1000;
        String[] words = input.split("\\s+");


        for (String word : words) {
            if (!word.isEmpty()) {
                char firstChar = word.charAt(0);
                tourCode.append(Character.toUpperCase(firstChar));
            }
        }
        return tourCode.toString() + firstThreeDigits;
    }
}
