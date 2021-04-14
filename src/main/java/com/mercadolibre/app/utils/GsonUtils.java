package com.mercadolibre.app.utils;

import com.google.gson.Gson;

/**
 * Utilidad usando la libreria gson
 * @author jesus
 *
 */
public class GsonUtils {

	/**
	 * Convierte de un objecto a un string
	 * @param src
	 * @return
	 */
	public static String serialize(Object src) {
		Gson gson = new Gson();
		return gson.toJson(src);
	}

	/**
	 * Convierte de string a un objecto
	 * @param <D>
	 * @param json
	 * @param dClass
	 * @return
	 */
	public static <D> D toObject(String json, Class<D> dClass) {
		Gson gson = new Gson();
		return gson.fromJson(json, dClass);
	}
	
	/**
	 * Convierte de un objecto a otro objecto
	 * @param <D>
	 * @param src
	 * @param dClass
	 * @return
	 */
	public static <D> D toObject(Object src, Class<D> dClass) {

		Gson gson = new Gson();
		String srcJson = gson.toJson(src);		
		return gson.fromJson(srcJson, dClass);
	}

}
