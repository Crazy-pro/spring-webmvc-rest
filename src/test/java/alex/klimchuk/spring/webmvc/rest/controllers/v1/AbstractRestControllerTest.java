package alex.klimchuk.spring.webmvc.rest.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Copyright Alex Klimchuk (c) 2023.
 */
public abstract class AbstractRestControllerTest {

    public static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
