
package tn.esprit.spring.entities;


import com.twilio.Twilio;
import org.springframework.context.annotation.Configuration;
import tn.esprit.spring.config.Twilioproperties;

@Configuration
public class Twilioinitializer {

    private final Twilioproperties twilioproperties;

    public Twilioinitializer(Twilioproperties twilioproperties)
    {
        this.twilioproperties=twilioproperties;
        Twilio.init(twilioproperties.getAccountSid(), twilioproperties.getAuthToken());
        System.out.println("twilio intialized with account: "+twilioproperties.getAccountSid());
    }

}
