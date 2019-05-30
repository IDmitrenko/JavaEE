package ru.geekbrains.homework6;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.geekbrains.lesson6.homework6.ArrayAction;


public class ArraysTest {

    private ArrayAction arrayAction;
    private static int arr1[] = {1, 2, 4, 4, 2, 3, 4, 1, 7};
    private static int arr2[] = {32, 34, 4, 2, 90, 87, 125};
    private static int arr3[] = {45, 666, 916, 22, 39, 124, 21, 10};
    private static int arr4[] = {4, 4, 7, 0, 30, 5, 2019, 5, 66, 88, 94, 64};

    @Before
    public void init() {
        arrayAction = new ArrayAction();
    }

    @Test
    public void test1Arr1() {
        int[] arr = {1, 7};
        Assert.assertArrayEquals(arr, arrayAction.arrayProcessing(arr1));
    }

    @Test
    public void test1Arr2() {
        int[] arr = {2, 90, 87, 125};
        Assert.assertArrayEquals(arr, arrayAction.arrayProcessing(arr2));
    }

    @Test(expected = RuntimeException.class)
    public void test1Arr3() {
        int[] arr = {1};
        Assert.assertArrayEquals(arr, arrayAction.arrayProcessing(arr3));
    }

    @Test
    public void test1Arr4() {
        int[] arr = {7, 0, 30, 5, 2019, 5, 66, 88, 94, 64};
        Assert.assertArrayEquals(arr, arrayAction.arrayProcessing(arr4));
    }

    @Test
    public void test2Arr1() {
        Assert.assertTrue(arrayAction.compositionCheck(arr1));
    }

    @Test
    public void test2Arr2() {
        Assert.assertFalse(arrayAction.compositionCheck(arr2));
    }

    @Test
    public void test2Arr3() {
        Assert.assertFalse(arrayAction.compositionCheck(arr3));
    }

    @Test
    public void test2Arr4() {
        Assert.assertFalse(arrayAction.compositionCheck(arr4));
    }

}
