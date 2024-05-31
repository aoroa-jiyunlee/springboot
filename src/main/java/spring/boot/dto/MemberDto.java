package spring.boot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import spring.boot.model.Address;
import spring.boot.model.Order;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private String name;
    private Address address;
    private List<Order> orders = new ArrayList<>();
}
