package alex.klimchuk.spring.webmvc.rest.api.v1.mapper;

import alex.klimchuk.spring.webmvc.rest.api.v1.model.VendorDto;
import alex.klimchuk.spring.webmvc.rest.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Copyright Alex Klimchuk (c) 2023.
 */
@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDto vendorToVendorDto(Vendor vendor);

    Vendor vendorDtoToVendor(VendorDto vendorDto);

}
