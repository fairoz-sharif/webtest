package com.tss.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class CommonUtil {

	public static String replaceExpr(String value) {
		long seconds = LocalDateTime.now().toEpochSecond( ZoneOffset.UTC );
		return value.replace("{time}", (""+seconds));
	}

}
