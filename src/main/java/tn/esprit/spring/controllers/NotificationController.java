package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tn.esprit.spring.dto.ApiResponse;
import tn.esprit.spring.dto.NotificationDto;
import tn.esprit.spring.serviceInterface.INotificationService;

@Controller
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private INotificationService notificationService;
    
    @PostMapping("/admin")
    @ResponseBody
    public ApiResponse addAdminToken(@RequestBody NotificationDto notification){
        ApiResponse apiResponse = new ApiResponse();
        try{
            notificationService.addAdminToken(notification);
            apiResponse.setStatus("SUCCESS");
            apiResponse.setMessage("Token added successfully");
        }catch(Exception ex){
            apiResponse.setStatus("ERROR");
            apiResponse.setMessage("Error while adding token");
            apiResponse.setError(ex.getMessage());
        }
        return apiResponse;
    }

    @PostMapping("/client")
    @ResponseBody
    public ApiResponse addClientToken(@RequestBody NotificationDto notification){
        ApiResponse apiResponse = new ApiResponse();

        try{
            notificationService.addClientToken(notification);
            apiResponse.setStatus("SUCCESS");
            apiResponse.setMessage("Token added successfully");
        }catch(Exception ex){
            apiResponse.setStatus("ERROR");
            apiResponse.setMessage("Error while adding token");
            apiResponse.setError(ex.getMessage());
        }
        return apiResponse;
    }


    @PostMapping("/send")
    @ResponseBody
    public ApiResponse sendNotification(@RequestBody NotificationDto notification){
        ApiResponse apiResponse = new ApiResponse();

        try{
            notificationService.sendNotificationToUser(notification);
            apiResponse.setStatus("SUCCESS");
            apiResponse.setMessage("Token added successfully");
        }catch(Exception ex){
            apiResponse.setStatus("ERROR");
            apiResponse.setMessage("Error while adding token");
            apiResponse.setError(ex.getMessage());
        }
        return apiResponse;
    }

}
