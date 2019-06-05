package ru.geekbrains.lesson7.hw7;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestRunner {

    public static void run(Class<?> clazz) {
        Method beforeMethod = null;
        Method afterMethod = null;
        List<Method> testMethods = new ArrayList<>();

        for (Method mth : clazz.getMethods()) {
            if (mth.isAnnotationPresent(BeforeSuit.class)) {
                if (beforeMethod == null) {
                    beforeMethod = mth;
                } else {
                    throw new IllegalStateException("BeforeSuite annotation duplicated");
                }
            } else if (mth.isAnnotationPresent(AfterSuit.class)) {
                if (afterMethod == null) {
                    afterMethod = mth;
                } else {
                    throw new IllegalStateException("AfterSuite annotation duplicated");
                }
            } else if (mth.isAnnotationPresent(Test.class)) {
                testMethods.add(mth);
            }
        }

// Сортировка коллекции методов по полю приоритет
/*  Вариант с реализацией comparator
        testMethods.sort((a, b) -> {
            if (a.getAnnotation(Test.class).priority() > b.getAnnotation(Test.class).priority()) {
                return 1;
            } else if (a.getAnnotation(Test.class).priority() < b.getAnnotation(Test.class).priority()) {
                return -1;
            } else {
                return 0;
            }
        });
    }
*/
/*
// Вариант 1 для Java8 и выше
        testMethods.sort((a, b) -> {
            return Integer.compare(a.getAnnotation(Test.class).priority(), b.getAnnotation(Test.class).priority());
        });
    }
*/
// Вариант 2 с лямбдой (указывается правило по которому сортируется список)
        testMethods.sort(Comparator.comparingInt(method -> method.getAnnotation(Test.class).priority()));

// запускаем методы теста
        try {
// создаем экземпляр класса
            Object testObject = clazz.newInstance();
            if (beforeMethod != null) {
                beforeMethod.invoke(testObject);
            }
            for (Method mth : testMethods) {
                mth.invoke(testObject);
            }
            if (afterMethod != null) {
                afterMethod.invoke(testObject);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        run(TestExamples.class);
    }
}
