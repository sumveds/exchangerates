package com.middleware.input;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Data {

	private String sourceCurrency;
	private String targetCurrency;
	private String date = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());

	public Data() {
	}

	public String getSourceCurrency() {
		return sourceCurrency;
	}

	public void setSourceCurrency(String sourceCurrency) {
		this.sourceCurrency = sourceCurrency;
	}

	public String getTargetCurrency() {
		return targetCurrency;
	}

	public void setTargetCurrency(String targetCurrency) {
		this.targetCurrency = targetCurrency;
	}

	public String getDate() {
		
		return date;
	}
	
	@Override
	public String toString() {
		
		return sourceCurrency + "," + targetCurrency + "," + date;
	}
}
