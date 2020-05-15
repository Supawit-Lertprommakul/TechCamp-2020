package com.digitalacademy.loan.service;

import com.digitalacademy.loan.controller.LoanController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoanService {

    private static final Logger log = LogManager.getLogger(LoanService.class.getName());

    public Map<String, Object> getLoanInfoById(Long id) throws Exception {
        log.info("Get loan info by customer id: " + id);

        Map<String, Object> map = new HashMap<>();

        map.put("id", 1L);
        map.put("status", "OK");
        map.put("accountPayable", "102-222-2200");
        map.put("accountReceivable", "102-333-2020");
        map.put("principalAmount", 3400000.00);
        return map;
    }

    public Map<String, Object> getLoanInfoByIdTwo(Long id) throws Exception {
        log.info("Get loan info by customer id: " + id);

        Map<String, Object> map = new HashMap<>();

        map.put("id", 1L);
        map.put("status", "OK");
        map.put("accountPayable", "102-222-2200");
        map.put("accountReceivable", "102-333-2020");
        map.put("principalAmount", 3400000.00);
        return map;
    }
}
