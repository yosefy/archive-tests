package org.bb.qa.common;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;

public class TestContext {
    private static String methodName;
    private static Method testMethod;

    public static void writeMethodName(Method method) {
        methodName = String.format("%s.%s",
                method.getDeclaringClass().getSimpleName(),
                StringUtils.capitalize(method.getName()));
        testMethod = method;
    }

    public static String getCurrentMethodName() {
        return methodName;
    }

    public static Method getCurrentTestMethod() {
        return testMethod;
    }

}
