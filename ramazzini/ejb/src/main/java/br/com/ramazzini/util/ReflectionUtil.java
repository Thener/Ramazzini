package br.com.ramazzini.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Reflection Util.
 *
 * @author Guilherme Sakata
 */
@SuppressWarnings("unchecked")
public class ReflectionUtil {

	private ReflectionUtil() {
	}

	/**
	 *
	 * @param clazz
	 * @return
	 */
	public static ParameterizedType getParameterizedType(Class<?> clazz) {
		if (clazz.equals(Object.class)) {
			return null;
		}
		if (clazz.getGenericSuperclass() instanceof ParameterizedType) {
			return (ParameterizedType) clazz.getGenericSuperclass();
		}
		return getParameterizedType(clazz.getSuperclass());
	}

	/**
	 *
	 * @param <T>
	 * @param clazz
	 * @param fieldName
	 * @return
	 */
	public static <T> Method getGetterMethod(Class<T> clazz, String fieldName) {
		return getMethod(clazz, "get", fieldName, new Class[] {});
	}

	/**
	 *
	 * @param object
	 * @param fieldName
	 * @return
	 */
	public static Object executeGetterMethod(Object object, String fieldName) {
		try {
			Field field = getField(object, fieldName);
			if (field != null) {
				Class valueClass = field.getType();
				if (valueClass != null && List.class.isAssignableFrom(valueClass)) {
					try {
						field.setAccessible(true);
						return field.get(object);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					}
					return null;
				}
			}
		} catch (SecurityException e1) {
			e1.printStackTrace();
		}
		return executeMethod("get", object, fieldName, new Class[] {}, new Object[] {});
	}

	/**
	 *
	 * @param <T>
	 * @param clazz
	 * @param fieldName
	 * @param params
	 * @return
	 */
	public static <T> Method getSetterMethod(Class<T> clazz, String fieldName, Class... params) {
		return getMethod(clazz, "set", fieldName, params);
	}

	/**
	 *
	 * @param <T>
	 * @param object
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public static <T> Object executeSetterMethod(Object object, String fieldName, T value) {
		Class<T> valueClass = getFieldType(object, fieldName);
		if (valueClass != null && List.class.isAssignableFrom(valueClass)) {
//			valueClass = (Class<T>) List.class;
			// Altera��o para setar diretamente no atributo quando for uma lista
			try {
				Field field = getField(object, fieldName);
				if (field != null) {
					field.setAccessible(true);
					field.set(object, value);
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
			return null;
		}

		return executeMethod("set", object, fieldName, new Class[] {valueClass}, new Object[] {value});
	}

	/**
	 *
	 * @param <T>
	 * @param object
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public static <T> Object setValueInField(Object object, Field field, T value) {
		try {
			if (field != null) {
				field.setAccessible(true);
				field.set(object, value);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *
	 * @param object
	 * @param fieldName
	 * @return
	 */
	public static Class getFieldType(Object object, String fieldName) {
		object = getLastObject(object, fieldName);
		int idx = fieldName.lastIndexOf('.');
		if (idx > 0) {
			fieldName = fieldName.substring(idx + 1);
		}
		return getFieldType(object.getClass(), fieldName);
	}

	/**
	 *
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Object getLastObject(Object obj, String fieldName) {
		if (fieldName.indexOf('.') > 0) {
			String fieldNameTmp = fieldName.substring(fieldName.indexOf('.') + 1);
			fieldName = fieldName.substring(0, fieldName.indexOf('.'));

			Object tmp = executeGetterMethod(obj, fieldName);
			if (tmp != null && Collection.class.isAssignableFrom(tmp.getClass())) {
				Field field = getField(obj, fieldName);
				Class clazz = (Class) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
				try {
					tmp = clazz.newInstance();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (tmp == null) {
				tmp = newInstance(getFieldType(obj.getClass(), fieldName));
				executeSetterMethod(obj, fieldName, tmp);
			}
			obj = tmp;
			return getLastObject(obj, fieldNameTmp);
		}

		return obj;
	}

	/**
	 *
	 * @param <T>
	 * @param clazz
	 * @param params
	 * @return
	 */
	public static <T> T newInstance(Class<T> clazz, Object... params) {
		if (clazz != null) {
			ArrayList<Class> classes = new ArrayList<Class>();
			for (int i = 0; i < params.length; i++) {
				if (params[i] != null && !params[i].toString().equals("")) {
					classes.add(params[i].getClass());
				}
			}
			Constructor<T> cons = null;
			try {
				if (classes.size() > 0) {
					Class[] classArray = new Class[classes.size()];
					cons = clazz.getDeclaredConstructor(classes.toArray(classArray));
					return cons != null ? cons.newInstance(params) : null;
				} else {
					return clazz.newInstance();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 *
	 * @param object
	 * @param fieldName
	 * @return
	 */
	public static Field getField(Object object, String fieldName) {
		object = getLastObject(object, fieldName);
		int idx = fieldName.lastIndexOf('.');
		if (idx > 0) {
			fieldName = fieldName.substring(idx + 1);
		}
		try {
			return object.getClass().getDeclaredField(fieldName);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// Ignore
		}
		return null;
	}

	/**
	 *
	 * @param objectClass
	 * @param fieldName
	 * @return
	 */
	public static Class getFieldType(Class objectClass, String fieldName) {
		Class ret = null;
		try {
			Field f = objectClass.getDeclaredField(fieldName);
			ret = f.getType();
		} catch (Exception e) {
			if (objectClass != null && !objectClass.isInterface() && !Object.class.equals(objectClass)) {
				return getFieldType(objectClass.getSuperclass(), fieldName);
			}
		}

		return ret;
	}

	/**
	 *
	 * @param <T>
	 * @param clazz
	 * @param prefix
	 * @param fieldName
	 * @param params
	 * @return
	 */
	public static <T> Method getMethod(Class<T> clazz, String prefix, String fieldName, Class... params) {
		String methodName = prefix != null ? prefix + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1)
				: fieldName;
		Method m = null;
		try {
			m = clazz.getDeclaredMethod(methodName, params);
		} catch (Exception e) {
			try {
				m = clazz.getMethod(methodName, params);
			} catch (Exception e1) {
				Method[] ms = clazz.getMethods();
				for (Method mtmp : ms) {
					if (mtmp.getName().equals(methodName)) {
						m = mtmp;
						break;
					}
				}
			}
		}
		return m;
	}

	/**
	 *
	 * @param prefix
	 * @param object
	 * @param fieldName
	 * @param valueClass
	 * @param value
	 * @return
	 */
	public static Object executeMethod(String prefix, Object object, String fieldName, Class[] valueClass,
			Object[] value) {
		if (object == null) {
			return null;
		}

		Object ret = null;

		object = getLastObject(object, fieldName);
		if (object != null) {
			Class objectClass = object.getClass();

			if (fieldName.indexOf('.') > 0) {
				fieldName = fieldName.substring(fieldName.lastIndexOf('.') + 1);
			}

			Method method = getMethod(objectClass, prefix, fieldName, valueClass);
			if (method != null) {
				try {
					ret = method.invoke(object, value);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return ret;
	}


	public static final List<Field> getFieldList(Class<?> clazz) {
		return getFieldList(clazz, Object.class);
	}
	/**
	 *
	 * @param clazz
	 * @param clazzLimite
	 * @return
	 */
	public static final List<Field> getFieldList(Class<?> clazz, Class<?> clazzLimite) {
		if (clazzLimite == null || !clazzLimite.isAssignableFrom(clazz)) {
			clazzLimite = clazz.getSuperclass();
		}
		Class parentClass = clazz;
		ArrayList<Field> fieldList = new ArrayList<Field>();
		while (true) {
			if (!clazzLimite.equals(parentClass)) {
				Field[] parentFieldList = parentClass.getDeclaredFields();
				if (parentFieldList.length > 0) {
					fieldList.addAll(0, Arrays.asList(parentFieldList));
				}
			} else {
				break;
			}
			parentClass = parentClass.getSuperclass();
		}
		return fieldList;
	}
	
	public static final List<Field> getFields(Class<?> clazz, FieldFilter ff) {
		ArrayList<Field> fieldList = new ArrayList<Field>();
		Class<?> targetClass = clazz;
		do {
			ArrayList<Field> currFields = new ArrayList<Field>();
			for (Field f : targetClass.getDeclaredFields()) {
				if (ff != null && !ff.matches(f)) continue;
				currFields.add(f);
			}
			fieldList.addAll(0, currFields);
			targetClass = targetClass.getSuperclass();
		} while (targetClass != null && targetClass != Object.class);
		return fieldList;
	}

	/**
	 *
	 * @param <T>
	 * @param clazz
	 * @param list
	 * @return
	 */
	public static final <T> T groupObject(Class<T> clazz, List<T> list) {
		if (list != null && !list.isEmpty()) {
			T mainObject = list.remove(0);
			for (T t : list) {
				mainObject = groupObject(mainObject, t);
			}
			return mainObject;
		}
		return null;
	}

	private static <T> T groupObject(T mainObject, T newValue) {
		for (Field field : getFieldList(newValue.getClass(), Object.class)) {
			Object value = executeGetterMethod(newValue, field.getName());
			if (value != null) {
				if (isBasicClasses(value.getClass())) {
					Object currentValue = executeGetterMethod(mainObject, field.getName());
					if (currentValue != null) {
						throw new IllegalStateException("J� existe valor no campo '" + field.getName()
								+ "' da classe '" + mainObject.getClass() + "'");
					}
					executeSetterMethod(mainObject, field.getName(), value);
				} else {
					Object currentValue = executeGetterMethod(mainObject, field.getName());
					if (currentValue == null) {
						executeSetterMethod(mainObject, field.getName(), value);
					} else {
						executeSetterMethod(mainObject, field.getName(), groupObject(currentValue, value));
					}
				}
			}
		}
		return mainObject;
	}

	/**
	 *
	 * @param c
	 * @return
	 */
	public static boolean isBasicClasses(Class c) {
		if (c.isPrimitive()) {
			return true;
		}
		if (String.class.isAssignableFrom(c)) {
			return true;
		}
		if (Number.class.isAssignableFrom(c)) {
			return true;
		}
		if (Enum.class.isAssignableFrom(c)) {
			return true;
		}
		if (XMLGregorianCalendar.class.isAssignableFrom(c)) {
			return true;
		}
		if (Date.class.isAssignableFrom(c)) {
			return true;
		}
		if (Boolean.class.isAssignableFrom(c)) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Get all the fields which are annotated with the given annotation. Returns
	 * an empty list if none are found
	 *
	 * @return All the fields which are annotated with the given annotation
	 */
	public static List<Field> getFields(Class clazz, Class annotation) {
		List<Field> fields = new ArrayList<Field>();
		for (Class superClass = clazz; superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			for (Field field : superClass.getDeclaredFields()) {
				if (field.isAnnotationPresent(annotation)) {
					fields.add(field);
				}
			}
		}
		return fields;
	}
	

	public interface FieldFilter {
		boolean matches(Field field);
	}
}
