package com.lilei.java8.OptionalExplain;

import java.util.Optional;

/**
 * @description: Optional In Action
 * @author: Mr.Li
 * @date: Created in 2020/1/8 11:24
 * @version: 1.0
 * @modified By:
 */
public class OptionalInAction {

    public static void main(String[] args) {
        System.out.println(getInsuranceNameByOptional(null));

        Optional.ofNullable(getInsuranceNameByOptional(null)).ifPresent(System.out::println);
    }

    private static String getInsuranceNameByOptional(Person person) {
        /**
         * flatMap(Function<? super T,Optional<U>> mapper)
         * If a value is present, apply the provided Optional-bearing mapping function to it, return that result, otherwise return an empty Optional.
         *
         * public<U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper) {
         *     Objects.requireNonNull(mapper);
         *     if (!isPresent())
         *         return empty();
         *     else {
         *         return Objects.requireNonNull(mapper.apply(value));
         *     }
         * }
         */
        return Optional.ofNullable(person).flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }
}
