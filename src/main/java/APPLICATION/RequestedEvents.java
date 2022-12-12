package APPLICATION;

import DB.ProviderDAO;

import java.net.URL;
import java.time.LocalDateTime;

public class RequestedEvents {      //klasse gebruikt om events aan providers aan te bieden
    private enum Service {music, material, service, location}
    private User organizer;
    private Provider requestedProvider;


    private LocalDateTime datum;
    private int duration;
    private String eventName;
    private String streetName;
    private int houseNumber;
    private int ZIP;
    private String city;
    private String country;
    private LocalDateTime startDate;
    private LocalDateTime confirmationDate;
    private LocalDateTime endDate;
    private String description;
    private URL linkToPage;
    public static boolean checkProviderUserNumber(int providerNumber, int userNumber){
        for (Provider provider : ProviderDAO.getProviders()) {
            if (provider.getUserNumber() == userNumber)
                return true;
        }
        return false;

    }

}
