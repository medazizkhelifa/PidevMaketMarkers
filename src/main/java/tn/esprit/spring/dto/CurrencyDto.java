package tn.esprit.spring.dto;

import java.util.Map;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CurrencyDto {

    public String base;
    public Map<String, Float> results;
    public String updated;
    public int ms;

}
