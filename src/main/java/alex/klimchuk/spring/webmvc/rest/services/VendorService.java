package alex.klimchuk.spring.webmvc.rest.services;

import alex.klimchuk.spring.webmvc.rest.api.v1.model.VendorDto;
import alex.klimchuk.spring.webmvc.rest.api.v1.model.VendorListDto;

/**
 * Copyright Alex Klimchuk (c) 2023.
 */
public interface VendorService {

    VendorDto getVendorById(Long id);

    VendorListDto getAllVendors();

    VendorDto createNewVendor(VendorDto vendorDto);

    VendorDto saveVendorByDto(Long id, VendorDto vendorDto);

    VendorDto patchVendor(Long id, VendorDto vendorDto);

    void deleteVendorById(Long id);

}
