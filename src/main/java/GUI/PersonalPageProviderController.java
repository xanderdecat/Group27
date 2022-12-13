package GUI;

import APPLICATION.Provider;
import DB.ProviderDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

public class PersonalPageProviderController {

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
    private ChoiceBox<String> genreChooser;

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

    public void initialize() {
        genreChooser.getItems().addAll("Techno", "Rock", "Pop", "Dance", "Blues", "Jazz", "Soul", "Party", "Hiphop", "Acoustic", "Disco", "Funk", "Classic", "Background", "Nineties", "Eighties", "Seventies", "Sixties", "Latin", "Lounge", "Other");

        VATNumberInput.setText(HelloApplication.providerMain.getVATNumber());
        accountNumberInput.setText(HelloApplication.providerMain.getAccountNumber());
        streetNameInput.setText(HelloApplication.providerMain.getAccountNumber());
        houseNumberInput.setText(String.valueOf(HelloApplication.providerMain.getHouseNumber()));
        ZIPCodeInput.setText(String.valueOf(HelloApplication.providerMain.getZIP()));
        cityInput.setText(HelloApplication.providerMain.getCity());
        countryInput.setText(HelloApplication.providerMain.getCountry());
        artistNameInput.setText(HelloApplication.providerMain.getArtistName());
        activityDateInput.setValue(HelloApplication.providerMain.getActivityDate().toLocalDate());
        priceHourInput.setText(String.valueOf(HelloApplication.providerMain.getPriceHour()));
        minHoursInput.setText(String.valueOf(HelloApplication.providerMain.getMinHours()));
        maxHoursInput.setText(String.valueOf(HelloApplication.providerMain.getMaxHours()));
        conditionsInput.setText(HelloApplication.providerMain.getConditions());
        descriptionInput.setText(HelloApplication.providerMain.getDescription());
        linkToSetInput.setText(String.valueOf(HelloApplication.providerMain.getTeaserSet()));
        linkToPageInput.setText(String.valueOf(HelloApplication.providerMain.getLinkToPage()));
        genreChooser.setValue(String.valueOf(HelloApplication.providerMain.getGenre()));
    }

    public void changeInformation(ActionEvent actionEvent) {
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

                HelloApplication.providerMain.setVATNumber(VATNumber);
                HelloApplication.providerMain.setAccountNumber(accountNumber);
                HelloApplication.providerMain.setStreetName(streetName);
                HelloApplication.providerMain.setHouseNumber(houseNumber);
                HelloApplication.providerMain.setZIP(ZIPCode);
                HelloApplication.providerMain.setCity(city);
                HelloApplication.providerMain.setCountry(country);
                HelloApplication.providerMain.setArtistName(artistName);
                HelloApplication.providerMain.setGenre(genre);
                HelloApplication.providerMain.setActivityDate(date);
                HelloApplication.providerMain.setPriceHour(priceHour);
                HelloApplication.providerMain.setMinHours(minHours);
                HelloApplication.providerMain.setMaxHours(maxHours);
                HelloApplication.providerMain.setConditions(conditions);
                HelloApplication.providerMain.setDescription(description);
                HelloApplication.providerMain.setTeaserSet(linkToSet);
                HelloApplication.providerMain.setLinkToPage(linkToPage);

                ProviderDAO.saveProvider(HelloApplication.providerMain);

                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("ProviderPage.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 800, 500);
                    Stage stage = new Stage();
                    stage.setTitle("Muzer");
                    stage.setScene(scene);
                    stage.show();
                    stage.setResizable(false);
                    ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
                } catch (IOException e) {
                }
            } catch (MalformedURLException | NumberFormatException e) {
            }
        }
    }

    public void goBack(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ProviderPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 500);
            Stage stage = new Stage();
            stage.setTitle("Muzer");
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
        }
    }


}
