import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Embelished<T>{
    private T instance;
    Set<Dependency> dependencies = new HashSet<>();

    public Embelished(T instance) {
        this.instance = instance;
        this.detectFields();
    }
    
    public T getInstance()
    {
        return instance;
    }


    public void notifyInstanceAdded(Object instance, Set<Class<?>> properties)
    {
        
    }
    
    public void notifyInstanceUpdated(Object instance, Set<Class<?>> properties)
    {
        
    }
    
    public void notifyInstanceRemoved(Object instance)
    {
        
    }
    
    // dependency
    public boolean isResolved()
    {
        
    }
    
    public void destroy()
    {
        
    }
    
    private void detectFields()
    {
        Class<? extends Object> clazz = instance.getClass();
        Set<Field> iocFields;
        iocFields = Arrays.stream(clazz.getDeclaredFields())
                .filter(f->f.isAnnotationPresent(Inject.class))
                .collect(Collectors.toSet());
        
        iocFields.stream().forEach((Field f) -> {
            Inject i = f.getAnnotation(Inject.class);
            Dependency d = new Dependency(f, i);
            dependencies.add(d);
        });
    }
    
    private void setField(Field field, Object injectedValue)
    {
        try{
            // om het te laten werken met private fields
            field.setAccessible(true);
            if (Collection.class.equals(field.getType()))
            {
                // Initialize the collection if null
                if (field.get(instance) == null)
                {
                    field.set(instance, new HashSet());
                }
                ((Collection) field.get(instance)).add(injectedValue);
            }
            else if (field.get(instance) == null)
            {
                field.set(instance, injectedValue);
            }
                    
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
