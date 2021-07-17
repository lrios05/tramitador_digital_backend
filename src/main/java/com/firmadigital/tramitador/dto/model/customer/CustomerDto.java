package com.firmadigital.tramitador.dto.model.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.firmadigital.tramitador.dto.model.user.UserDto;
import com.firmadigital.tramitador.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDto {

    private Long customerId;
    private String customerCode;
    private String dni;
    private String name;
    private String paternal;
    private String maternal;
    private String mobile;
    private String phone;
    private String email;
    private String address;
    private UserDto userDto;

    public String getFullName() {
        return maternal != null ? name.concat(" ").concat(paternal).concat(maternal) : "";
    }
}
