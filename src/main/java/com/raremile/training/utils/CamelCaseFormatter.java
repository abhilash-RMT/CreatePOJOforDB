package com.raremile.training.utils;

import com.google.common.base.CaseFormat;

public class CamelCaseFormatter {

	public static String getCamelCaseString(String underscoreSeparatedString) {
		return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, underscoreSeparatedString);
	}
}
