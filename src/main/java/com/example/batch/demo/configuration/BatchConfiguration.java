package com.example.batch.demo.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BatchConfiguration {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private JobRepository jobRepository;

    @Bean
    @StepScope
    public CustomReader reader(@Value("#{jobParameters[filename]}") String filename){

        return new CustomReader(filename);
    }

    @Bean
    @StepScope
    public CustomWriter writer(){

        return new CustomWriter();
    }


    @Bean
    public Step batchStep(CustomReader customReader,CustomWriter writer){

        return stepBuilderFactory.get("sampleStep")
                .<String,String>chunk(1)
                .reader(customReader)
                .writer(writer)
                .build();
    }

    @Bean
    public Job job(Step batchStep){

        return jobBuilderFactory.get("sampleJob")
                .incrementer(new RunIdIncrementer())
                .flow(batchStep)
                .end()
                .build();

    }

}
