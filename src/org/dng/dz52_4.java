package org.dng;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 Задание 4
 Написать программу, которая проверяет, все ли значения
 элементов массива различные (не повторяются).
 Массив задается и инициализируется в начале программы.
 */
public class dz52_4 {
    public static boolean HasDoubleValue(int[] arr) {
        Set<Integer> mySet = IntStream.of(arr).boxed().collect(Collectors.toSet());
        return (arr.length != mySet.size());
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(arr));
        if (HasDoubleValue(arr)) {
            System.out.println("In array presents duplicated values");
        } else {
            System.out.println("Array doesn`t contain duplicated values");
        }
        int[] arr2 = {1, 2, 2, 2, 5};
        System.out.println(Arrays.toString(arr2));
        if (HasDoubleValue(arr2)) {
            System.out.println("In array presents duplicated values");
        } else {
            System.out.println("Array doesn`t contain duplicated values");
        }
    }
}
