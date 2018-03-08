package day11;

public @interface Uniquenness {
    Constraints constrains() default @Constraints(unique = true);
}
