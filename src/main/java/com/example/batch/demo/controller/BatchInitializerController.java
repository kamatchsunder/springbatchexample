package com.example.batch.demo.controller;

import com.example.batch.demo.model.JobResponse;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BatchInitializerController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @PostMapping(value="/api/initbatch",consumes = "application/json")
    public JobResponse initJob(@RequestBody JobRequest jobRequest){

        // In spring job is identified by parameters, so it is required to add timestamp parameter to uniquely identify each job
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addString("filename",jobRequest.getFilename());
        jobParametersBuilder.addLong("timestamp", System.currentTimeMillis());


        JobParameters jobParameters = jobParametersBuilder.toJobParameters();

        try {
            return new JobResponse(jobLauncher.run(job,jobParameters).getJobId());
        }
        catch (JobExecutionException e) {
           throw new RuntimeException(e);
        }

    }
}
