public interface IContainer {

    <T> Embelished<T> instantiate(Class<T> clazz) throws Exception ;
    
    Set<Object> find(Class<?>... props);
    
    void bind(Object instance, Class<?>... props);
    
    void update(Object instance, Class<?>... props);
    
    void unbind(Object instance);
    
}
