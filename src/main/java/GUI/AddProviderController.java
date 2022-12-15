package GUI;

import APPLICATION.Provider;
import DB.ProviderDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

public class AddProviderController {

    @FXML
    private TextField VATNumberInput;
    @FXML
    private TextField ZIPCodeInput;
    @FXML
    private TextField accountNumberInput;
    @FXML
    private DatePicker activityDateInput;
    @FXML
    private TextField artistNameInput;
    @FXML
    private TextField cityInput;
    @FXML
    private TextField conditionsInput;
    @FXML
    private TextField countryInput;
    @FXML
    private TextField descriptionInput;
    @FXML
    private TextField houseNumberInput;
    @FXML
    private TextField linkToPageInput;
    @FXML
    private TextField linkToSetInput;
    @FXML
    private TextField maxHoursInput;
    @FXML
    private TextField minHoursInput;
    @FXML
    private TextField priceHourInput;
    @FXML
    private TextField streetNameInput;
    @FXML
    private ChoiceBox<String> genreChooser;
    @FXML
    private Label enterValidData;
    @FXML
    private Label enterValidURL;

    public void initialize() {
        genreChooser.getItems().addAll("Techno", "Rock", "Pop", "Dance", "Blues", "Jazz", "Soul", "Party", "Hiphop", "Acoustic", "Disco", "Funk", "Classic", "Background", "Nineties", "Eighties", "Seventies", "Sixties", "Latin", "Lounge", "Other");
        enterValidData.setVisible(false);
        enterValidURL.setVisible(false);
    }

    public void goToHomeScreen(ActionEvent actionEvent) {
        Main.loadPage("UserPage.fxml", actionEvent);
    }

    public void goToProviderPage(ActionEvent actionEvent) {
        enterValidData.setVisible(false);
        enterValidURL.setVisible(false);
        if (Provider.checkVATNumberAddProvider(VATNumberInput.getText()) && Provider.checkAccountNumberAddProvider(accountNumberInput.getText()) && ZIPCodeInput.getText() != null && activityDateInput.getValue() != null && activityDateInput.getValue().isBefore(LocalDate.now()) && activityDateInput.getClass().equals(DatePicker.class) && Provider.checkArtistNameAddProvider(artistNameInput.getText()) && cityInput.getText() != null && countryInput.getText() != null && descriptionInput.getText() != null && houseNumberInput.getText() != null && maxHoursInput.getText() != null && minHoursInput.getText() != null && Provider.checkMinMaxHours(minHoursInput.getText(), maxHoursInput.getText()) && priceHourInput.getText() != null && streetNameInput.getText() != null) {
            try {
                String VATNumber = VATNumberInput.getText();
                if (VATNumber.equals(""))
                    VATNumber = null;
                String accountNumber = accountNumberInput.getText();
                int ZIPCode = Integer.parseInt(ZIPCodeInput.getText());
                LocalDate ld = activityDateInput.getValue();
                java.sql.Date date = java.sql.Date.valueOf(ld);
                String artistName = artistNameInput.getText();
                String city = cityInput.getText();
                String conditions = conditionsInput.getText();
                String country = countryInput.getText();
                String description = descriptionInput.getText();
                String streetName = streetNameInput.getText();
                int houseNumber = Integer.parseInt(houseNumberInput.getText());
                URL linkToPage = new URL(linkToPageInput.getText());
                URL linkToSet = new URL(linkToSetInput.getText());
                double maxHours = Double.parseDouble(maxHoursInput.getText());
                double minHours = Double.parseDouble(minHoursInput.getText());
                double priceHour = Double.parseDouble(priceHourInput.getText());
                Provider.genres genre = Provider.genres.valueOf(genreChooser.getValue());

                Main.providerMain = new Provider(Main.userMain.getUserNumber(), Main.userMain.getFirstName(), Main.userMain.getLastName(), Main.userMain.getDateOfBirth(), Main.userMain.getAge(), Main.userMain.getEmail(), Main.userMain.getPhoneNumber(), Main.userMain.getPassword(), VATNumber, accountNumber, streetName, houseNumber, ZIPCode, city, country, artistName,
                        genre, date, priceHour, minHours, maxHours, conditions, description, linkToSet, linkToPage);
                ProviderDAO.saveProvider(Main.providerMain);

                Main.loadPage("ProviderPage.fxml", actionEvent);

            } catch (MalformedURLException | NumberFormatException e) {
                enterValidURL.setVisible(true);
            }
        }
        else
            enterValidData.setVisible(true);
    }

}