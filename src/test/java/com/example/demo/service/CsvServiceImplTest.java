package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.dto.CsvRecord;
@ExtendWith(MockitoExtension.class)
class CsvServiceImplTest {
	@InjectMocks
	private CsvServiceImpl service;

	@Test
	void testRecordById() throws ParseException {
		Date date = new SimpleDateFormat("dd-mm-yyyy").parse("26-06-1996");
		CsvRecord file = new CsvRecord("1525eec4-7bed-4597-bf5a-e06fcede5f4f", "sm", "gm", "tt", date);
		CsvRecord csvFileById = service.getRecordById(file.getStoreId());
		assertEquals("1525eec4-7bed-4597-bf5a-e06fcede5f4f", csvFileById.getStoreId());
	}

	@Test
	void testRecoreByCityOrDate() throws ParseException {
		Date date = new SimpleDateFormat("dd-mm-yyyy").parse("26-06-1996");
		CsvRecord file = new CsvRecord("1525eec4-7bed-4597-bf5a-e06fcede5f4f", "sm", "gm", "tt", date);
		List<CsvRecord> list = new ArrayList<CsvRecord>();
		list.add(file);
		assertEquals("1525eec4-7bed-4597-bf5a-e06fcede5f4f",
				service.getRecordByCityOrDate("city").get(0).getStoreId());

	}

}
