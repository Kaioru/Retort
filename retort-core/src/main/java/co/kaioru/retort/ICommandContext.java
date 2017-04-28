package co.kaioru.retort;

import java.io.Serializable;
import java.util.Queue;

public interface ICommandContext extends Serializable {

    Queue<String> getArgs();

}
