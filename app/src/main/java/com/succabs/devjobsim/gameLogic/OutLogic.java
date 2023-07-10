package com.succabs.devjobsim.gameLogic;

import com.succabs.devjobsim.ui.GameUI;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

public class OutLogic {
    private static List<Place> availablePlaces = new ArrayList<>();

    public static void setAvailablePlaces(Place... places) {
        availablePlaces.clear();
        availablePlaces.addAll(Arrays.asList(places));
    }

    public static void handleOutButton(GameUI gameUI) {
        StringBuilder placesContent = new StringBuilder();
        placesContent.append("Available Places to Go:\n");

        for (Place place : availablePlaces) {
            placesContent.append(place.getName()).append("\n");
        }
        gameUI.updateOutScreen(placesContent.toString());
    }
}

class Place {
    private String name;

    public Place(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
