package co.kaioru.retort;

import lombok.Getter;

import java.util.LinkedList;

@Getter
public abstract class AbstractCommandContext implements ICommandContext {

    private final LinkedList<String> args;

    protected AbstractCommandContext() {
        this.args = new LinkedList<>();
    }

}
