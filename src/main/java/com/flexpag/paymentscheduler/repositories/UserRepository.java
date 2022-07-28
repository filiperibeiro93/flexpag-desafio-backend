package com.flexpag.paymentscheduler.repositories;

import com.flexpag.paymentscheduler.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
}
