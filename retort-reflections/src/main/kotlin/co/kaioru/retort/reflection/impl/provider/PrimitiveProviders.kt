package co.kaioru.retort.reflection.impl.provider

import co.kaioru.retort.impl.CommandContext
import co.kaioru.retort.reflection.IReflectionProvider
import co.kaioru.retort.reflection.impl.ReflectionProvider

class StringProvider<I : CommandContext> : ReflectionProvider<I, String>() {
    override val type: Class<String> = String::class.java

    override fun provide(input: I): String {
        return input.args.remove()
    }
}

class ByteProvider<I : CommandContext> : ReflectionProvider<I, Byte>() {
    override val type: Class<Byte> = Byte::class.java

    override fun provide(input: I): Byte {
        return input.args.remove().toByte()
    }

}

class IntProvider<I : CommandContext> : ReflectionProvider<I, Int>() {
    override val type: Class<Int> = Int::class.java

    override fun provide(input: I): Int {
        return input.args.remove().toInt()
    }
}

class LongProvider<I : CommandContext> : ReflectionProvider<I, Long>() {
    override val type: Class<Long> = Long::class.java

    override fun provide(input: I): Long {
        return input.args.remove().toLong()
    }
}

class DoubleProvider<I : CommandContext> : ReflectionProvider<I, Double>() {
    override val type: Class<Double> = Double::class.java

    override fun provide(input: I): Double {
        return input.args.remove().toDouble()
    }
}

class FloatProvider<I : CommandContext> : ReflectionProvider<I, Float>() {
    override val type: Class<Float> = Float::class.java

    override fun provide(input: I): Float {
        return input.args.remove().toFloat()
    }
}

class BooleanProvider<I : CommandContext> : ReflectionProvider<I, Boolean>() {
    override val type: Class<Boolean> = Boolean::class.java

    override fun provide(input: I): Boolean {
        return input.args.remove().toBoolean()
    }

}

fun <I : CommandContext> primitiveProviders(): Collection<IReflectionProvider<I, *>> {
    return listOf(
            StringProvider(),
            ByteProvider(),
            IntProvider(),
            LongProvider(),
            DoubleProvider(),
            FloatProvider(),
            BooleanProvider()
    )
}