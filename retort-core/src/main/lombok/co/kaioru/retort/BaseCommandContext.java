package co.kaioru.retort;

import lombok.Getter;

import java.util.LinkedList;

@Getter
public class BaseCommandContext implements ICommandContext {

    private final LinkedList<String> args;

    public BaseCommandContext() {
        this.args = new LinkedList<>();
    }

}
