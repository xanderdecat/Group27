package com.example.group27;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

public class RequestedEventsController {

    @FXML
    private Label ZIPToSet;

    @FXML
    private Label cityToSet;

    @FXML
    private Label countryToSet;

    @FXML
    private Label descriptionToSet;

    @FXML
    private Label endDateToSet;

    @FXML
    private Label eventNameToSet;

    @FXML
    private Hyperlink linkToSet;

    @FXML
    private Label startDateToSet;

    @FXML
    private Label streetNameToSet;

    public void initialize() {
        eventNameToSet.setText(ProviderPageController.requestedEvent.getEventName());
        startDateToSet.setText(ProviderPageController.requestedEvent.getStartDate().toString());
        endDateToSet.setText(ProviderPageController.requestedEvent.getEndDate().toString());
        streetNameToSet.setText(ProviderPageController.requestedEvent.getStreetName() + " " + ProviderPageController.requestedEvent.getHouseNumber());
        ZIPToSet.setText(String.valueOf(ProviderPageController.requestedEvent.getZIP()));
        cityToSet.setText(ProviderPageController.requestedEvent.getCity());
        countryToSet.setText(ProviderPageController.requestedEvent.getCountry());
        linkToSet.setText(String.valueOf(ProviderPageController.requestedEvent.getLinkToPage()));
        descriptionToSet.setText(ProviderPageController.requestedEvent.getDescription());
    }


    public void acceptRequest(ActionEvent actionEvent) {

    }

    public void declineRequest(ActionEvent actionEvent) {
    }

    public void goBack(ActionEvent actionEvent) {
    }
    //public static boolean checkProviderUserNumber(int providerNumber, int userNumber){
    //        for (Provider provider : ProviderDAO.getProviders()) {
    //            if (provider.getUserNumber() == userNumber)
    //                return true;
    //        }
    //        return false;
    //
    //    }
}
