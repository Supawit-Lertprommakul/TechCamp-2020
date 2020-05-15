package com.digitalacademy.loan.exception;

import com.digitalacademy.loan.constants.LoanError;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class LoanException extends Exception{
    private LoanError loanError;
    private HttpStatus httpStatus = HttpStatus.OK;

    public LoanException(LoanError loanError, HttpStatus httpStatus) {
        this.loanError = loanError;
        this.httpStatus = httpStatus;
    }
}
