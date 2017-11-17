package com.cachet.test;

import com.cachet.common.util.StringUtil;

public class StringUtilTest {

	public static void main(String[] args) {
		String fieldName = "idCardBackFile";
		if(StringUtil.equals(fieldName, "idCardBackFile")) {
			System.out.println("equals");
		}
		if(StringUtil.equalsIgnoreCase(fieldName, "idCardBackFile")) {
			System.out.println("equalsIgnoreCase");
		}
	}

}
