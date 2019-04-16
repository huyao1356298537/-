package com.zr.bookstore.utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.zr.bookstore.entity.PageBean;

import java.util.List;

/*
 * json数据处理的工具类
 */
public class JsonUtils {

	/**
	 * 将一个对象转换成一个json字符串
	 * @param data
	 * @return
	 */
	public static String toJson(Object data) {
		Gson gson = new Gson();
		return gson.toJson(data);
	}
	public static <T> JSONObject getJsonObject(List<T> list , PageBean pageBean) {
		Object jsonArr = JSONArray.toJSON(list);

		JSONObject array = new JSONObject();
		array.put("code",0);
		array.put("msg","");
		array.put("count",pageBean.getCount());
		array.put("data",jsonArr);
		return array;
	}

}
