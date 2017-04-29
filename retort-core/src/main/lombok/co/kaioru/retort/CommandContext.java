package co.kaioru.retort;

import lombok.Getter;

import java.util.LinkedList;

@Getter
public class CommandContext implements ICommandContext {

    private final LinkedList<String> args;

    protected CommandContext() {
        this.args = new LinkedList<>();
    }

}
