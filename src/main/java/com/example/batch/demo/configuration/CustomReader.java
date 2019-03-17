package com.example.batch.demo.configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.core.io.ClassPathResource;

import java.beans.XMLDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class CustomReader implements ItemReader<String> {

    private final String filename;


    public CustomReader(final String filename) {
        this.filename = filename;
    }

    @Override
    public String read() throws IOException {

        IteratorItemReader iteratorItemReader =  new IteratorItemReader<>(values());
        return (String) iteratorItemReader.read();
    }

    private List<String> values() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        File resource = new ClassPathResource("test.json").getFile();

        Map map = mapper.readValue(resource,new TypeReference<Map<String,Object>>(){});

        return (List<String>) map.values().stream().map(Object::toString).collect(Collectors.toList());

    }
}
