package co.kaioru.retort;

import java.io.Serializable;
import java.util.LinkedList;

public interface ICommandContext extends Serializable {

    LinkedList<String> getArgs();

}
