package ru.geekbrains.lesson6;

public class HomeWork6 {

    private static int number1 = 4;
    private static int number2 = 1;

    public static void main(String[] args) {

        int arr1[] = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        int arr2[] = {32, 34, 4, 2, 90, 87, 125};
        int arr3[] = {45, 666, 916, 22, 39, 124, 21, 10};
        int arr4[] = {4, 4, 7, 0, 30, 5, 2019, 5, 66, 88, 94, 64};

        try {
            int rezTest1Arr1[] = arrayProcessing(arr1);
            int rezTest1Arr2[] = arrayProcessing(arr2);
            int rezTest1Arr3[] = arrayProcessing(arr3);
            int rezTest1Arr4[] = arrayProcessing(arr4);

        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }

        boolean rezTest2Arr1 = compositionCheck(arr1);
        boolean rezTest2Arr2 = compositionCheck(arr2);
        boolean rezTest2Arr3 = compositionCheck(arr3);
        boolean rezTest2Arr4 = compositionCheck(arr4);

    }

    private static int[] arrayProcessing(int[] arr) throws RuntimeException {
        int origin = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == number1)
                origin = i;
        }

        if (origin >= 0) {
            int[] rez = new int[arr.length - origin - 1];
            System.arraycopy(arr,origin + 1, rez, 0, arr.length - origin - 1);
            return rez;
        } else {
            throw new RuntimeException();
        }
    }

    private static boolean compositionCheck(int[] arr) {
        boolean rez1 = false;
        boolean rez2 = false;
        for (int i = 0; i < arr.length; i++) {
            if (rez1 && rez2) {
                return true;
            } else if (arr[i] == number1) {
                rez1 = true;
            } else if (arr[i] == number2) {
                rez2 = true;
            }
        }
        return false;
    }
}
