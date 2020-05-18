package com.digitalacademy.customer.support;

import com.digitalacademy.customer.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerSupportTest {
    public static List<Customer> getCustomerList() {
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Mick");
        customer.setLastName("Mouse");
        customer.setEmail("mick.mouse@test.com");
        customer.setPhone("0988888888");
        customer.setAge(20);
        customerList.add(customer);

        customer = new Customer();
        customer.setId(2L);
        customer.setFirstName("Minnie");
        customer.setLastName("Mouse");
        customer.setEmail("minnie.mouse@test.com");
        customer.setPhone("0988888888");
        customer.setAge(19);
        customerList.add(customer);
        return customerList;
    }

    public static Customer getNewCustomer() {
        Customer reqCustomer = new Customer();
        reqCustomer.setId(1L);
        reqCustomer.setFirstName("Flint");
        reqCustomer.setLastName("Stone");
        reqCustomer.setEmail("flints.stone@test.com");
        reqCustomer.setPhone("0988888888");
        reqCustomer.setAge(26);
        return reqCustomer;
    }

    public static Customer getUpdateCustomer() {
        Customer reqCustomer = new Customer();
        reqCustomer.setId(1L);
        reqCustomer.setFirstName("Tangmay");
        reqCustomer.setLastName("AiAi");
        reqCustomer.setEmail("Tangmay.AiAi@test.com");
        reqCustomer.setPhone("0988888888");
        reqCustomer.setAge(23);
        return reqCustomer;
    }

    public static List<Customer> getCustomerNameMickList() {
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Mick");
        customer.setLastName("Mouse");
        customer.setEmail("mick.mouse@test.com");
        customer.setPhone("0988888888");
        customer.setAge(20);
        customerList.add(customer);

        customer = new Customer();
        customer.setId(2L);
        customer.setFirstName("Minnie");
        customer.setLastName("Mouse");
        customer.setEmail("minnie.mouse@test.com");
        customer.setPhone("0988888888");
        customer.setAge(19);
        customerList.add(customer);
        return customerList;
    }
}
