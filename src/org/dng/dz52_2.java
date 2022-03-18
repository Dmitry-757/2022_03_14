package org.dng;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Задание 2
 * Заполнить массив на 30 элементов случайными числами
 * от -70 до +50. Найти минимальный элемент и вывести его
 * на консоль. Найти максимальный элемент и вывести его на
 * консоль.
 */
public class dz52_2 {
    public static void main(String[] args) {
        int[] arr = new int[30];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 120) - 70; //rnd belong area from 0 to 1 -> rnd*120 belong [0:120] -> rnd*120-70 belong [-70:50]
        }
        System.out.println(Arrays.toString(arr));

        //first method
        int min1 = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (min1 > arr[i]) min1 = arr[i];
        }
        System.out.println("1-st meth. Min value of array is " + min1);

        //second method
        System.out.println("2-th meth. Min value in array is " + IntStream.of(arr).sorted().findFirst().orElse(0));
        //third method
        System.out.println("3-th meth. Min value in array is " + IntStream.of(arr).min().orElse(0));

        //forth method
        Arrays.sort(arr);
        System.out.println("4-th meth. Min value of array is " + arr[0]);
        System.out.println();
    }
}
