package com.example.demo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CsvRecord;

@Service
public class CsvServiceImpl implements CsvService {

	@Override
	public CsvRecord getRecordById(String id) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/stores.csv"));
			CSVParser csvParser = new CSVParser(bufferedReader,
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
			Iterable<CSVRecord> iterable = csvParser.getRecords();
			if (iterable != null) {
				for (CSVRecord csv : iterable) {
					Date date = new SimpleDateFormat("dd-mm-yyyy").parse(csv.get("Opened Date"));
					CsvRecord csvRecord = new CsvRecord(csv.get("Store Id"), csv.get("Post Code"), csv.get("City"),
							csv.get("Address"), date);
					if (csvRecord.getStoreId().equals(id)) {
						return csvRecord;

					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CsvRecord> getRecordByCityOrDate(String section) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/stores.csv"));
			CSVParser csvParser = new CSVParser(bufferedReader,
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
			Iterable<CSVRecord> iterable = csvParser.getRecords();
			List<CsvRecord> list = new ArrayList<>();
			for (CSVRecord csvRecord : iterable) {
				Date date= new SimpleDateFormat("dd-mm-yyyy").parse(csvRecord.get("opened Date"));
				CsvRecord csv = new CsvRecord(csvRecord.get("Store Id"), csvRecord.get("Post Code"),
						csvRecord.get("City"), csvRecord.get("Address"), date);
				if (section.equalsIgnoreCase("city")) {
					list.add(csv);
				} else if (section.equalsIgnoreCase("Opened Date")) {
					list.add(csv);
				}
			}

			for (int i = 0; i < list.size(); i++) {
				if (section.equalsIgnoreCase("city")) {
					List<CsvRecord> records = list.stream().sorted((d1, d2) -> d1.getCity().compareTo(d2.getCity()))
							.collect(Collectors.toList());
					return records;
				} else if (section.equalsIgnoreCase("Opened Date")) {
					List<CsvRecord> record = list.stream()
							.sorted((d1, d2) -> d1.getOpenedDate().compareTo(d2.getOpenedDate()))
							.collect(Collectors.toList());
					return record;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
