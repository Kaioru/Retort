package co.kaioru.retort;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public interface ICommandContext extends Serializable {

    LinkedList<String> getArgs();

}
