package com.advise_clothes.project_advise_clothes.entity.config;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Entity를 효과적으로 다루기 위한 클래스
 */
public class EntityTools {
    /**
     * Entity to Map
     * @param entity AuditingEntity를 상속받는 Object
     * @return param == null 제외한 Key(param) : Value
     * @throws Exception
     * @author 임리을
     */
    public static Map<String, Object> toMap(AuditingEntity entity) throws Exception {
        Map<String, Object> result = new HashMap<>();

        BeanInfo info = Introspector.getBeanInfo(entity.getClass());

        Arrays.stream(info.getPropertyDescriptors()).forEach(x ->
            {
                try {
                    String key = replaceGetterToParamName(x.getReadMethod().getName().replace("get", ""));
                    Object value = x.getReadMethod().invoke(entity);
                    if (value != null) { result.put(key, value); }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

//        /* stream 쓰기 전 */
//        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
//            String key = toLowerCaseFirst(pd.getReadMethod().getName().replace("get", ""));
//            Object value = pd.getReadMethod().invoke(obj);
//            result.put(key, value);
//        }
        return result;
    }

    public static boolean isParams(AuditingEntity entity, String[] arr) throws Exception {
        return Arrays.stream(arr).allMatch(x -> {
            try {
                return toMap(entity).get(x) != null;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    /* private */
    private static String toLowerCaseFirst(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    private static String replaceGetterToParamName(String str) {
        return toLowerCaseFirst(str.replace("get", ""));
    }
}