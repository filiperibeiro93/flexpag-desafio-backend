package com.flexpag.paymentscheduler.config;

import com.flexpag.paymentscheduler.models.PaymentScheduleModel;
import com.flexpag.paymentscheduler.models.UserModel;
import com.flexpag.paymentscheduler.repositories.PaymentScheduleRepository;
import com.flexpag.paymentscheduler.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentScheduleRepository paymentScheduleRepository;

    @Override
    public void run(String... args) throws Exception {

        UserModel u1 = new UserModel(null, "filipe", "123.456.789-10", "filipe@gmail.com", "123456");
        UserModel u2 = new UserModel(null, "joao", "123.456.789-10", "joao@gmail.com", "123456");

        PaymentScheduleModel p1 = new PaymentScheduleModel(null, LocalDate.parse("2022-07-30"), 500.0, u1);

        userRepository.saveAll(Arrays.asList(u1, u2));
        paymentScheduleRepository.save(p1);

    }
}
