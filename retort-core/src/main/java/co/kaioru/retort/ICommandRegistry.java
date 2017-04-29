package co.kaioru.retort;

import co.kaioru.retort.exception.CommandException;

public interface ICommandRegistry<I extends ICommandContext, O> extends ICommand<I, O> {

    O execute(I i, String text) throws CommandException;

}
