package co.kaioru.retort.annotation;

import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

@Target(METHOD)
public @interface Description {

    String value();

}
