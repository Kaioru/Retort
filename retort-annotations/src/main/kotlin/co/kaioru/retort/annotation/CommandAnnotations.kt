package co.kaioru.retort.annotation

@Repeatable
@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
annotation class Alias(val value: String)

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
annotation class Command(val value: String)

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
annotation class Description(val value: String)