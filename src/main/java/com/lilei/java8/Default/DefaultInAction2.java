package com.lilei.java8.Default;

/**
 * @description: Default: 目的解决接口的修改与现有的实现不兼容的问题
 * @author: Mr.Li
 * @date: Created in 2020/1/12 15:44
 * @version: 1.0
 * @modified By:
 * <p>
 * Default关键字解决多重继承冲突的三大原则详细介绍
 * There are three rules to follow when a class inherits a method with the same signature from multiple places:
 * <p>
 * 1、Classes always win. A method declaration in the class or a superclass takes priority over any default method declaration.
 * 2、Otherwise, sub-interfaces win: the method with the same signature in the most specific default-providing interface is selected.
 * (If B extends A, B is more specific than A).
 * 3、Finally, if the choice is still ambiguous, the class inheriting from multiple interfaces has to explicitly select
 * which default method implementation to use by overriding it and calling the desired method explicitly.
 */
public class DefaultInAction2 {


    public static void main(String[] args) {
        // C implements B,and class C no override,no class D
        C c1 = new C();
        c1.hello();     //  ==> B.hello

        // C implements B,and class C no override,no class D
        A c2 = new C();
        c1.hello();     //  ==> B.hello

        System.out.println("--------------------");

        // C implements A、B,and class C override hello(),no class D
        C c3 = new C();
        c3.hello();     //  ==> C.hello

        // C implements A、B,and class C override hello(),no class D
        A c4 = new C();
        c4.hello();     //  ==> C.hello

        System.out.println("--------------------");


    }

    private interface D {
        default void hello() {
            System.out.println(" ==> D.hello");
        }
    }

    private interface A {
        default void hello() {
            System.out.println(" ==> A.hello");
        }
    }


    private interface B extends A {
        @Override
        default void hello() {
            System.out.println(" ==> B.hello");
        }
    }

    private static class C implements A, D {

        @Override
        public void hello() {
            A.super.hello();
            D.super.hello();
            System.out.println(" ==> C.hello");
        }
    }
}
