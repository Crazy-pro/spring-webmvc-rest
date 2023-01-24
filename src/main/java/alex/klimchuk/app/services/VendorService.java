package alex.klimchuk.app.services;

import alex.klimchuk.app.api.v1.model.*;

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
