package edu.icet.clothify.util.email_to_sms_gatewaylayer;

import java.lang.reflect.Method;

public class AnnotationProccesor {
    private final OtpGateway otpGateway = new OtpGateway();

    public void processAnocation(Object object) throws Throwable {
        Class<?> clazz = object.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Validate.class)) {
                Validate validate = method.getAnnotation(Validate.class);
                String value = validate.value();
            }
        }
    }
}
