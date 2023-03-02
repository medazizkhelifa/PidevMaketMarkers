package tn.esprit.spring.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GiftPointDto {
    private int clientId;
    private double availableBalance;
    private double totalBalance;
}
