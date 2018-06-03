import java.lang.annotation.*;

// Annotates fields that should be injected.
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
    Class<?>[] filter() default{};
}
