package com.digitalacademy.customer.controller;

import com.digitalacademy.customer.model.Customer;
import com.digitalacademy.customer.service.CustomerService;
import com.digitalacademy.customer.support.CustomerSupportTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    private MockMvc mvc;

    public static final String urlCustomerList = "/customer/list/";
    public static final String urlCustomer = "/customer";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        customerController = new CustomerController(customerService);
        mvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @DisplayName("Test get customer should return list")
    @Test
    void testGetCustomerList() throws Exception{
        when(customerService.getCustomerList()).thenReturn(CustomerSupportTest.getCustomerList());

        MvcResult mvcResult = mvc.perform(get(urlCustomerList))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        JSONArray jsonArray = new JSONArray((mvcResult.getResponse().getContentAsString()));
        assertEquals("1",jsonArray.getJSONObject(0).get("id").toString());
        assertEquals("Mick",jsonArray.getJSONObject(0).get("firstName"));
        assertEquals("Mouse",jsonArray.getJSONObject(0).get("lastName"));
        assertEquals("mick.mouse@test.com",jsonArray.getJSONObject(0).get("email"));
        assertEquals("0988888888",jsonArray.getJSONObject(0).get("phone"));
        assertEquals(20,jsonArray.getJSONObject(0).get("age"));

        assertEquals(2,jsonArray.getJSONObject(1).get("id"));
        assertEquals("Minnie",jsonArray.getJSONObject(1).get("firstName"));
        assertEquals("Mouse",jsonArray.getJSONObject(1).get("lastName"));
        assertEquals("minnie.mouse@test.com",jsonArray.getJSONObject(1).get("email"));
        assertEquals("0988888888",jsonArray.getJSONObject(1).get("phone"));
        assertEquals(19,jsonArray.getJSONObject(1).get("age"));
    }

    @DisplayName("Test get customer by id should return customer")
    @Test
    void testGetCustomerById() throws Exception{
        Long reqId = 1L;
        when(customerService.getCustomer(reqId)).thenReturn(CustomerSupportTest.getNewCustomer());

        MvcResult mvcResult = mvc.perform(get(urlCustomer + "/" + reqId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals("1",jsonObject.get("id").toString());
        assertEquals("Flint",jsonObject.get("firstName"));
        assertEquals("Stone",jsonObject.get("lastName"));
        assertEquals("flints.stone@test.com",jsonObject.get("email"));
        assertEquals("0988888888",jsonObject.get("phone"));
        assertEquals(26,jsonObject.get("age"));
    }

    @DisplayName("Test get customer by id should return not found")
    @Test
    void testGetCustomerByIdNotFound() throws Exception{
        Long reqId = 5L;
        MvcResult mvcResult = mvc.perform(get(urlCustomer + "/" + reqId))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @DisplayName("Test get customer by name should return customer")
    @Test
    void testGetCustomerByName() throws Exception{
        String reqName = "Tim";
        when(customerService.getCustomerName(reqName)).thenReturn(CustomerSupportTest.getCustomerNameMickList());

        MvcResult mvcResult = mvc.perform(get(urlCustomer + "?name=" + reqName))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
        assertEquals("1",jsonArray.getJSONObject(0).get("id").toString());
        assertEquals("Mick",jsonArray.getJSONObject(0).get("firstName"));
        assertEquals("Mouse",jsonArray.getJSONObject(0).get("lastName"));
        assertEquals("mick.mouse@test.com",jsonArray.getJSONObject(0).get("email"));
        assertEquals("0988888888",jsonArray.getJSONObject(0).get("phone"));
        assertEquals(20,jsonArray.getJSONObject(0).get("age"));

        assertEquals(2,jsonArray.getJSONObject(1).get("id"));
        assertEquals("Mick",jsonArray.getJSONObject(1).get("firstName"));
        assertEquals("Minnie",jsonArray.getJSONObject(1).get("lastName"));
        assertEquals("minnie.mouse@test.com",jsonArray.getJSONObject(1).get("email"));
        assertEquals("0988888888",jsonArray.getJSONObject(1).get("phone"));
        assertEquals(19,jsonArray.getJSONObject(1).get("age"));
    }


    @DisplayName("Test get customer by name should return not found")
    @Test
    void testGetCustomerByNameNotFound() throws  Exception{
        String reqName = "Ryan";
        MvcResult mvcResult = mvc.perform(get(urlCustomer + "?name=" + reqName))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @DisplayName("Test get customer by name should return not found")
    @Test
    void testGetCustomerByNameEmpty() throws  Exception{
        String reqName = "Ryan";
        when(customerService.getCustomerName(reqName)).thenReturn(null);
        MvcResult mvcResult = mvc.perform(get(urlCustomer + "?name=" + reqName))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @DisplayName("Test create customer should return success")
    @Test
    void testCreateCustomer() throws Exception{
        Customer reqCustomer = CustomerSupportTest.createNewCustomer();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(reqCustomer);

        when(customerService.createCustomer(reqCustomer)).thenReturn(
                CustomerSupportTest.responseCreateNewCustomer()
        );

        MvcResult mvcResult = mvc.perform(post(urlCustomer)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated())
                .andReturn();

        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals("8",jsonObject.get("id").toString());
        assertEquals("Tim",jsonObject.get("firstName"));
        assertEquals("Tim",jsonObject.get("lastName"));
        assertEquals("tim.tim@test.com",jsonObject.get("email"));
        assertEquals("0988888888",jsonObject.get("phone"));
        assertEquals(24,jsonObject.get("age"));

        verify(customerService, times(1)).createCustomer(reqCustomer);
    }

    @DisplayName("Test create customer with first name is empty should")
    @Test
    void testCreateCustomerWithNameEmpty() throws Exception{
        Customer reqCustomer = CustomerSupportTest.createNewCustomer();
        reqCustomer.setFirstName("");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(reqCustomer);

        when(customerService.createCustomer(reqCustomer)).thenReturn(
                CustomerSupportTest.responseCreateNewCustomer()
        );

        MvcResult mvcResult = mvc.perform(post(urlCustomer)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals("Validation failed for argument [0] in public org.springframework.http.ResponseEntity<?> com.digitalacademy.customer.controller.CustomerController.createCustomer(com.digitalacademy.customer.model.Customer): [Field error in object 'customer' on field 'firstName': rejected value []; codes [Size.customer.firstName,Size.firstName,Size.java.lang.String,Size]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [customer.firstName,firstName]; arguments []; default message [firstName],100,1]; default message [Please type your first name size between 1 - 100]] "
                ,mvcResult.getResolvedException().getMessage());
    }

    @DisplayName("Test update customer should return success")
    @Test
    void testUpdateCustomer() throws Exception{
        Customer reqCustomer = CustomerSupportTest.getUpdateCustomer();
        Long reqId = 1L;

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(reqCustomer);

        when(customerService.updateCustomer(reqId, reqCustomer)).thenReturn(CustomerSupportTest.getUpdateCustomer());

        MvcResult mvcResult = mvc.perform(put(urlCustomer + "/" + reqId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();

        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals("1",jsonObject.get("id").toString());
        assertEquals("Tangmay",jsonObject.get("firstName"));
        assertEquals("AiAi",jsonObject.get("lastName"));
        assertEquals("Tangmay.AiAi@test.com",jsonObject.get("email"));
        assertEquals("0988888888",jsonObject.get("phone"));
        assertEquals(23,jsonObject.get("age"));

        verify(customerService, times(1)).updateCustomer(reqId, reqCustomer);
    }

    @DisplayName("Test update customer should return not found")
    @Test
    void testUpdateCustomerIdNotFound() throws Exception{
        Customer reqCustomer = CustomerSupportTest.getUpdateCustomer();
        Long reqId = 1L;

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(reqCustomer);

        when(customerService.updateCustomer(reqId, reqCustomer)).thenReturn(null);

        MvcResult mvcResult = mvc.perform(put(urlCustomer + "/" + reqId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isNotFound())
                .andReturn();

        verify(customerService, times(1)).updateCustomer(reqId, reqCustomer);
    }

    @DisplayName("Test update customer with path id not send")
    @Test
    void testUpdateCustomerWithPathIdNotSend() throws Exception{
        Customer reqCustomer = CustomerSupportTest.getUpdateCustomer();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(reqCustomer);

        MvcResult mvcResult = mvc.perform(put(urlCustomer + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isMethodNotAllowed())
                .andReturn();
    }

    @DisplayName("Test delete customer return success")
    @Test
    void testDeleteCustomer() throws Exception{
        Long reqId=4L;
        when(customerService.deleteById(reqId)).thenReturn(true);
        MvcResult mvcResult = mvc.perform(delete(urlCustomer + "/"+ reqId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        verify(customerService,times(1)).deleteById(reqId);
    }

    @DisplayName("Test delete customer should not found")
    @Test
    void testDeleteCustomerNotFound() throws Exception{
        Long reqId=4L;
        when(customerService.deleteById(reqId)).thenReturn(false);
        MvcResult mvcResult = mvc.perform(delete(urlCustomer + "/"+ reqId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
        verify(customerService,times(1)).deleteById(reqId);
    }

}
