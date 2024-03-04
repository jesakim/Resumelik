package com.example.resumlik.dto.response;

import com.example.resumlik.model.Address;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressResponseDto {
    private Long id;
    private String street;
    private String additionalStreet;
    private String city;
    private String country;

    public AddressResponseDto(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.additionalStreet = address.getAdditionalStreet();
        this.city = address.getCity();
        this.country = address.getCountry();
    }
}
