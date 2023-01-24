package alex.klimchuk.app.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright Alex Klimchuk (c) 2023.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendorDto {

    @ApiModelProperty(value = "Name of the Vendor", required = true)
    private String name;

    @JsonProperty("vendor_url")
    private String vendorUrl;

}
