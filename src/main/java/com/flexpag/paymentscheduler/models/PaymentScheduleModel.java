package com.flexpag.paymentscheduler.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flexpag.paymentscheduler.models.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "tb_payment")
public class PaymentScheduleModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant date;
    private Double payment;
    private Integer paymentStatus;

    public PaymentScheduleModel() {
        setPaymentStatus(PaymentStatus.PENDING);
    }

    public PaymentScheduleModel(Instant date, Double payment) {
        this.date = date;
        this.payment = payment;
        setPaymentStatus(PaymentStatus.PENDING);
    }

    public PaymentStatus getPaymentStatus() {
        return PaymentStatus.valueOf(paymentStatus);
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus.getCode();
    }

}
