package tn.esprit.spring.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderFilterDto {
    private int id;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    private Double totalPrice;
}
