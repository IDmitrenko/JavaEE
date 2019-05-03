package ru.geekbrains.lesson1.homework1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        String[] arr1 = {"A", "B", "C", "D", "E"};
        Double[] arr2 = {1.056d, 56.98765d, 123.008d, 45.875d, 251.34d};
        Integer[] arr3 = {3, 5, 7, 9, 1};

        int a = 3;
        int b = 1;

        System.out.println("Element 1 - " + arr1[a] + " , Element 2 - " + arr1[b]);
        String[] arrS = new Main().swapArrayElements(arr1, 3, 1);
        System.out.println("Element 1 - " + arrS[a] + " , Element 2 - " + arrS[b]);

        System.out.println("Element 1 - " + arr2[a] + " , Element 2 - " + arr2[b]);
        Double[] arrD = new Main().swapArrayElements(arr2, 3, 1);
        System.out.println("Element 1 - " + arrD[a] + " , Element 2 - " + arrD[b]);

        System.out.println("Element 1 - " + arr3[a] + " , Element 2 - " + arr3[b]);
        Integer[] arrI = new Main().swapArrayElements(arr3, 3, 1);
        System.out.println("Element 1 - " + arrI[a] + " , Element 2 - " + arrI[b]);

        for (int i = 0; i < arr1.length; i++) {
            if (i == arr1.length - 1) {
                System.out.printf(arr1[i] + "%n");
            } else {
                System.out.printf(arr1[i] + ", ");
            }
        }
        ArrayList<String> arrayListS = new Main().arrayToArrayList(arr1);
        System.out.println(arrayListS);

        for (int i = 0; i < arr2.length; i++) {
            if (i == arr2.length - 1) {
                System.out.printf(arr2[i] + "%n");
            } else {
                System.out.printf(arr2[i] + ", ");
            }
        }
        ArrayList<Double> arrayListD = new Main().arrayToArrayList(arr2);
        System.out.println(arrayListD);

        for (int i = 0; i < arr3.length; i++) {
            if (i == arr3.length - 1) {
                System.out.printf(arr3[i] + "%n");
            } else {
                System.out.printf(arr3[i] + ", ");
            }
        }
        ArrayList<Integer> arrayListI = new Main().arrayToArrayList(arr3);
        System.out.println(arrayListI);

    }

    private <T> T[] swapArrayElements(T[] arr, int a, int b) throws ArrayIndexOutOfBoundsException {
        if (arr.length > a && arr.length > b) {
            T element = arr[a];
            arr[a] = arr[b];
            arr[b] = element;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
        return arr;
    }

    private <T> ArrayList<T> arrayToArrayList(T[] arr) {
        ArrayList<T> arrayList = new ArrayList<>();
        arrayList.addAll(Arrays.asList(arr));
/*
        for (T t : arr) {
            arrayList.add(t);
        }
*/
/*
        for (int i = 0; i < arr.length; i++) {
            arrayList.add(arr[i]);
        }
*/
        return arrayList;
    }
}
