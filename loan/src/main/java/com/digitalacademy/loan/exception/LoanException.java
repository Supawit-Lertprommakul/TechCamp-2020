package com.digitalacademy.loan.exception;

import com.digitalacademy.loan.constants.LoanError;
import org.springframework.http.HttpStatus;

public class LoanException {
    private LoanError loanError;
    private HttpStatus httpStatus = HttpStatus.OK;

    public LoanException(LoanError loanError, HttpStatus httpStatus) {
        this.loanError = loanError;
        this.httpStatus = httpStatus;
    }
}
