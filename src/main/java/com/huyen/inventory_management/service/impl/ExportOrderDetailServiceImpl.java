//package com.huyen.inventory_management.service.impl;
//
//import java.math.BigDecimal;
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.huyen.inventory_management.dto.ExportOrderDetailDto;
//import com.huyen.inventory_management.dto.ExportOrderDetailResponseDto;
//import com.huyen.inventory_management.dto.ExportOrderDetailUpdateDto;
//import com.huyen.inventory_management.exception.NotFoundException;
//import com.huyen.inventory_management.mapper.ExportOrderMapper;
//import com.huyen.inventory_management.model.ExportOrder;
//import com.huyen.inventory_management.model.ExportOrderDetail;
//import com.huyen.inventory_management.model.Material;
//import com.huyen.inventory_management.repository.ExportOrderDetailRepository;
//import com.huyen.inventory_management.repository.ExportOrderRepository;
//import com.huyen.inventory_management.repository.MaterialRepository;
//import com.huyen.inventory_management.service.ExportOrderDetailService;
//
//@Service
//public class ExportOrderDetailServiceImpl implements ExportOrderDetailService {
//
//    @Autowired
//    private ExportOrderRepository exportOrderRepository;
//
//    @Autowired
//    private ExportOrderDetailRepository exportOrderDetailRepository;
//
//    @Autowired
//    private MaterialRepository materialRepository;
//
//    @Override
//    public ExportOrderDetailResponseDto createExportOrderDetail(ExportOrderDetailDto exportOrderDetailDto) {
//        ExportOrder exportOrder = exportOrderRepository.findByIdAndDeletedFalse(exportOrderDetailDto.getExportOrderId())
//                .orElseThrow(() -> new NotFoundException("Export order not found"));
//        Material material = materialRepository.findByIdAndDeletedFalse(exportOrderDetailDto.getMaterialId())
//                .orElseThrow(() -> new NotFoundException("Material not found"));
//
//        ExportOrderDetail exportOrderDetail = new ExportOrderDetail();
//        exportOrderDetail.setExportOrder(exportOrder);
//        exportOrderDetail.setMaterial(material);
//        exportOrderDetail.setQuantity(exportOrderDetailDto.getQuantity());
//        exportOrderDetail.setUnitPrice(exportOrderDetailDto.getUnitPrice());
//        exportOrderDetail.setDeleted(false);
//
//        exportOrderDetailRepository.save(exportOrderDetail);
//
//        return ExportOrderMapper.toDetailResponseDto(exportOrderDetail);
//    }
//
//    @Override
//    public ExportOrderDetailResponseDto updateExportOrderDetail(UUID id,
//            ExportOrderDetailUpdateDto exportOrderDetailUpdateDto) {
//        ExportOrderDetail exportOrderDetail = exportOrderDetailRepository.findByIdAndDeletedFalse(id)
//                .orElseThrow(() -> new NotFoundException("Export order detail not found"));
//
//        if (exportOrderDetailUpdateDto.getMaterialId() != null) {
//            Material material = materialRepository.findByIdAndDeletedFalse(exportOrderDetailUpdateDto.getMaterialId())
//                    .orElseThrow(() -> new NotFoundException("Material not found"));
//            exportOrderDetail.setMaterial(material);
//        }
//        if (exportOrderDetailUpdateDto.getQuantity() != null && exportOrderDetailUpdateDto.getQuantity().compareTo(BigDecimal.ZERO) > 0) {
//            exportOrderDetail.setQuantity(exportOrderDetailUpdateDto.getQuantity());
//        }
//        if (exportOrderDetailUpdateDto.getUnitPrice() != null && exportOrderDetailUpdateDto.getUnitPrice().compareTo(BigDecimal.ZERO) > 0) {
//            exportOrderDetail.setUnitPrice(exportOrderDetailUpdateDto.getUnitPrice());
//        }
//
//        exportOrderDetailRepository.save(exportOrderDetail);
//
//        return ExportOrderMapper.toDetailResponseDto(exportOrderDetail);
//    }
//
//    @Override
//    public void deleteExportOrderDetail(UUID id) {
//        ExportOrderDetail exportOrderDetail = exportOrderDetailRepository.findByIdAndDeletedFalse(id)
//                .orElseThrow(() -> new NotFoundException("Export order detail not found"));
//
//        exportOrderDetail.setDeleted(true);
//        exportOrderDetailRepository.save(exportOrderDetail);
//    }
//}
