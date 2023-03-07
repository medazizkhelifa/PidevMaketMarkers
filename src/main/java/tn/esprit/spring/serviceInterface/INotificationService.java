package tn.esprit.spring.serviceInterface;

import tn.esprit.spring.config.InvalidInputException;
import tn.esprit.spring.dto.NotificationDto;

public interface INotificationService {
    void addAdminToken(NotificationDto notification) throws InvalidInputException;
    void addClientToken(NotificationDto notification) throws InvalidInputException;
    void sendNotificationRequest(NotificationDto notification) throws InvalidInputException;
    void sendNotificationToUser(NotificationDto notification) throws InvalidInputException;
}
