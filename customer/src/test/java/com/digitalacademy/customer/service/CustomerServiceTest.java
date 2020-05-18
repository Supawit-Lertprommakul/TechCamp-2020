package com.digitalacademy.customer.service;

import com.digitalacademy.customer.model.Customer;
import com.digitalacademy.customer.repositories.CustomerRepository;
import com.digitalacademy.customer.support.CustomerSupportTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerService(customerRepository);
    }

    @DisplayName("Test get all customer should return list")
    @Test
    void testGetAllCustomer() {
        when(customerRepository.findAll()).thenReturn(CustomerSupportTest.getCustomerList());
        List<Customer> resp = customerService.getCustomerList();

        assertEquals(1,resp.get(0).getId().intValue());
        assertEquals("Mick",resp.get(0).getFirstName());
        assertEquals("Mouse",resp.get(0).getLastName());
        assertEquals("mick.mouse@test.com",resp.get(0).getEmail());
        assertEquals("0988888888",resp.get(0).getPhone());
        assertEquals(20,resp.get(0).getAge().intValue());

        assertEquals(2,resp.get(1).getId().intValue());
        assertEquals("Minnie",resp.get(1).getFirstName());
        assertEquals("Mouse",resp.get(1).getLastName());
        assertEquals("minnie.mouse@test.com",resp.get(1).getEmail());
        assertEquals("0988888888",resp.get(1).getPhone());
        assertEquals(19,resp.get(1).getAge().intValue());
    }

    @DisplayName("Test get all customer by id should return list")
    @Test
    void testGetAllCustomerById() {
        Long reqParam = 1L;
        when(customerRepository.findAllById(reqParam)).thenReturn(CustomerSupportTest.getCustomerList().get(0));
        Customer resp = customerService.getCustomer(reqParam);

        assertEquals(1,resp.getId().intValue());
        assertEquals("Mick",resp.getFirstName());
        assertEquals("Mouse",resp.getLastName());
        assertEquals("mick.mouse@test.com",resp.getEmail());
        assertEquals("0988888888",resp.getPhone());
        assertEquals(20,resp.getAge().intValue());
    }

    @DisplayName("Test get all customer by name should return")
    @Test
    void testGetAllCustomerByName() {
        String name = "Mick";
        when(customerRepository.findByFirstName(name)).thenReturn(CustomerSupportTest.getCustomerNameMickList());
        List<Customer> resp = customerService.getCustomerName(name);

        assertEquals(1,resp.get(0).getId().intValue());
        assertEquals("Mick",resp.get(0).getFirstName());
        assertEquals("Mouse",resp.get(0).getLastName());
        assertEquals("mick.mouse@test.com",resp.get(0).getEmail());
        assertEquals("0988888888",resp.get(0).getPhone());
        assertEquals(20,resp.get(0).getAge().intValue());

        assertEquals(2,resp.get(1).getId().intValue());
        assertEquals("Mick",resp.get(1).getFirstName());
        assertEquals("Minnie",resp.get(1).getLastName());
        assertEquals("minnie.mouse@test.com",resp.get(1).getEmail());
        assertEquals("0988888888",resp.get(1).getPhone());
        assertEquals(19,resp.get(1).getAge().intValue());
    }


    @DisplayName("Test create customer should return new customer")
    @Test
    void testCreateCustomer() {
        Customer reqCustomer = new Customer();
        reqCustomer.setId(1L);
        reqCustomer.setFirstName("Flint");
        reqCustomer.setLastName("Stone");
        reqCustomer.setEmail("flint.stone@test.com");
        reqCustomer.setPhone("0988888888");
        reqCustomer.setAge(26);

        when(customerRepository.save(reqCustomer)).thenReturn(CustomerSupportTest.getNewCustomer());
        Customer resp = customerService.createCustomer(reqCustomer);

        assertEquals(1,resp.getId().intValue());
        assertEquals("Flint",resp.getFirstName());
        assertEquals("Stone",resp.getLastName());
        assertEquals("flints.stone@test.com",resp.getEmail());
        assertEquals("0988888888",resp.getPhone());
        assertEquals(26,resp.getAge().intValue());
    }

    @DisplayName("Test update customer should return true")
    @Test
    void testUpdateCustomer() {
        Long reqId = 1L;
        Customer reqCustomer = new Customer();
        reqCustomer.setId(1L);
        reqCustomer.setFirstName("Flint");
        reqCustomer.setLastName("Stone");
        reqCustomer.setEmail("flints.stone@test.com");
        reqCustomer.setPhone("0988888888");
        reqCustomer.setAge(26);
        when(customerRepository.findAllById(reqId)).thenReturn(CustomerSupportTest.getNewCustomer());
        when(customerRepository.save(reqCustomer)).thenReturn(CustomerSupportTest.getUpdateCustomer());
        Customer resp = customerService.updateCustomer(reqId,reqCustomer);
        assertEquals(1,resp.getId().intValue());
        assertEquals("Tangmay",resp.getFirstName());
        assertEquals("AiAi",resp.getLastName());
        assertEquals("Tangmay.AiAi@test.com",resp.getEmail());
        assertEquals("0988888888",resp.getPhone());
        assertEquals(23,resp.getAge().intValue());
    }

    @DisplayName("Test update customer should return fail")
    @Test
    void testUpdateCustomerFail() {
        Long reqId = 4L;
        Customer reqCustomer = new Customer();
        reqCustomer.setId(1L);
        reqCustomer.setFirstName("Flint");
        reqCustomer.setLastName("Stone");
        reqCustomer.setEmail("flints.stone@test.com");
        reqCustomer.setPhone("0988888888");
        reqCustomer.setAge(26);

        when(customerRepository.findAllById(reqId)).thenReturn(null);
        Customer resp = customerService.updateCustomer(reqId, reqCustomer);
        assertEquals(null, resp);
    }

    @DisplayName("Test delete customer should return true")
    @Test
    void testDeleteCustomer() {
        Long reqId = 1L;

        doNothing().when(customerRepository).deleteById(reqId);
        boolean resp = customerService.deleteById(reqId);
        assertTrue(resp);
    }

    @DisplayName("Test delete customer should return fail")
    @Test
    void testDeleteCustomerFail() {
        Long reqId = 1L;

        doThrow(EmptyResultDataAccessException.class).when(customerRepository).deleteById(reqId);
        boolean resp = customerService.deleteById(reqId);
        assertFalse(resp);
    }

}
