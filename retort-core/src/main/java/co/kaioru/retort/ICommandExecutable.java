package co.kaioru.retort;

import co.kaioru.retort.exception.CommandException;

import java.io.Serializable;

public interface ICommandExecutable<I extends ICommandContext, O> extends Serializable {

    O execute(I i) throws CommandException;

}
