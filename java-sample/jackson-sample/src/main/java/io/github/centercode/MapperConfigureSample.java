package io.github.centercode;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;

public class MapperConfigureSample {

    private static JsonMapper mapper = new JsonMapper();

    static {
        //suppressed error : "Unrecognized field, not marked as ignorable"
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    }
}
