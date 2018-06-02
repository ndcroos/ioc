public interface IContainer {

    <T> ManagedInstance<T> instantiate(Class<T> clazz) throws Exception ;
    
    Set<Object> find(Class<?>... properties);
    
    void bind(Object instance, Class<?>... properties);
    
    void update(Object instance, Class<?>... properties);
    
    void unbind(Object instance);
    
}
