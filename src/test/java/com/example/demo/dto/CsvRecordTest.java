package com.example.demo.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class CsvRecordTest {

	ObjectMapper mapper = new ObjectMapper();
	String json = "{\"storeId\":\"12\",\"postCode\":\"dd\",\"city\":\"abc\",\"address\":\"gg\",\"openedDate\":822594960000}";

	@Test
	void serializeTest() throws JsonProcessingException, ParseException {
//		Date date= new SimpleDateFormat("dd-mm-yyyy").parse("26-06-1996");
//		CsvRecord  csvRecord= new CsvRecord("12", "dd", "abc", "gg", date);
//		System.out.println(mapper.writeValueAsString(csvRecord));

		CsvRecord record = mapper.readValue(json, CsvRecord.class);
		String writeValueAsString = mapper.writeValueAsString(record);
		assertEquals(json, writeValueAsString);
	}
	
	@Test
	void deserializedTest() throws JsonMappingException, JsonProcessingException {
		CsvRecord record = mapper.readValue(json, CsvRecord.class);
		assertEquals("12", record.getStoreId());
	}

}
