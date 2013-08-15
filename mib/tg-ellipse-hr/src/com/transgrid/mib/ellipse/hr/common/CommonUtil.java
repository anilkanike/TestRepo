package com.transgrid.mib.ellipse.hr.common;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang.StringUtils;

import com.transgrid.mib.ellipse.hr.common.AATInterfaceException;

/**
 * 
 * @author Anil Kanike
 * 
 */
public class CommonUtil {

	public static final SimpleDateFormat ellDateFormat = new SimpleDateFormat("yyyyMMdd");

	public static String generate(Object obj) throws Exception {

		StringWriter stringWriter = new StringWriter();
		try {
			final JAXBContext jc = JAXBContext.newInstance("com.transgrid.mib.ellipse.aat.generated");
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

			marshaller.marshal(obj, stringWriter);

		} catch (JAXBException e) {
			throw new AATInterfaceException("Error in generating target system XML format.", e);
		}
		return stringWriter.toString();
	}

	public static String getMajorRevNo(String majorRevNo) {
		int result;
		result = (AATConstants.MAJOR_REV_REVSD - (Integer.parseInt(majorRevNo.trim())));

		return Integer.toString(result);
	}

	public static XMLGregorianCalendar getXMLDate(String dateString) throws ParseException, DatatypeConfigurationException {
		return DatatypeFactory.newInstance().newXMLGregorianCalendar(dateString.trim());
	}

	public static String validateEmptyDate(String date) {

		if (StringUtils.isNotEmpty(date) && StringUtils.equals(date, "00000000")) {
			return "";
		}
		return date;
	}

	public static int convertToMonths(String convInd, BigDecimal number) {

		if ("D".equals(convInd)) {
			// return number/30;
		}

		return 0;
	}

	public static boolean isValidDateFormat(String text) {
		ellDateFormat.setLenient(false);
		try {
			ellDateFormat.parse(text);
			return true;
		} catch (ParseException ex) {
			return false;
		}
	}

	public static long calculateDays(Date from, Date to) {
		Calendar sDate = Calendar.getInstance();
		sDate.setTime(from);
		Calendar eDate = Calendar.getInstance();
		eDate.setTime(to);
		return daysBetween(sDate, eDate);
	}

	public static long daysBetween(Calendar startDate, Calendar endDate) {
		Calendar date = (Calendar) startDate.clone();
		long daysBetween = 0;
		while (date.before(endDate)) {
			date.add(Calendar.DAY_OF_MONTH, 1);
			daysBetween++;
		}
		return daysBetween;
	}
	
	public static String now() {
		Calendar cal = Calendar.getInstance();
		return ellDateFormat.format(cal.getTime());
	}
	
	/**
	 * method to return from the time format hh.mm/h.mm to hh:mm:ss
	 * @param time
	 * @return
	 */
	public static String formatTime(String time){
		
		SimpleDateFormat timeFormat =  new SimpleDateFormat ("HH:mm:ss");
		
		Date timeformat=null;
		try {
			timeformat = new SimpleDateFormat("hh.mm").parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	      
		return timeFormat.format(timeformat);
	}
}
