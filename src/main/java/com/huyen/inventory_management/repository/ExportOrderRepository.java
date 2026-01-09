package com.huyen.inventory_management.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.huyen.inventory_management.model.ExportOrder;

@Repository
public interface ExportOrderRepository extends JpaRepository<ExportOrder, UUID>{
    List<ExportOrder> findByDeletedFalse();
    
    Optional<ExportOrder> findByIdAndDeletedFalse(UUID id);
}
