package com.lilei.java8.OptionalExplain;

import java.util.Optional;

/**
 * @description: Optional Usage
 * @author: Mr.Li
 * @date: Created in 2020/1/7 16:01
 * @version: 1.0
 * @modified By:
 */
public class OptionalUsage {

    public static void main(String[] args) {
        // empty(): Returns an empty Optional instance.
        Optional<Insurance> insuranceOptional = Optional.empty();
        // get(): If a value is present in this Optional, returns the value, otherwise throws NoSuchElementException.
        // 报错: NoSuchElementException: No value present
        // insuranceOptional.get();

        // of(T value): Returns an Optional with the specified present non-null value.
        // 相当于对Insurance进行了封装
        Optional<Insurance> insuranceOptional1 = Optional.of(new Insurance());
        insuranceOptional1.get();

        /**
         * ofNullable(T value):
         * Returns an Optional describing the specified value, if non-null, otherwise returns an empty Optional.
         *
         * public static <T> Optional<T> ofNullable(T value) {
         *     return value == null ? empty() : of(value);
         * }
         */
        Optional<Insurance> insuranceOptional2 = Optional.ofNullable(new Insurance());
        insuranceOptional2.get();

        /**
         * orElseGet(Supplier<? extends T> other):
         * Return the value if present, otherwise invoke other and return the result of that invocation.
         *
         * public T orElseGet(Supplier<? extends T> other) {
         *     return value != null ? value : other.get();
         * }
         */
        Insurance insurance = insuranceOptional.orElseGet(Insurance::new);
        System.out.println("insurance: " + insurance.getName());

        /**
         * orElse(T other):
         * Return the value if present, otherwise return other.
         *
         * public T orElse(T other) {
         *     return value != null ? value : other;
         * }
         */
        Insurance insurance1 = insuranceOptional.orElse(new Insurance());
        System.out.println("insurance1: " + insurance1.getName());

        /**
         * orElseThrow(Supplier<? extends X> exceptionSupplier):
         * Return the contained value, if present, otherwise throw an exception to be created by the provided supplier.
         *
         * public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
         *     if (value != null) {
         *         return value;
         *     } else {
         *         throw exceptionSupplier.get();
         *     }
         * }
         */
        // Insurance insurance2 = insuranceOptional.orElseThrow(RuntimeException::new);
        // Insurance insurance3 = insuranceOptional.orElseThrow(() -> new RuntimeException("Not have reference."));

        /**
         * filter(Predicate<? super T> predicate):
         * If a value is present, and the value matches the given predicate, return an Optional describing the value, otherwise return an empty Optional.
         *
         * public Optional<T> filter(Predicate<? super T> predicate) {
         *     Objects.requireNonNull(predicate);
         *     if (!isPresent())
         *         return this;
         *     else
         *         return predicate.test(value) ? this : empty();
         * }
         */
        Insurance insurance2 = insuranceOptional1.filter(i -> i.getName() == null).get();
        System.out.println(insurance2);

        /**
         * map(Function<? super T,? extends U> mapper)
         * If a value is present, apply the provided mapping function to it, and if the result is non-null, return an Optional describing the result.
         *
         * public<U> Optional<U> map(Function<? super T, ? extends U> mapper) {
         *     Objects.requireNonNull(mapper);
         *     if (!isPresent())
         *         return empty();
         *     else {
         *         return Optional.ofNullable(mapper.apply(value));
         *     }
         * }
         */
        Optional<String> nameOptional = insuranceOptional1.map(i -> i.getName());
        System.out.println(nameOptional.orElse("Empty Value."));

        /**
         * isPresent(): Return true if there is a value present, otherwise false.
         *
         * public boolean isPresent() {
         *     return value != null;
         * }
         */
        System.out.println(nameOptional.isPresent());

        System.out.println(getInsuranceName(null));
        System.out.println(getInsuranceNameByOptional(null));
    }

    private static String getInsuranceName(Insurance insurance) {
        if (insurance == null) {
            return "UNKNOWN";
        }
        return insurance.getName();
    }

    private static String getInsuranceNameByOptional(Insurance insurance) {
        return Optional.ofNullable(insurance).map(Insurance::getName).orElse("UNKNOWN");
    }
}