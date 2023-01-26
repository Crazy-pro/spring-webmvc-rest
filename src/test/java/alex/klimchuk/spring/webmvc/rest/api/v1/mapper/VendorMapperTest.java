package alex.klimchuk.spring.webmvc.rest.api.v1.mapper;

import alex.klimchuk.spring.webmvc.rest.api.v1.model.VendorDto;
import alex.klimchuk.spring.webmvc.rest.domain.Vendor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Copyright Alex Klimchuk (c) 2023.
 */
public class VendorMapperTest {

    public static final String NAME = "someName";

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    public void testVendorToVendorDto() {
        Vendor vendorMock = new Vendor();
        vendorMock.setName(NAME);

        VendorDto vendorDto = vendorMapper.vendorToVendorDto(vendorMock);

        assertEquals(vendorMock.getName(), vendorDto.getName());
    }

    @Test
    public void testVendorDtoToVendor() {
        VendorDto vendorDto = new VendorDto();
        vendorDto.setName(NAME);

        Vendor vendor = vendorMapper.vendorDtoToVendor(vendorDto);

        assertEquals(vendorDto.getName(), vendor.getName());
    }

}
