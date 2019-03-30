package com.payment.seffaf.operation;

import com.payment.seffaf.utils.UtilsForSpring;

import java.util.HashMap;
import java.util.Map;

/**
 * enbiya on 30.03.2019
 */
public class SeffafOperationRegistry {

    private static Map<String, Map<String, Class>> operations = new HashMap<>();

    public static Class get(String sourceId, String operation) {
        return operations.get(sourceId).get(operation);
    }

    public static void register(String sourceId, String operation, Class clazz) {
        operations.putIfAbsent(sourceId, new HashMap<>());
        operations.get(sourceId).put(operation, clazz);
    }

}
