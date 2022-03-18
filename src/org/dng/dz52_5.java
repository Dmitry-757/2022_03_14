package org.dng;

import java.util.*;
import java.util.stream.Collectors;

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
        System.out.println("The parsing array is:");
        System.out.println(Arrays.toString(arr));

        Map<Integer, Integer> myMap = new HashMap<>();//the arrays value will be as a key and the number of reduplication will be as a Value of Map
        int oldVal;
        for (int value : arr) {
            if (myMap.containsKey(value)) {
                oldVal = myMap.get(value);
                myMap.replace(value, ++oldVal);
            } else {
                myMap.put(value, 1);
            }
        }

        myMap = myMap.entrySet().stream().filter(e -> e.getValue() > 1).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
        //forEach(e -> System.out.println("Value " + e.getKey() + " occurs " + e.getValue() + " times"));
        System.out.println("The number of duplicated values is " + myMap.size());

        for (var entry : myMap.entrySet()) {
            //System.out.println(entry.getKey() + "/" + entry.getValue());
            System.out.println("Value " + entry.getKey() + " occurs " + entry.getValue() + " times");
        }

    }
}
