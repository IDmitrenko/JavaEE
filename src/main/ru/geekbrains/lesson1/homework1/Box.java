package ru.geekbrains.lesson1.homework1;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private List<T> fruits = new ArrayList<>();

    public void addBox(T fruit) {
        fruits.add(fruit);
    }

    public int getCount() {
        return fruits.size();
    }

    public Float getWeight() {
        return getCount() * fruits.get(0).getWeight();
    }

    public boolean compare(Box<?> box) {
        return Math.abs(this.getWeight() - box.getWeight()) < 0.0001;
    }

    public void move(Box<T> box) {
/*
        for (T t : this.obj) {
            box.addBox(t);
        }
*/
        box.fruits.addAll(this.fruits);
        this.fruits.clear();
    }
}