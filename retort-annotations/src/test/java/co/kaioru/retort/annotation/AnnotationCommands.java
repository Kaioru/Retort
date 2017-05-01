package co.kaioru.retort.annotation;

import co.kaioru.retort.impl.CommandContext;

public class AnnotationCommands {

    @Command("true")
    public Boolean trueCommand(CommandContext context) {
        return true;
    }

    @Command("false")
    @Alias("nottrue")
    @Description("false")
    public Boolean falseCommand(CommandContext context) {
        return false;
    }

}
