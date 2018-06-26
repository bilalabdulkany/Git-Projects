package com.ef.parser.data.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Validator {

	public static boolean dateValidator(String date) {
		String format = "yyyy-MM-dd HH:mm:ss.SSS";
		SimpleDateFormat dateformat = new SimpleDateFormat(format);
		try {
			dateformat.parse(date);
		} catch (ParseException e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	public static boolean thresholdValidator(String threshold) {
		try{Integer.parseInt(threshold);}
		catch(NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static boolean durationValidator(String threshold) {
		if (threshold.trim().equalsIgnoreCase("daily") || threshold.trim().equalsIgnoreCase("hourly"))
			return true;
		else
			return false;
	}
}
