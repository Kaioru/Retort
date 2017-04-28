package co.kaioru.retort.annotation;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

@Target(METHOD)
@Repeatable(Aliases.class)
public @interface Alias {

    String value();

}
