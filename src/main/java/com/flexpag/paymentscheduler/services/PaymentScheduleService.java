package com.flexpag.paymentscheduler.services;

import com.flexpag.paymentscheduler.models.PaymentScheduleModel;
import com.flexpag.paymentscheduler.repositories.PaymentScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PaymentScheduleService {

    @Autowired
    PaymentScheduleRepository paymentScheduleRepository;

    @Transactional
    public PaymentScheduleModel save(PaymentScheduleModel paymentScheduleModel) {
        return paymentScheduleRepository.save(paymentScheduleModel);
    }
}
