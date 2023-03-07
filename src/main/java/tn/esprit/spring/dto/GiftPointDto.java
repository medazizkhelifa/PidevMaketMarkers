package tn.esprit.spring.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GiftPointDto {
    private Long clientId;
    private double availableBalance;
    private double totalBalance;
}
