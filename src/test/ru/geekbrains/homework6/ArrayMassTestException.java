package ru.geekbrains.homework6;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.geekbrains.lesson6.homework6.ArrayAction;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ArrayMassTestException {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {new int[]{1, 2, 34, 48, 2, 3, 24, 1, 7},
                        new int[]{}},
                {new int[]{32, 34, 54, 2, 90, 87, 125},
                        new int[]{}},
                {new int[]{23, 45, 7, 0, 30, 5, 2019, 5, 66, 88, 94, 64},
                        new int[]{}}
        });
    }

    private int[] arrIn;
    private int[] arrOut;

    public ArrayMassTestException(int[] arrIn, int[] arrOut) {
        this.arrIn = arrIn;
        this.arrOut = arrOut;
    }

    private ArrayAction arrayAction;

    @Before
    public void init() {
        arrayAction = new ArrayAction();
    }

    @Test(expected = RuntimeException.class)
    public void test() {
        Assert.assertArrayEquals(arrOut, arrayAction.arrayProcessing(arrIn));
    }

}
