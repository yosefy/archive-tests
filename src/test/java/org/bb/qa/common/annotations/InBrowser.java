package org.bb.qa.common.annotations;

import org.bb.qa.common.configuration.Emulator;
import org.bb.qa.common.drivers.Browser;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE})
public @interface InBrowser {

    String browserSize() default "";

    Browser browser() default Browser.DEFAULT;

    Emulator emulator() default Emulator.DEFAULT;
}
