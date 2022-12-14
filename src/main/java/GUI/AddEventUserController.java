package GUI;

import APPLICATION.Event;
import APPLICATION.NonProfitOrganisation;
import DB.EventDAO;
import DB.NonProfitOrganisationDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;

public class AddEventUserController {

    public static Event eventMain;
    @FXML
    private TextField cityInput;
    @FXML
    private TextField countryInput;
    @FXML
    private TextField descriptionInput;
    @FXML
    private DatePicker endDateInput;
    @FXML
    private TextField eventNameInput;
    @FXML
    private TextField houseNumberInput;
    @FXML
    private TextField linkToPageInput;
    @FXML
    private TextField zipCodeInput;
    @FXML
    private DatePicker startDateInput;
    @FXML
    private TextField streetNameInput;
    @FXML
    private ChoiceBox<String> endDatePicker;
    @FXML
    private ChoiceBox<String> startDatePicker;
    @FXML
    private ListView<String> goodCauses;

    public void initialize() {
        for (NonProfitOrganisation npo : NonProfitOrganisationDAO.getNonProfitOrganisations()) {
            String s = npo.getNPOName() + " - " + npo.getCauseOfNPO().toString();
            goodCauses.getItems().add(s);
        }

        startDatePicker.getItems().addAll("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00");
        endDatePicker.getItems().addAll("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00");
    }

    public void makeNewEvent(ActionEvent actionEvent) {
        try {
            String eventName1 = eventNameInput.getText();
            String streetName1 = streetNameInput.getText();
            int houseNumber1 = Integer.parseInt(houseNumberInput.getText());
            int ZIPCode1 = Integer.parseInt(zipCodeInput.getText());
            String cityInput1 = cityInput.getText();
            String country1 = countryInput.getText();

            LocalDate startDateLD = startDateInput.getValue();
            String startDateString = startDatePicker.getValue();
            int startDateInteger = Integer.parseInt(startDateString.substring(0, 1));
            LocalDateTime startDate1 = LocalDateTime.of(startDateLD.getYear(), startDateLD.getMonth(), startDateLD.getDayOfMonth(), startDateInteger, 00, 00);

            LocalDate endDateLD = endDateInput.getValue();
            String endDateString = endDatePicker.getValue();
            int endDateInteger = Integer.parseInt(endDateString.substring(0, 1));
            LocalDateTime endDate1 = LocalDateTime.of(endDateLD.getYear(), endDateLD.getMonth(), endDateLD.getDayOfMonth(), endDateInteger, 00, 00);

            String description1 = descriptionInput.getText();
            URL linkToPage1 = new URL(linkToPageInput.getText());

            String s = goodCauses.getSelectionModel().getSelectedItem();
            String subs = s.substring(0, (s.indexOf("-")-1));
            int npoNumber1 = 0;
            for (NonProfitOrganisation npo : NonProfitOrganisationDAO.getNonProfitOrganisations()) {
                if (subs.equals(npo.getNPOName()))
                    npoNumber1 = npo.getNPONumber();
            }
            if (startDate1.isBefore(endDate1) && LocalDate.now().isBefore(ChronoLocalDate.from(startDate1.minusDays(5))) && eventName1 != null && streetName1 != null && houseNumberInput.getText() != null && zipCodeInput.getText() != null && country1 != null && description1 != null) {
                eventMain = new Event(Main.userMain.getUserNumber(), eventName1, streetName1, houseNumber1, ZIPCode1, cityInput1, country1, startDate1, endDate1, description1, linkToPage1, npoNumber1);
                EventDAO.saveEvent(eventMain);
                Main.loadPage("UserPage.fxml", actionEvent);
            }
        }catch (MalformedURLException | NumberFormatException e) {
        }
    }

    public void goToHomePage(ActionEvent actionEvent) {
        Main.loadPage("UserPage.fxml", actionEvent);
    }
}
