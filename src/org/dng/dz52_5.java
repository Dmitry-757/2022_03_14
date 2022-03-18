package org.dng;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;


/**
 * Задание 5
 * Заполнить массив на 10 элементов случайными числами от -10 до +10.
 * Посчитать количество повторяющихся значений.
 * Вывести на консоль только повторяющиеся элементы и количество повторений.
 */

public class dz52_5 {
    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 20) - 10; //rnd belong area from 0 to 1 -> rnd*20 belong [0:20] -> rnd*10-20 belong [-10:10]
        }
//        int[] arr = {1, 2, 2, 2, 4, 5, 5};
        System.out.println(Arrays.toString(arr));

        Set<Integer> myUnDoubledSet = new HashSet<>();
        Set<Integer> myDoubledSet = new HashSet<>();
        for (int value : arr) {
            if (!myUnDoubledSet.contains(Integer.valueOf(value))) {
                myUnDoubledSet.add(Integer.valueOf(value));
            }
            else {
                myDoubledSet.add(Integer.valueOf(value));
            }
        }
        System.out.print("Number of doubled values in array is "+myDoubledSet.size()+" : ");
        Stream.of(myDoubledSet).forEach(val-> System.out.println(val.toString()));

    }
}
