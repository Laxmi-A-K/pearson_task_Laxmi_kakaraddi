package com.example.demo.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CsvRecord {
	private String storeId;
	private String postCode;
	private String city;
	private String address;
	private Date openedDate;

}
