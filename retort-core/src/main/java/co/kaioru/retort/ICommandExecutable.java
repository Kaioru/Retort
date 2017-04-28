package co.kaioru.retort;

import co.kaioru.retort.exception.CommandException;

public interface ICommandExecutable<I extends ICommandContext, O> {

    O execute(I i) throws CommandException;

}
