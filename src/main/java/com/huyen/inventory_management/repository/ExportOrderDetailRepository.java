package com.huyen.inventory_management.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.huyen.inventory_management.model.ExportOrderDetail;

@Repository
public interface ExportOrderDetailRepository extends JpaRepository<ExportOrderDetail, UUID>{
    Optional<ExportOrderDetail> findByIdAndDeletedFalse(UUID id);
}
