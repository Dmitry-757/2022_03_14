package org.dng;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Задание 6
 * Заполнить массив на 10 элементов случайными числами от -10 до +10.
 * Посчитать количество уникальных значений (встречающихся в массиве один раз).
 * Вывести на консоль значения уникальных элементов и индексы, под которыми
 * они находятся в массиве.
 */


public class dz52_6 {
    public static void main(String[] args) {
//        int[] arr = new int[10];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = (int) (Math.random() * 20) - 10; //rnd belong area from 0 to 1 -> rnd*20 belong [0:20] -> rnd*10-20 belong [-10:10]
//        }
        int[] arr = {1, 2, 2, 2, 4, 5, 5};
        int[] arr2 = new int[arr.length];//how many times the value occurs

        System.out.println(Arrays.toString(arr));
        Set<Integer> myUniqueSet = new HashSet<>();
        myUniqueSet = IntStream.of(arr).boxed().collect(Collectors.toSet());
        Map<Integer, Integer> myMap = new HashMap<>();
//        myMap = IntStream.of(arr).collect(Collectors.toMap(a -> a[0],b->b[1])) )


        int value, countOfDoubled=0;
        for (int i = 0; i < arr.length; i++) {
            value = arr[i];
            if (myUniqueSet.contains(Integer.valueOf(value))) {
                arr2[i]++;
            }
        }
//        for (val:myUniqueSet) {
//            if(Arrays.){
//
//            }
//        }

        IntStream.of(arr2).boxed().filter(v->v>=1).forEach(v-> System.out.println(v));

        System.out.print("Number of unic values in array is " + (IntStream.of(arr2).boxed().filter(v->v>1).count() ) + " : ");
        Stream.of(arr2).filter(v-> v.equals(0) ).forEach(val-> {System.out.println(val.toString());} );
        System.out.println();

    }
}
