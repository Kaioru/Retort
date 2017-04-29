package co.kaioru.retort.reflection.type;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target(METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Reflect {

    //

}
