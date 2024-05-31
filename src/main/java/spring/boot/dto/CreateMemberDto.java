package spring.boot.dto;

import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import spring.boot.model.Address;
import spring.boot.model.Order;

import java.util.List;

@Getter
public class CreateMemberDto {
    @NotEmpty
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
