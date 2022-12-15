package org.bambrikii.examples.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

public class AspectFieldResolver {
    static Object findValue(JoinPoint joinPoint, Map<Class<?>, Field> map, String... fieldNames) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            Class<?> cls = arg.getClass();
            for (String fieldName : fieldNames) {
                Field field = ensure(map, arg, cls, fieldName);
                if (field == null) {
                    continue;
                }
                map.put(cls, field);
                return ReflectionUtils.getField(field, arg);
            }
            if (!map.containsKey(cls)) {
                map.put(cls, null);
            }
        }
        return null;
    }

    private static Field ensure(Map<Class<?>, Field> map, Object arg, Class<?> cls, String fieldName) {
        if (map.containsKey(cls)) {
            return map.get(cls);
        }
        Field field = doesObjectContain(arg, fieldName);
        if (field == null) {
            return null;
        }
        map.put(cls, field);
        return field;
    }

    public static Field doesObjectContain(Object object, String fieldName) {
        Class<?> objectClass = object.getClass();
        for (Field field : objectClass.getDeclaredFields()) {
            if (fieldName.equals(field.getName())) {
                field.setAccessible(true);
                return field;
            }
        }
        return null;
    }
}
