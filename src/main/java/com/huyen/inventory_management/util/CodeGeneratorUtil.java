package com.huyen.inventory_management.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class CodeGeneratorUtil {

    @PersistenceContext
    private EntityManager entityManager;

    public String generateCode(String prefix, String sequence) {
        String datePart = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        Long seq = ((Number) entityManager
                .createNativeQuery("SELECT nextval('" + sequence + "')")
                .getSingleResult())
                .longValue();

        // Format: PREFIX-YYYYMMDD-XXXX
        return String.format("%s-%s-%04d", prefix, datePart, seq);
    }
}
