package tn.esprit.spring.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NotificationDto {
    Long id;
    String token;
    String order_id;
    String notificationTitle;
    String notificationBody;
}
