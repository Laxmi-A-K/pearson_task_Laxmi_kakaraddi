package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.dto.CsvRecord;
import com.example.demo.responsemessage.ResponseMessage;
import com.example.demo.service.CsvService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CsvControllerTest {

	ObjectMapper mapper = new ObjectMapper();

	@MockBean
	private CsvService csvService;

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@BeforeEach
	void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	void testFindById() throws UnsupportedEncodingException, Exception {
		Date date = new SimpleDateFormat("dd-mm-yyyy").parse("10-04-1996");
		CsvRecord csvRecord = new CsvRecord("543", "dafs", "bang", "Kar", date);

		when(csvService.getRecordById(Mockito.anyString())).thenReturn(csvRecord);
		String contentAsString = mockMvc.perform(get("/fetch/543")).andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString();
		ResponseMessage readValue = mapper.readValue(contentAsString, ResponseMessage.class);

		assertEquals("Display Store Details", readValue.getMessage());
	}

	@Test
	void testFindByCityOrDate() throws UnsupportedEncodingException, Exception {
		Date date = new SimpleDateFormat("dd-mm-yyyy").parse("12-10-1998");
		List<CsvRecord> list = new ArrayList<CsvRecord>();
		CsvRecord file = new CsvRecord("hi", "2e", "me", "ss", date);
		list.add(file);
		when(csvService.getRecordByCityOrDate(Mockito.anyString())).thenReturn(list);
		String contentAsString = mockMvc.perform(get("/sortedRecord/hi")).andExpect(status().isOk()).andReturn()
				.getResponse().getContentAsString();
		ResponseMessage readValue = mapper.readValue(contentAsString, ResponseMessage.class);
		assertEquals("Display all the details", readValue.getMessage());
	}
}
