package com.digitalacademy.loan.controller;

import com.digitalacademy.loan.model.LoanInfo;
import com.digitalacademy.loan.service.LoanService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping(path = "/loan")
public class LoanController {
    private static final Logger log = LogManager.getLogger(LoanController.class.getName());
    private LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/info/{id}")
    public HttpEntity<?> getLoanInfoByCustomerId(@PathVariable Long id) throws Exception{
        log.info("Get loan info by customer id: " + id);
        LoanInfo resp = loanService.getLoanInfoById(id);
//        return ResponseEntity.ok(resp);
        return resp != null ? ResponseEntity.ok(resp) : ResponseEntity.notFound().build();
    }

}
