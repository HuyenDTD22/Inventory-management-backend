//package com.huyen.inventory_management.service.impl;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.huyen.inventory_management.dto.ExportOrderDto;
//import com.huyen.inventory_management.dto.ExportOrderResponseDto;
//import com.huyen.inventory_management.dto.ExportOrderUpdateDto;
//import com.huyen.inventory_management.exception.NotFoundException;
//import com.huyen.inventory_management.mapper.ExportOrderMapper;
//import com.huyen.inventory_management.model.Agent;
//import com.huyen.inventory_management.model.ExportOrder;
//import com.huyen.inventory_management.model.ExportOrderDetail;
//import com.huyen.inventory_management.model.Material;
//import com.huyen.inventory_management.model.User;
//import com.huyen.inventory_management.model.Warehouse;
//import com.huyen.inventory_management.repository.AgentRepository;
//import com.huyen.inventory_management.repository.ExportOrderDetailRepository;
//import com.huyen.inventory_management.repository.ExportOrderRepository;
//import com.huyen.inventory_management.repository.MaterialRepository;
//import com.huyen.inventory_management.repository.UserRepository;
//import com.huyen.inventory_management.repository.WarehouseRepository;
//import com.huyen.inventory_management.service.ExportOrderService;
//import com.huyen.inventory_management.util.CodeGeneratorUtil;
//
//import jakarta.transaction.Transactional;
//
//@Service
//public class ExportOrderServiceImpl implements ExportOrderService{
//
//    @Autowired
//    private ExportOrderRepository exportOrderRepository;
//
//    @Autowired
//    private ExportOrderDetailRepository exportOrderDetailRepository;
//
//    @Autowired
//    private AgentRepository agentRepository;
//
//    @Autowired
//    private WarehouseRepository warehouseRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private MaterialRepository materialRepository;
//
//    @Autowired
//    private CodeGeneratorUtil codeGeneratorUtil;
//
//    @Override
//    @Transactional
//    public ExportOrderResponseDto createExportOrder(ExportOrderDto exportOrderDto) {
//        Agent agent = agentRepository.findByIdAndDeletedFalse(exportOrderDto.getAgentId())
//                .orElseThrow(() -> new NotFoundException("Agent not found"));
//        Warehouse warehouse = warehouseRepository.findByIdAndDeletedFalse(exportOrderDto.getWarehouseId())
//                .orElseThrow(() -> new NotFoundException("Warehouse not found"));
//        User user = userRepository.findByIdAndDeletedFalse(exportOrderDto.getUserId())
//                .orElseThrow(() -> new NotFoundException("User not found"));
//
//        ExportOrder exportOrder = new ExportOrder();
//        exportOrder.setCode(codeGeneratorUtil.generateCode("PX", "export_order_seq"));
//        exportOrder.setAgent(agent);
//        exportOrder.setWarehouse(warehouse);
//        exportOrder.setUser(user);
//        exportOrder.setDeleted(false);
//        exportOrder.setPaymentStatus(exportOrderDto.getPaymentStatus());
//        exportOrder.setCreatedAt(LocalDateTime.now());
//
//        exportOrderRepository.save(exportOrder);
//
//        List<ExportOrderDetail> exportOrderDetails = exportOrderDto.getExportOrderDetails().stream()
//                .map(exportOrderDetailDto -> {
//                    Material material = materialRepository.findByIdAndDeletedFalse(exportOrderDetailDto.getMaterialId())
//                            .orElseThrow(() -> new NotFoundException("Material not found"));
//                    if (exportOrderDetailDto.getQuantity().compareTo(BigDecimal.ZERO) <= 0) {
//                        throw new IllegalArgumentException("Quantity must be positive");
//                    }
//                    if (exportOrderDetailDto.getUnitPrice().compareTo(BigDecimal.ZERO) < 0) {
//                        throw new IllegalArgumentException("Unit price can not be negative");
//                    }
//
//                    ExportOrderDetail exportOrderDetail = new ExportOrderDetail();
//                    exportOrderDetail.setMaterial(material);
//                    exportOrderDetail.setQuantity(exportOrderDetailDto.getQuantity());
//                    exportOrderDetail.setUnitPrice(exportOrderDetailDto.getUnitPrice());
//                    exportOrderDetail.setDeleted(false);
//
//                    return exportOrderDetail;
//                })
//                .collect(Collectors.toList());
//
//        exportOrderDetailRepository.saveAll(exportOrderDetails);
//        exportOrder.setExportOrderDetails(exportOrderDetails);
//
//        BigDecimal totalAmount = exportOrderDetails.stream()
//                .map(exportOrderDetail -> exportOrderDetail.getQuantity().multiply(exportOrderDetail.getUnitPrice()))
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//        exportOrder.setTotalAmount(totalAmount);
//        exportOrderRepository.save(exportOrder);
//
//        return ExportOrderMapper.toResponseDto(exportOrder);
//    }
//
//    @Override
//    public ExportOrderResponseDto getExportOrder(UUID id) {
//        ExportOrder exportOrder = exportOrderRepository.findByIdAndDeletedFalse(id)
//                .orElseThrow(() -> new NotFoundException("ExportOrder not found"));
//
//        return ExportOrderMapper.toResponseDto(exportOrder);
//    }
//
//    @Override
//    public List<ExportOrderResponseDto> getAllExportOrders() {
//        return exportOrderRepository.findByDeletedFalse()
//                .stream()
//                .map(ExportOrderMapper::toSumaryDto)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    @Transactional
//    public ExportOrderResponseDto updateExportOrder(UUID id, ExportOrderUpdateDto exportOrderUpdateDto) {
//        ExportOrder exportOrder = exportOrderRepository.findByIdAndDeletedFalse(id)
//                .orElseThrow(() -> new NotFoundException("ExportOrder not found"));
//
//        if (exportOrderUpdateDto.getPaymentStatus() != null) {
//            exportOrder.setPaymentStatus(exportOrderUpdateDto.getPaymentStatus());
//        }
//        if (exportOrderUpdateDto.getAgentId() != null) {
//            Agent agent = agentRepository.findByIdAndDeletedFalse(exportOrderUpdateDto.getAgentId())
//                    .orElseThrow(() -> new NotFoundException("Agent not found"));
//            exportOrder.setAgent(agent);
//        }
//        if (exportOrderUpdateDto.getWarehouseId() != null) {
//            Warehouse warehouse = warehouseRepository.findByIdAndDeletedFalse(exportOrderUpdateDto.getWarehouseId())
//                    .orElseThrow(() -> new NotFoundException("Warehouse not found"));
//            exportOrder.setWarehouse(warehouse);
//        }
//        if (exportOrderUpdateDto.getUserId() != null) {
//            User user = userRepository.findByIdAndDeletedFalse(exportOrderUpdateDto.getUserId())
//                    .orElseThrow(() -> new NotFoundException("User not found"));
//            exportOrder.setUser(user);
//        }
//
//        exportOrderRepository.save(exportOrder);
//
//        return ExportOrderMapper.toSumaryDto(exportOrder);
//    }
//
//    @Override
//    public void deleteExportOrder(UUID id) {
//        ExportOrder exportOrder = exportOrderRepository.findByIdAndDeletedFalse(id)
//                .orElseThrow(() -> new NotFoundException("ExportOrder not found"));
//
//        exportOrder.setDeleted(true);
//        exportOrderRepository.save(exportOrder);
//    }
//}
