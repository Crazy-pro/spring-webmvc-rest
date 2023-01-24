package alex.klimchuk.app.services;

import alex.klimchuk.app.api.v1.mapper.VendorMapper;
import alex.klimchuk.app.api.v1.model.VendorDto;
import alex.klimchuk.app.api.v1.model.VendorListDto;
import alex.klimchuk.app.domain.Vendor;
import alex.klimchuk.app.exceptions.ResourceNotFoundException;
import alex.klimchuk.app.repositories.VendorRepository;
import alex.klimchuk.app.services.impl.VendorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

/**
 * Copyright Alex Klimchuk (c) 2023.
 */
public class VendorServiceImplTest {

    public static final String NAME_1 = "My Vendor";
    public static final long ID_1 = 1L;
    public static final String NAME_2 = "My Vendor";
    public static final long ID_2 = 2L;

    @Mock
    VendorRepository vendorRepository;

    VendorService vendorService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
    }

    @Test
    public void testGetVendorById() {
        Vendor vendor = getVendor1();

        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));

        VendorDto vendorDto = vendorService.getVendorById(ID_1);

        then(vendorRepository).should(times(1)).findById(anyLong());

        assertThat(vendorDto.getName(), is(equalTo(NAME_1)));
    }

    @Test
    public void testGetVendorByIdNotFound() throws ResourceNotFoundException {
        given(vendorRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> vendorService.getVendorById(ID_1));
        then(vendorRepository).should(times(1)).findById(anyLong());
    }

    @Test
    public void testGetAllVendors() {
        List<Vendor> vendors = Arrays.asList(getVendor1(), getVendor2());

        given(vendorRepository.findAll()).willReturn(vendors);

        VendorListDto vendorListDto = vendorService.getAllVendors();

        then(vendorRepository).should(times(1)).findAll();
        assertThat(vendorListDto.getVendors().size(), is(equalTo(2)));
    }

    @Test
    public void testCreateNewVendor() {
        VendorDto vendorDto = new VendorDto();
        vendorDto.setName(NAME_1);

        Vendor vendor = getVendor1();

        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);

        VendorDto savedVendorDto = vendorService.createNewVendor(vendorDto);

        then(vendorRepository).should().save(any(Vendor.class));
        assertThat(savedVendorDto.getVendorUrl(), containsString("1"));
    }

    @Test
    public void testSaveVendorByDto() {
        VendorDto vendorDto = new VendorDto();
        vendorDto.setName(NAME_1);

        Vendor vendor = getVendor1();

        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);

        VendorDto savedVendorDto = vendorService.saveVendorByDto(ID_1, vendorDto);

        then(vendorRepository).should().save(any(Vendor.class));
        assertThat(savedVendorDto.getVendorUrl(), containsString("1"));
    }

    @Test
    public void testPatchVendor() {
        VendorDto vendorDto = new VendorDto();
        vendorDto.setName(NAME_1);

        Vendor vendor = getVendor1();

        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));
        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);

        VendorDto savedVendorDto = vendorService.patchVendor(ID_1, vendorDto);

        then(vendorRepository).should().save(any(Vendor.class));
        then(vendorRepository).should(times(1)).findById(anyLong());
        assertThat(savedVendorDto.getVendorUrl(), containsString("1"));
    }

    @Test
    public void testDeleteVendorById() {
        vendorService.deleteVendorById(ID_1);

        then(vendorRepository).should().deleteById(anyLong());
    }

    private Vendor getVendor1() {
        return Vendor.builder()
                .name(NAME_1)
                .id(ID_1)
                .build();
    }

    private Vendor getVendor2() {
        return Vendor.builder()
                .name(NAME_2)
                .id(ID_2)
                .build();
    }

}
