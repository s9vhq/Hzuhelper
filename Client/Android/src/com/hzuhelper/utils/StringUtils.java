package com.hzuhelper.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	
	/**
	 * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
	 * 
	 * @param input
	 * @return boolean
	 */
	public static boolean isEmpty(String input) {
		if (input == null || "".equals(input))
			return true;

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断是不是一个合法的电子邮件地址
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (email == null || email.trim().length() == 0)
			return false;
		return Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*").matcher(email).matches();
	}

	/**
	 * 判断是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
	/**
	 * 字符串转整数
	 * 
	 * @param str
	 * @param defValue
	 * @return
	 */
	public static int toInt(String str, int defValue) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
		}
		return defValue;
	}

	/**
	 * 对象转整数
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static int toInt(Object obj) {
		if (obj == null)
			return 0;
		return toInt(obj.toString(), 0);
	}

	/**
	 * 对象转整数
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static long toLong(String obj) {
		try {
			return Long.parseLong(obj);
		} catch (Exception e) {
		}
		return 0;
	}

	/**
	 * tweet和评论中 '#' 和 '@'的转换
	 * 
	 * @param str
	 * @return
	 */
	public static String tweetContentTran(String str) {
		String head = "<font color='#507DAF'>";
		String foot = "</font>";
		String[] strs = str.split("@");
		StringBuffer sb = new StringBuffer();
		char[] chars = str.toCharArray();
		boolean tag = true;
		int index = 0;
		int len = chars.length;
		if (strs.length > 1) {
			int i = 0;
			for (i = 0; i < len; i++) {
				if (chars[i] == '@')
					if (tag) {
						sb.append(new String(chars, index, i - index));
						index = i;
						tag = false;
					} else {
						sb.append(head);
						sb.append(new String(chars, index, i - index));
						sb.append(foot);
						index = i;
						i--;
						tag = true;
					}
				else if (chars[i] == ' ' && !tag) {
					sb.append(head);
					sb.append(new String(chars, index, i - index));
					sb.append(foot);
					index = i;
					tag = true;
				}
			}
			if (!tag) {
				sb.append(head);
				sb.append(new String(chars, index, i - index));
				sb.append(foot);
			} else {
				sb.append(new String(chars, index, len - index));
			}
		} else {
			for (int i = 0; i < len; i++) {
				if (chars[i] == '#')
					if (tag) {
						sb.append(new String(chars, index, i - index));
						index = i;
						tag = false;
					} else {
						i++;
						sb.append(head);
						sb.append(new String(chars, index, i - index));
						sb.append(foot);
						index = i;
						tag = true;
					}
			}
			sb.append(new String(chars, index, len - index));
		}
		return sb.toString();
	}

	/**
	 * 字符串转布尔值
	 * 
	 * @param b
	 * @return 转换异常返回 false
	 */
	public static boolean toBool(String b) {
		try {
			return Boolean.parseBoolean(b);
		} catch (Exception e) {
		}
		return false;
	}
}
