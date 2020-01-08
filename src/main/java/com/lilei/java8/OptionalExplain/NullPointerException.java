// package com.lilei.java8.OptionalExplain;
//
// /**
//  * @description: The traditional way to avoid NullPointerException
//  * @author: Mr.Li
//  * @date: Created in 2020/1/7 15:43
//  * @version: 1.0
//  * @modified By:
//  */
// public class NullPointerException {
//
//     public static void main(String[] args) {
//         // String insuranceName = getInsuranceName(new Person());
//
//         // 可以使用如下的方式防止空指针异常,但是当该方法嵌套了很多类的话,那么会非常复杂
//         System.out.println(getInsuranceNameByDeepDoubts(new Person()));
//
//         // 这种方式避免了嵌套,但是还是有一些啰嗦
//         System.out.println(getInsuranceNameByMultExit(new Person()));
//
//         // Optional就因此而生
//     }
//
//     private static String getInsuranceNameByMultExit(Person person) {
//         final String defaultValue = "UNKNOWN";
//         if (person == null) {
//             return defaultValue;
//         }
//         if (person.getCar() == null) {
//             return defaultValue;
//         }
//         if (person.getCar().getInsurance() == null) {
//             return defaultValue;
//         }
//
//         return person.getCar().getInsurance().getName();
//     }
//
//     private static String getInsuranceNameByDeepDoubts(Person person) {
//         if (person != null) {
//             Car car = person.getCar();
//             if (car != null) {
//                 Insurance insurance = car.getInsurance();
//                 if (insurance != null) {
//                     return insurance.getName();
//                 }
//             }
//         }
//
//         return "UNKNOWN";
//     }
//
//     private static String getInsuranceName(Person person) {
//         return person.getCar().getInsurance().getName();
//     }
// }
