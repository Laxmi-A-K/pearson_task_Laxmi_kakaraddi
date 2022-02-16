package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CsvRecord;
import com.example.demo.responsemessage.ResponseMessage;
import com.example.demo.service.CsvService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CsvController {
	@Autowired
	private CsvService csvService;

	@GetMapping("/fetch/{storeId}")
	public ResponseEntity<ResponseMessage> getById(@PathVariable("storeId") String storeId) {
		CsvRecord recordById = csvService.getRecordById(storeId);
		if (recordById != null) {
			log.info("Display Store Details {}", recordById);
			return new ResponseEntity<>(new ResponseMessage(false, "Display Store Details", recordById), HttpStatus.OK);
		} else {
			log.error("No Data Found with the give Store ID");
			return new ResponseEntity<>(new ResponseMessage(true, "No Data Found with the give Store ID", null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/sortedRecord/{section}")
	public ResponseEntity<ResponseMessage> getByCityOrDate(@PathVariable("section") String section) {
		List<CsvRecord> list = csvService.getRecordByCityOrDate(section);
		if (list != null) {
			log.info("Display all details {}", list);
			return new ResponseEntity<>(new ResponseMessage(false, "Display all the details", list), HttpStatus.OK);
		} else {
			log.error("No Data Found with the give City or Date");
			return new ResponseEntity<>(new ResponseMessage(false, "No Data Found with the give City or Date", null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
