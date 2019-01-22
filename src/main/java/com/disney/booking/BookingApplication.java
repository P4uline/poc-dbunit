package com.disney.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication
public class BookingApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(BookingApplication.class, args);
    }
}