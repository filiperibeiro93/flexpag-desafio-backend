package com.flexpag.paymentscheduler.repositories;

import com.flexpag.paymentscheduler.models.PaymentScheduleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentScheduleRepository extends JpaRepository<PaymentScheduleModel, Long> {
}
