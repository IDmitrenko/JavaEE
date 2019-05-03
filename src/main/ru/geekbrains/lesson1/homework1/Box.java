package ru.geekbrains.lesson1.homework1;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> obj;

    public Box() {
        this.obj = new ArrayList<>();
    }

    public void addBox(T fruit) {
        obj.add(fruit);
    }

    public int getCount() {
        return obj.size();
    }

    public Float getWeight() {
        return obj.size() * obj.get(0).getWeight();
    }

    public boolean compare(Box box) {
        boolean result = false;
        if (this.getWeight().equals(box.getWeight()))
            result = true;
        return result;
    }

    public void move(Box<T> box) {
        for (T t : this.obj) {
            box.addBox(t);
        }
        this.obj.clear();
    }
}