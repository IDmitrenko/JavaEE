package ru.geekbrains.lesson7.hw7;

public class TestExamples {

    @BeforeSuit
    public void before() {
        System.out.println(getClass().getName() + " before method");
    }

/*
    @BeforeSuit
    public void before1() {
        System.out.println(getClass().getName() + " before method");
    }
*/

    @Test(priority = 1)
    public void test1() {
        System.out.println(getClass().getName() + " test 1 method");
    }

    @Test(priority = 2)
    public void test2() {
        System.out.println(getClass().getName() + " test 2 method");
    }

    @Test(priority = 32)
    public void test3() {
        System.out.println(getClass().getName() + " test 3 method");
    }

    @Test(priority = 4)
    public void test4() {
        System.out.println(getClass().getName() + " test 4 method");
    }


    @AfterSuit
    public void after() {
        System.out.println(getClass().getName() + " after method");
    }
}
