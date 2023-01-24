package alex.klimchuk.app.services.impl;

import alex.klimchuk.app.api.v1.mapper.VendorMapper;
import alex.klimchuk.app.api.v1.model.VendorDto;
import alex.klimchuk.app.api.v1.model.VendorListDto;
import alex.klimchuk.app.controllers.v1.VendorController;
import alex.klimchuk.app.domain.Vendor;
import alex.klimchuk.app.exceptions.ResourceNotFoundException;
import alex.klimchuk.app.repositories.VendorRepository;
import alex.klimchuk.app.services.VendorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Copyright Alex Klimchuk (c) 2023.
 */
@Service
public class VendorServiceImpl implements VendorService {

    private final VendorMapper vendorMapper;
    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public VendorDto getVendorById(Long id) {
        return vendorRepository.findById(id)
                .map(vendorMapper::vendorToVendorDto)
                .map(vendorDto -> {
                    vendorDto.setVendorUrl(getVendorUrl(id));

                    return vendorDto;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorListDto getAllVendors() {
        List<VendorDto> vendorDtos = vendorRepository.findAll()
                .stream()
                .map(vendor -> {
                    VendorDto vendorDto = vendorMapper.vendorToVendorDto(vendor);
                    vendorDto.setVendorUrl(getVendorUrl(vendor.getId()));

                    return vendorDto;
                })
                .collect(Collectors.toList());

        return new VendorListDto(vendorDtos);
    }

    @Override
    public VendorDto createNewVendor(VendorDto vendorDto) {
        return saveAndReturnDto(vendorMapper.vendorDtoToVendor(vendorDto));
    }

    @Override
    public VendorDto saveVendorByDto(Long id, VendorDto vendorDto) {
        Vendor vendorToSave = vendorMapper.vendorDtoToVendor(vendorDto);
        vendorToSave.setId(id);

        return saveAndReturnDto(vendorToSave);
    }

    @Override
    public VendorDto patchVendor(Long id, VendorDto vendorDto) {
        return vendorRepository.findById(id)
                .map(vendor -> {
                    if (Objects.nonNull(vendorDto.getName())) {
                        vendor.setName(vendorDto.getName());
                    }

                    if (Objects.nonNull(vendorDto.getVendorUrl())) {
                        vendor.setName(vendorDto.getVendorUrl());
                    }

                    return saveAndReturnDto(vendor);
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }

    private String getVendorUrl(Long id) {
        return VendorController.BASE_URL + "/" + id;
    }

    private VendorDto saveAndReturnDto(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);

        VendorDto returnDto = vendorMapper.vendorToVendorDto(savedVendor);
        returnDto.setVendorUrl(getVendorUrl(savedVendor.getId()));

        return returnDto;
    }

}
