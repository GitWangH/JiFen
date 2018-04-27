package com.huatek.file.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {
	public static final Gson gson = new GsonBuilder().setDateFormat(
			"yyyy-MM-dd HH:mm:ss").create();
}
