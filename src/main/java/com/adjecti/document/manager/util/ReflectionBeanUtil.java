package com.adjecti.document.manager.util;
import java.lang.reflect.Field;
import java.util.Map;

public class ReflectionBeanUtil {

public static void mapClassFields(Map<Object, Object> map, Object entity) {
		
		for (Field field : entity.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			String fieldType = field.getType().getSimpleName();
			try {

				if (field.getName() != "id" && map.get(field.getName()) != null) {

					if (fieldType.equals("long")) {

						String a = "";
						if (map.get(field.getName()).toString() != "") {
							a = map.get(field.getName()).toString();
						} else {
							a = "0";
						}
						field.set(entity, Long.parseLong(a));

					} else if (fieldType.equals("int")) {

						field.set(entity, Integer.parseInt(map.get(field.getName()).toString()));

					} else if (fieldType.equals("float")) {

						field.set(entity, Float.parseFloat(map.get(field.getName()).toString()));

					}

					else {
						System.out.println(field.getType()+"---"+field.getName());
						field.set(entity, map.get(field.getName()));
					}
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

	}
}
