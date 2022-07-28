package com.flexpag.paymentscheduler.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flexpag.paymentscheduler.models.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tb_payment")
public class PaymentScheduleModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;
    private Double payment;
    private Integer paymentStatus;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel userModel;

    public PaymentScheduleModel() {}

    public PaymentScheduleModel(Long id, LocalDate date, Double payment, UserModel userModel) {
        this.id = id;
        this.date = date;
        this.payment = payment;
        setPaymentStatus(PaymentStatus.PENDING);
        this.userModel = userModel;
    }

    public PaymentStatus getPaymentStatus() {
        return PaymentStatus.valueOf(paymentStatus);
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus.getCode();
    }
}
