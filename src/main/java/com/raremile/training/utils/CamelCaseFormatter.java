package com.raremile.training.utils;

import com.google.common.base.CaseFormat;

public class CamelCaseFormatter {

	public static String getUpperCamelCase(String underscoreSeparatedString) {
		return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, underscoreSeparatedString);
	}

	public static String getLowerCamelCase(String underscoreSeparatedString) {
		return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, underscoreSeparatedString);
	}
}
