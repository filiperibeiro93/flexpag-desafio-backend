package com.flexpag.paymentscheduler.controllers;

import com.flexpag.paymentscheduler.models.PaymentScheduleModel;
import com.flexpag.paymentscheduler.models.enums.PaymentStatus;
import com.flexpag.paymentscheduler.services.PaymentScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/payments")
public class PaymentScheduleController {

    @Autowired
    private PaymentScheduleService paymentScheduleService;

    @GetMapping
    public ResponseEntity<List<PaymentScheduleModel>> findAll() {
        List<PaymentScheduleModel> list = paymentScheduleService.findAll();
        for (PaymentScheduleModel p : list) {
            if (p.getDate().equals(Instant.now()) || p.getDate().isBefore(Instant.now())) {
                p.setPaymentStatus(PaymentStatus.PAID);
                paymentScheduleService.save(p);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(paymentScheduleService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Optional<PaymentScheduleModel> paymentScheduleModelOptional = paymentScheduleService.findById(id);

        if (paymentScheduleModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento não encontrado.");
        }

        else if (paymentScheduleModelOptional.get().getDate().equals(Instant.now()) ||
                paymentScheduleModelOptional.get().getDate().isBefore(Instant.now())) {
            paymentScheduleModelOptional.get().setPaymentStatus(PaymentStatus.PAID);
            paymentScheduleService.save(paymentScheduleModelOptional.get());
        }

        return ResponseEntity.status(HttpStatus.OK).body(paymentScheduleModelOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody PaymentScheduleModel paymentScheduleModel) {
        if (paymentScheduleModel.getDate().equals(Instant.now()) || paymentScheduleModel.getDate().isBefore(Instant.now())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Horário do agendamento precisa ser uma data posterior à atual.");
        }
        paymentScheduleModel.setPaymentStatus(PaymentStatus.PENDING);
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentScheduleService.save(paymentScheduleModel));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody PaymentScheduleModel paymentScheduleModel) {
        Optional<PaymentScheduleModel> paymentScheduleModelOptional = paymentScheduleService.findById(id);

        if (paymentScheduleModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento não encontrado.");
        }

        else if (paymentScheduleModelOptional.get().getDate().equals(Instant.now()) ||
                paymentScheduleModelOptional.get().getDate().isBefore(Instant.now())) {
            paymentScheduleModelOptional.get().setPaymentStatus(PaymentStatus.PAID);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Esse pagamento já foi efetuado.");
        }

        else if (paymentScheduleModel.getDate().equals(Instant.now()) ||
                paymentScheduleModel.getDate().isBefore(Instant.now())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Horário do agendamento precisa ser uma data posterior à atual.");
        }

        var updatedPaymentSchedule = new PaymentScheduleModel();
        BeanUtils.copyProperties(paymentScheduleModel, updatedPaymentSchedule);
        updatedPaymentSchedule.setId(paymentScheduleModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(paymentScheduleService.save(updatedPaymentSchedule));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Optional<PaymentScheduleModel> paymentScheduleModelOptional = paymentScheduleService.findById(id);

        if (paymentScheduleModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento não encontrado.");
        }

        else if (paymentScheduleModelOptional.get().getDate().isAfter(Instant.now())) {
            paymentScheduleService.delete(paymentScheduleModelOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Agendamento apagado com sucesso.");
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Esse pagamento já foi efetuado.");
    }
}
