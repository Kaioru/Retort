package co.kaioru.retort.annotatation

import co.kaioru.retort.TestCommandRegistry
import co.kaioru.retort.annotation.impl.adapter.basicGenerators
import co.kaioru.retort.annotation.impl.adapter.basicProcessors
import co.kaioru.retort.impl.CommandContext
import org.junit.Assert.assertEquals
import org.junit.Test

class KotlinAnnotationTest {
    var registry: TestCommandRegistry = TestCommandRegistry()

    @Test
    fun annotationbuilding() {
        registry.registerCommands(
                TestAnnotationFactoryBuilder()
                        .withAdapters(basicGenerators(CommandContext::class.java, Boolean::class.java))
                        .withAdapters(basicProcessors())
                        .withObjects(listOf())
                        .build())

        assertEquals(0, registry.commands.size)
    }

}