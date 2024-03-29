package alex.klimchuk.spring.webmvc.rest.controllers.v1;

import alex.klimchuk.spring.webmvc.rest.api.v1.model.VendorDto;
import alex.klimchuk.spring.webmvc.rest.api.v1.model.VendorListDto;
import alex.klimchuk.spring.webmvc.rest.services.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Copyright Alex Klimchuk (c) 2023.
 */
@WebMvcTest(controllers = {VendorController.class})
public class VendorControllerTest {

    @MockBean
    VendorService vendorService;

    @Autowired
    MockMvc mockMvc;

    VendorDto vendorDto_1;
    VendorDto vendorDto_2;

    @BeforeEach
    public void setUp() throws Exception {
        vendorDto_1 = new VendorDto("Vendor 1", VendorController.BASE_URL + "/1");
        vendorDto_2 = new VendorDto("Vendor 2", VendorController.BASE_URL + "/2");
    }

    @Test
    public void testGetVendorList() throws Exception {
        VendorListDto vendorListDto = new VendorListDto(Arrays.asList(vendorDto_1, vendorDto_2));

        given(vendorService.getAllVendors()).willReturn(vendorListDto);

        mockMvc.perform(get(VendorController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    public void testGetVendorById() throws Exception {
        given(vendorService.getVendorById(anyLong())).willReturn(vendorDto_1);

        mockMvc.perform(get(VendorController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDto_1.getName())));
    }

    @Test
    public void testCreateNewVendor() throws Exception {
        given(vendorService.createNewVendor(vendorDto_1)).willReturn(vendorDto_1);

        mockMvc.perform(post(VendorController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(AbstractRestControllerTest.asJsonString(vendorDto_1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(vendorDto_1.getName())));
    }

    @Test
    public void testUpdateVendor() throws Exception {
        given(vendorService.saveVendorByDto(anyLong(), any(VendorDto.class))).willReturn(vendorDto_1);

        mockMvc.perform(put(VendorController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(AbstractRestControllerTest.asJsonString(vendorDto_1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDto_1.getName())));
    }

    @Test
    public void testPatchVendor() throws Exception {
        given(vendorService.saveVendorByDto(anyLong(), any(VendorDto.class))).willReturn(vendorDto_1);

        mockMvc.perform(patch(VendorController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(AbstractRestControllerTest.asJsonString(vendorDto_1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDto_1.getName())));
    }

    @Test
    public void testDeleteVendor() throws Exception {
        mockMvc.perform(delete(VendorController.BASE_URL + "/1"))
                .andExpect(status().isOk());

        then(vendorService).should().deleteVendorById(anyLong());
    }

}
