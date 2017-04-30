package co.kaioru.retort.annotation.builder;

import co.kaioru.retort.ICommand;
import co.kaioru.retort.ICommandContext;
import co.kaioru.retort.annotation.BaseAnnotationFactory;
import co.kaioru.retort.annotation.IAnnotationAdapter;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BaseAnnotationFactoryBuilder<I extends ICommandContext, O> extends BaseAnnotationFactory<I, O> implements IAnnotationFactoryBuilder<I, O> {

    private final List<Object> objects;

    public BaseAnnotationFactoryBuilder() {
        this.objects = new ArrayList<>();
    }

    @Override
    public IAnnotationFactoryBuilder<I, O> withAdapter(IAnnotationAdapter<I, O> adapter) {
        this.registerAdapter(adapter);
        return this;
    }

    @Override
    public IAnnotationFactoryBuilder<I, O> withObject(Object object) {
        this.getObjects().add(object);
        return this;
    }

    @Override
    public Collection<ICommand<I, O>> build() {
        return getObjects().stream()
                .map(this::generate)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

}
