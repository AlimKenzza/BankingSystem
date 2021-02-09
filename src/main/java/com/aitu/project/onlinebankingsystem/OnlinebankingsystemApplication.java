package com.aitu.project.onlinebankingsystem;


import com.aitu.project.onlinebankingsystem.filters.File;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@SpringBootApplication
public class OnlinebankingsystemApplication {

    public static void main(String[] args) {

        SpringApplication.run(OnlinebankingsystemApplication.class, args);
        ExecutorService pool = Executors.newFixedThreadPool(10);
        int count = 1;
        while(count<=10){
            File file = new File();
            file.setName("file-" + count);
            pool.execute(file);
            count++;
        }


    }

}
