package com.flexpag.paymentscheduler.services;

import com.flexpag.paymentscheduler.models.PaymentScheduleModel;
import com.flexpag.paymentscheduler.models.enums.PaymentStatus;
import com.flexpag.paymentscheduler.repositories.PaymentScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentScheduleService {

    @Autowired
    PaymentScheduleRepository paymentScheduleRepository;

    public List<PaymentScheduleModel> findAll() {
        return paymentScheduleRepository.findAll();
    }

    public Optional<PaymentScheduleModel> findById(Long id) {
        return paymentScheduleRepository.findById(id);
    }

    @Transactional
    public PaymentScheduleModel save(PaymentScheduleModel paymentScheduleModel) {
        paymentScheduleModel.setPaymentStatus(PaymentStatus.PENDING);
        return paymentScheduleRepository.save(paymentScheduleModel);
    }

    @Transactional
    public void delete(PaymentScheduleModel paymentScheduleModel) {
        paymentScheduleRepository.delete(paymentScheduleModel);
    }
}
