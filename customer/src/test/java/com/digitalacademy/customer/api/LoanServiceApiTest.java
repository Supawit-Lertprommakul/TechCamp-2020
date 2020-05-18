package com.digitalacademy.customer.api;

import com.digitalacademy.customer.model.response.GetLoanInfoResponse;
import com.digitalacademy.customer.support.LoanSupportTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LoanServiceApiTest {
    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    LoanApi loanApi;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        loanApi = new LoanApi(restTemplate);
    }

    @DisplayName("Test get loan info should return loan infomation")
    @Test
    void testGetLoanInfo() throws Exception{
        Long reqId = 1L;

        when(restTemplate.exchange(
                ArgumentMatchers.<RequestEntity<String>>any(),
                ArgumentMatchers.<Class<String>>any()
        )).thenReturn(LoanSupportTest.prepareResponseSuccess());

        GetLoanInfoResponse resp = loanApi.getLoanInfo(reqId);
        assertEquals("1",resp.getId().toString());
        assertEquals("OK",resp.getStatus());
        assertEquals("102-222-2200",resp.getAccountPayable());
        assertEquals("102-333-2020",resp.getAccountReceivable());
        assertEquals(3400000.0,resp.getPrincipalAmount());

        verify(restTemplate,times(1))
                .exchange(ArgumentMatchers.<RequestEntity<String>>any(),
                        ArgumentMatchers.<Class<String>>any());
    }

    @DisplayName("Test get loan info should return LOAN4002")
    @Test
    void testGetLoanInfoLOAN4002() throws Exception{
        Long reqId = 2L;

        when(restTemplate.exchange(
                ArgumentMatchers.<RequestEntity<String>>any(),
                ArgumentMatchers.<Class<String>>any()
        )).thenReturn(LoanSupportTest.prepareResponseEntityLOAN4002());

        GetLoanInfoResponse resp = loanApi.getLoanInfo(reqId);

        assertEquals(null,resp.getId());
        assertEquals(null,resp.getStatus());
        assertEquals(null,resp.getAccountPayable());
        assertEquals(null,resp.getAccountReceivable());
        assertEquals(0,resp.getPrincipalAmount());
    }

    @DisplayName("Test get loan info should return LOAN4001")
    @Test
    void testGetLoanInfoLOAN4001() throws Exception{
        Long reqId = 3L;

        when(restTemplate.exchange(
                ArgumentMatchers.<RequestEntity<String>>any(),
                ArgumentMatchers.<Class<String>>any()
        )).thenReturn(LoanSupportTest.prepareResponseEntityLOAN4001());

        GetLoanInfoResponse resp = loanApi.getLoanInfo(reqId);

        assertEquals(null,resp.getId());
        assertEquals(null,resp.getStatus());
        assertEquals(null,resp.getAccountPayable());
        assertEquals(null,resp.getAccountReceivable());
        assertEquals(0,resp.getPrincipalAmount());
    }

    @DisplayName("Test get loan info should return client exception")
    @Test
    void testGetLoanInfoClientException() throws Exception{
        final Long reqParam = 2L;

        when(restTemplate.exchange(
                ArgumentMatchers.<RequestEntity<String>>any(),
                ArgumentMatchers.<Class<String>>any()
        )).thenThrow(HttpClientErrorException.class);

        Exception thrown = assertThrows(Exception.class,
                () -> loanApi.getLoanInfo(reqParam),
                "Expected loanInfoById(reqParam) to throw, but it didn't");

        assertEquals("httpClientErrorException",thrown.getMessage());
        verify(restTemplate, times(1)).exchange(ArgumentMatchers.<RequestEntity<String>>any(),
                ArgumentMatchers.<Class<String>>any());
    }

    @DisplayName("Test get loan info should return server exception")
    @Test
    void testGetLoanInfoServerException() throws Exception{
        final Long reqParam = 2L;

        when(restTemplate.exchange(
                ArgumentMatchers.<RequestEntity<String>>any(),
                ArgumentMatchers.<Class<String>>any()
        )).thenThrow(HttpServerErrorException.class);

        Exception thrown = assertThrows(Exception.class,
                () -> loanApi.getLoanInfo(reqParam),
                "Expected loanInfo(reqParam) to throw, but it didn't");

        assertEquals("httpServerErrorException",thrown.getMessage());
        verify(restTemplate, times(1)).exchange(ArgumentMatchers.<RequestEntity<String>>any(),
                ArgumentMatchers.<Class<String>>any());
    }

    @DisplayName("Test get loan info should return exception")
    @Test
    void testGetLoanInfoException() throws Exception{
        final Long reqParam = 2L;

        when(restTemplate.exchange(
                ArgumentMatchers.<RequestEntity<String>>any(),
                ArgumentMatchers.<Class<String>>any()
        )).thenThrow(Exception.class);

        Exception thrown = assertThrows(Exception.class,
                () -> loanApi.getLoanInfo(reqParam),
                "Expected loanInfo(reqParam) to throw, but it didn't");

        assertEquals(null,thrown.getMessage());
        verify(restTemplate, times(1)).exchange(ArgumentMatchers.<RequestEntity<String>>any(),
                ArgumentMatchers.<Class<String>>any());
    }
}
