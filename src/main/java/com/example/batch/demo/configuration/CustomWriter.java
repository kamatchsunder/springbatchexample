package com.example.batch.demo.configuration;

import org.springframework.batch.item.ItemWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;


public class CustomWriter implements ItemWriter<String> {

    private final PrintWriter writer;

    public CustomWriter() {
        OutputStream out;
        try {
            out = new FileOutputStream("output.txt");
        } catch (FileNotFoundException e) {
            out = System.out;
        }
        this.writer = new PrintWriter(out);
    }


    @Override
    public void write(List<? extends String> list) {
        for (String item : list) {
            writer.println(item);
        }
    }
}
