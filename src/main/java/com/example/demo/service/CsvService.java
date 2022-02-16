package com.example.demo.service;

import java.text.ParseException;
import java.util.List;

import com.example.demo.dto.CsvRecord;

public interface CsvService {
	public CsvRecord getRecordById(String id);

	public List<CsvRecord> getRecordByCityOrDate(String section);

}
