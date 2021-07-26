package com.firmadigital.tramitador.dto.mapper.signatures;

import com.firmadigital.tramitador.dto.mapper.ContractMapper;
import com.firmadigital.tramitador.dto.mapper.UserMapper;
import com.firmadigital.tramitador.dto.model.signatures.DigitalSignDto;
import com.firmadigital.tramitador.model.signatures.DigitalSign;
import org.springframework.stereotype.Component;

@Component
public class DigitalSignMapper {

    public static DigitalSignDto toDigitalSignDto (DigitalSign digitalSign) {
        return new DigitalSignDto()
                .setSignId(digitalSign.getId())
                .setContractDto(ContractMapper.toContractDto(digitalSign.getContract()))
                .setUserDto(UserMapper.toUserDto(digitalSign.getUser()));
    }
}
