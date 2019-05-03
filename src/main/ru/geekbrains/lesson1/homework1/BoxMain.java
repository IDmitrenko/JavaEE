package ru.geekbrains.lesson1.homework1;

public class BoxMain {
    public static void main(String[] args) {

        Box<Apple> appleBox1 = new Box<>();
        Box<Orange> orangeBox = new Box<>();

        for (int i = 0; i < 8; i++) {
            appleBox1.addBox(new Apple());
            orangeBox.addBox(new Orange());
        }

        for (int i = 0; i < 5; i++) {
            appleBox1.addBox(new Apple());
        }

        Float wtApple = appleBox1.getWeight();
        Float wtOrange = orangeBox.getWeight();
        System.out.println("Вес коробки с яблоками - " + wtApple);
        System.out.println("Вес коробки с апельсинами - " + wtOrange);

        boolean result = appleBox1.compare(orangeBox);
        if (result) {
            System.out.println("Вес коробки с яблоками равен весу коробки с апельсинами.");
        } else {
            System.out.println("Вес коробки с яблоками не равен весу коробки с апельсинами.");
        }

        Box<Apple> appleBox2 = new Box<>();
        System.out.println("Количество фруктов в первой коробке - " + appleBox1.getCount());
        System.out.println("Количество фруктов во второй коробке - " + appleBox2.getCount());
        System.out.println("Пересыпаем фрукты из первой коробки во вторую.");
        appleBox1.move(appleBox2);
        System.out.println("Количество фруктов в первой коробке - " + appleBox1.getCount());
        System.out.println("Количество фруктов во второй коробке - " + appleBox2.getCount());

    }

}
