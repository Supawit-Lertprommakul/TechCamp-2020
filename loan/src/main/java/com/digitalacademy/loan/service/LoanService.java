package com.digitalacademy.loan.service;

import com.digitalacademy.loan.constants.LoanError;
import com.digitalacademy.loan.exception.LoanException;
import com.digitalacademy.loan.model.LoanInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoanService {

    private static final Logger log = LogManager.getLogger(LoanService.class.getName());

//    public Map<String, Object> getLoanInfoById(Long id) throws Exception {
//        log.info("Get loan info by customer id: " + id);
//
//        Map<String, Object> map = new HashMap<>();
//
//        map.put("id", 1L);
//        map.put("status", "OK");
//        map.put("accountPayable", "102-222-2200");
//        map.put("accountReceivable", "102-333-2020");
//        map.put("principalAmount", 3400000.00);
//        return map;
//    }

    public LoanInfo getLoanInfoById(Long id) throws Exception {
        log.info("Get loan info by customer id: " + id);

        LoanInfo loanInfo = new LoanInfo();

        if (id.equals(1L)) {
            loanInfo.setId(1L);
            loanInfo.setStatus("OK");
            loanInfo.setAccountPayable("102-222-2200");
            loanInfo.setAccountReceivable("102-333-2020");
            loanInfo.setPrincipalAmount(3400000.00);
        } else if(id.equals(2L)) {
            log.info("id: {}", id);
            throw new LoanException(
                    LoanError.GET_LOAN_INFO_NOT_FOUND,
                    HttpStatus.BAD_REQUEST
            );
            //throw new
            // return loan exception loan not found http status
            // bad request
        } else {
            log.info("id: {}", id);
            throw new Exception("Test throw new exception");
        }
        return loanInfo;
    }
}
