package cn.kbyte.utils.model;

import java.util.Objects;
import java.util.function.Function;

public class Try {

    public static <T, R> Function<T, R> of(UncheckedFunction<T, R> mapper) {
        Objects.requireNonNull(mapper);
        return t -> {
            try {
                return mapper.apply(t);
            } catch (Exception ex) {
                System.out.println(t);
                throw new RuntimeException(ex);
            }
        };
    }

    @FunctionalInterface
    public static interface UncheckedFunction<T, R> {

        R apply(T t) throws Exception;
    }
}