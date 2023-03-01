package tn.esprit.spring.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import tn.esprit.spring.entities.Smsrequest;
import tn.esprit.spring.service.SmsService;


@RestController
public class TwilioRestController {

    @Autowired
    private SmsService smsservice;

    @RequestMapping("/")
    public String homepage(ModelAndView model)
    {
        return "index";
    }

    @PostMapping("/sendmessage")
    public ResponseEntity<Object> sendmessage(@RequestBody Smsrequest smsrequest) {

        String status = null;
        try {
            status = smsservice.sendsms(smsrequest);
            if ("sent".equals(status) || "queued".equals(status)) {
                return new ResponseEntity<Object>("sent successfully", HttpStatus.OK);
            }
            else
                return null;
        } catch (Exception e) {
            return new ResponseEntity<Object>("failed to send message "+e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }





}
