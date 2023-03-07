package tn.esprit.spring.service;

import java.util.*;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.config.InvalidInputException;
import tn.esprit.spring.dto.NotificationDto;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.serviceInterface.IHttpHelper;
import tn.esprit.spring.serviceInterface.INotificationService;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IHttpHelper httpHelper;

    @Autowired
    private Environment environment;

    @Override
    public void addAdminToken(NotificationDto notification) throws InvalidInputException {
        Optional<User> adminInDb = userRepository.findById(notification.getId());
        if (adminInDb.isPresent()) {
            User admin = adminInDb.get();
            admin.setToken(notification.getToken());
            userRepository.save(admin);
        } else {
            throw new InvalidInputException("Admin not found");
        }

    }

    @Override
    public void addClientToken(NotificationDto notification) throws InvalidInputException {
        Optional<User> clientInDb = userRepository.findById(notification.getId());
        if (clientInDb.isPresent()) {
            User client = clientInDb.get();
            client.setToken(notification.getToken());
            userRepository.save(client);
        } else {
            throw new InvalidInputException("Client not found");
        }
    }

    @Override
    public void sendNotificationRequest(NotificationDto notification) throws InvalidInputException {
        String url = environment.getProperty("notification.url");
        String serverKey = environment.getProperty("notification.serverKey");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "key=" + serverKey);

        // request body parameters
        Map<String, String> map = new HashMap<String, String>();
        map.put("title", notification.getNotificationTitle());
        map.put("body", notification.getNotificationBody());

        Map<String, Object> notificationMap = new HashMap<String, Object>();
        notificationMap.put("notification", map);
        notificationMap.put("to", notification.getToken());

        // build the request
        HttpEntity<Map<String, Object>> requestentity = new HttpEntity<>(notificationMap, headers);
        httpHelper.post(url, requestentity, Object.class);
    }

    @Override
    public void sendNotificationToUser(NotificationDto notificationDto) throws InvalidInputException {
        User userInDb = userRepository.getById(notificationDto.getId());
        if (userInDb == null) {
            return;
        }
        notificationDto.setToken(userInDb.getToken());
        this.sendNotificationRequest(notificationDto);
    }

}
