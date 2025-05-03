package com.musicband.merch.repository;

import com.musicband.merch.entity.MerchOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MerchOrderRepository extends JpaRepository<MerchOrder, Long> {

    Optional<MerchOrder> findByOrderId(UUID orderId);

}
