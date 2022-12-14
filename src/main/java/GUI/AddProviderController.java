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

    public void initialize() {
        genreChooser.getItems().addAll("Techno", "Rock", "Pop", "Dance", "Blues", "Jazz", "Soul", "Party", "Hiphop", "Acoustic", "Disco", "Funk", "Classic", "Background", "Nineties", "Eighties", "Seventies", "Sixties", "Latin", "Lounge", "Other");
    }

    public void goToHomeScreen(ActionEvent actionEvent) {
        Main.loadPage("UserPage.fxml", actionEvent);
    }

    public void goToProviderPage(ActionEvent actionEvent) {
        if (Provider.checkVATNumber(VATNumberInput.getText()) && accountNumberInput.getText() != null && ZIPCodeInput.getText() != null && activityDateInput.getValue() != null && activityDateInput.getClass().equals(DatePicker.class) && Provider.checkArtistName(artistNameInput.getText()) && cityInput.getText() != null && countryInput.getText() != null && descriptionInput.getText() != null && houseNumberInput.getText() != null && maxHoursInput.getText() != null && minHoursInput.getText() != null && Provider.checkMinMaxHours(minHoursInput, maxHoursInput) && priceHourInput.getText() != null && streetNameInput.getText() != null) {
            try {
                String VATNumber = VATNumberInput.getText();
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
                String genre0 = genreChooser.getValue();
                Provider.genres genre = Provider.genres.Other;
                if (genre0.equals("Techno"))
                    genre = Provider.genres.Techno;
                if (genre0.equals("Rock"))
                    genre = Provider.genres.Rock;
                if (genre0.equals("Pop"))
                    genre = Provider.genres.Pop;
                if (genre0.equals("Dance"))
                    genre = Provider.genres.Dance;
                if (genre0.equals("Blues"))
                    genre = Provider.genres.Blues;
                if (genre0.equals("Jazz"))
                    genre = Provider.genres.Jazz;
                if (genre0.equals("Soul"))
                    genre = Provider.genres.Soul;
                if (genre0.equals("Party"))
                    genre = Provider.genres.Party;
                if (genre0.equals("Hiphop"))
                    genre = Provider.genres.Hiphop;
                if (genre0.equals("Acoustic"))
                    genre = Provider.genres.Acoustic;
                if (genre0.equals("Disco"))
                    genre = Provider.genres.Disco;
                if (genre0.equals("Funk"))
                    genre = Provider.genres.Funk;
                if (genre0.equals("CLassic"))
                    genre = Provider.genres.Classic;
                if (genre0.equals("Background"))
                    genre = Provider.genres.Background;
                if (genre0.equals("Nineties"))
                    genre = Provider.genres.Nineties;
                if (genre0.equals("Eighties"))
                    genre = Provider.genres.Eighties;
                if (genre0.equals("Seventies"))
                    genre = Provider.genres.Seventies;
                if (genre0.equals("Sixties"))
                    genre = Provider.genres.Sixties;
                if (genre0.equals("Latin"))
                    genre = Provider.genres.Latin;
                if (genre0.equals("Lounge"))
                    genre = Provider.genres.Lounge;
                if (genre0.equals("Other"))
                    genre = Provider.genres.Other;
                Main.providerMain = new Provider(Main.userMain.getUserNumber(), Main.userMain.getFirstName(), Main.userMain.getLastName(), Main.userMain.getDateOfBirth(), Main.userMain.getAge(), Main.userMain.getEmail(), Main.userMain.getPhoneNumber(), Main.userMain.getPassword(), VATNumber, accountNumber, streetName, houseNumber, ZIPCode, city, country, artistName,
                        genre, date, priceHour, minHours, maxHours, conditions, description, linkToSet, linkToPage);
                ProviderDAO.saveProvider(Main.providerMain);
                Main.loadPage("ProviderPage.fxml", actionEvent);
            } catch (MalformedURLException | NumberFormatException e) {
            }
        }
    }

}