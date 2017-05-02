package co.kaioru.retort.reflection;

import co.kaioru.retort.annotation.Command;
import co.kaioru.retort.impl.CommandContext;

public class ReflectionCommands {

    @Reflect
    @Command("true")
    public Boolean getTrue() {
        return true;
    }

    @Reflect
    @Command("invert")
    public Boolean getInvert(boolean bool) {
        return !bool;
    }

    @Reflect
    @Command("error")
    public Boolean getError(CommandContext context) {
        return true;
    }

}
