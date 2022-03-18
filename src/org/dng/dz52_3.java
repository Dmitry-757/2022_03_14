package org.dng;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Задание 3
 * Написать программу, которая проверяет, все ли значения
 * элементов массива одинаковые.
 * Массив задается и инициализируется в начале программы
 */

public class dz52_3 {
    public static boolean HasDoubleValue(int[] arr) {
        Set<Integer> mySet = IntStream.of(arr).boxed().collect(Collectors.toSet());
        return (arr.length != mySet.size());
    }

    public static void main(String[] args) {
//        int[] arr = new int[30];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = (int)(Math.random()*100); //rnd belong area from 0 to 1 -> rnd*100 belong [0:100]
//        }
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(arr));

        if (HasDoubleValue(arr)) {
            System.out.println("In array presents doubled values");
        } else {
            System.out.println("Array doesn`t contain doubled values");
        }

        int[] arr2 = {1, 2, 2, 2, 5};
        System.out.println(Arrays.toString(arr2));

        if (HasDoubleValue(arr2)) {
            System.out.println("In array presents doubled values");
        } else {
            System.out.println("Array doesn`t contain doubled values");
        }

    }
}
