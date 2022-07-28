package com.flexpag.paymentscheduler.config;

import com.flexpag.paymentscheduler.models.PaymentScheduleModel;
import com.flexpag.paymentscheduler.repositories.PaymentScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Arrays;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private PaymentScheduleRepository paymentScheduleRepository;

    @Override
    public void run(String... args) throws Exception {

        PaymentScheduleModel p1 = new PaymentScheduleModel(null, Instant.parse("2022-07-30T11:00:00Z"), 500.0, 1L);
        PaymentScheduleModel p2 = new PaymentScheduleModel(null, Instant.parse("2022-08-30T11:00:00Z"), 700.0, 2L);
        PaymentScheduleModel p3 = new PaymentScheduleModel(null, Instant.parse("2022-05-30T11:00:00Z"), 1100.0, 1L);

        paymentScheduleRepository.saveAll(Arrays.asList(p1, p2, p3));

    }
}
