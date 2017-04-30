package co.kaioru.retort.annotation.type

@Repeatable
@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
annotation class Alias(val value: String)