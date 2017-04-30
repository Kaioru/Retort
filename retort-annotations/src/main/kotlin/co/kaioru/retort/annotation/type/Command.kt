package co.kaioru.retort.annotation.type

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
annotation class Command(val value: String)