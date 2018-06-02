import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Container implements IContainer{

    private Map<Embelished<Object>, Set<Class<?>>> context = new HashMap<>();
    
    @Override
    public <T> Embelished<T> instantiate(Class<T> clazz) throws Exception 
    {
        T newInstance = clazz.newInstance();
        Embelished<T> output = new Embelished(newInstance);
        return output;
    }

    @Override
    public Set<Object> find(Class<?>... properties) {
        Set<Class<?>> propSet = new HashSet<>();
        propSet.addAll(Arrays.asList(properties));
        
        Set<Object> set = new HashSet<>();
        
        context.keySet().stream().forEach((o) -> {
            Set<Class<?>> s = context.get(o);
            if (s.containsAll(propSet)) {
                set.add(o);
            }
        });
        return set;
    }

    @Override
    public void bind(Object instance, Class<?>... properties) {
        
        Embelishede<Object> mInstance = new Embelished(instance);
        
        Set<Class<?>> propSet = new HashSet<>();
        propSet.addAll(Arrays.asList(properties));
        this.context.put(mInstance, propSet);
        
        mInstance.notifyInstanceAdded(instance, propSet);
    }

    @Override
    public void update(Object instance, Class<?>... properties) {
        Embelished<T> mInstance;
        mInstance.notifyInstanceUpdated(instance, properties);
    }

    @Override
    public void unbind(Object instance) {
        Set<Class<?>> get = this.context.get(new Embelished(instance)); //equality?
        mInstance.notifyInstanceRemoved(instance);
    }
}
