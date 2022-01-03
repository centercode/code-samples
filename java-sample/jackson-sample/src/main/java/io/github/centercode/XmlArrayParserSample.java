package io.github.centercode;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class XmlArrayParserSample {

    private static final XmlMapper mapper = new XmlMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        URI uri = ClassLoader.getSystemResource("array.xml").toURI();
        String xmlStr = new String(Files.readAllBytes(Paths.get(uri)), UTF_8);
        Configuration conf = mapper.readValue(xmlStr, Configuration.class);
        for (Property pair : conf.properties) {
            String name = pair.name;
            String value = pair.value;
            System.out.println(pair.source + "\t" + name + "=" + value);
        }
    }

    static class Configuration {
        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "property")
        private List<Property> properties;
    }

    static class Property {
        public String name;
        public String value;
        public String source;
    }
}
