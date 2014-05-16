package com.amazonnext.spring.pojo;

import org.hibernate.search.bridge.StringBridge;
import org.joda.time.DateTime;

public class DateTimeBridge implements StringBridge{

	@Override
	public String objectToString(Object arg0) {
		return ((DateTime)arg0).toString("MMM-dd-yyyy");
	}

}
