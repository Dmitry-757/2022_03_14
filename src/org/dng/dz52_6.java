package org.dng;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Задание 6
 * Заполнить массив на 10 элементов случайными числами от -10 до +10.
 * Посчитать количество уникальных значений (встречающихся в массиве один раз).
 * Вывести на консоль значения уникальных элементов и индексы, под которыми
 * они находятся в массиве.
 * ***************************
 * Создадим коллекцию HashMap, в качестве ключа будут выступать элементы массива,
 * а в качестве значения будут объекты класса MyStorage, в приватных переменных которого будут содержаться
 * количество раз, которое данное число встречается в массиве (numDuplication) и индекс элемента массива (index)
 * При последовательном переборе элементов массива проверяем, встречается ли текущее число в индексах коллекции HashMap
 * если встречается, то выдергиваем объект MyStorage по ссылке содержащейся в значении HashMap и увиличиваем MyStorage.numDuplication
 * если же это число из массива еще не содержится в индексах HashMap, то добавляем в HashMap пару ключ-значение где ключем является данное число
 * а значением вновь созданный объект класса MyStorage свойствам которого в свою очередь устанавливаем значения numDuplication=1
 * (т.к. это число встретилось первый раз) и index = индекс обрабатываемого элемента массива.
 * далее отфильтруем коллекцию что бы в ней остались только элементы с количеством повторений == 1
 * количество уникальных значений в массиве равно количеству элементов в отфильтрованной коллекции HashMap
 * искомые индексы массива получаем из свойства index объектов MyStorage ссылки на которые в свою очередь хранятся в значенииях HashMap
 */

public class dz52_6 {
    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 20) - 10; //rnd belong area from 0 to 1 -> rnd*20 belong [0:20] -> rnd*10-20 belong [-10:10]
        }
//        int[] arr = {1, 2, 2, 2, 4, 5, 5};
        System.out.println("The parsing array is:");
        System.out.println(Arrays.toString(arr));

        Map<Integer, MyStorage> myMap = new HashMap<>();//the arrays value will be as a key and the number of reduplication will be as a Value of Map

        int value;
        MyStorage myStorage;
        for (int i = 0; i < arr.length; i++) {
            value = arr[i];
            if (myMap.containsKey(value)) {
                myStorage = myMap.get(value);
                myStorage.incNumDuplication();
            } else {
                myMap.put(value, new MyStorage(1, i));
            }
        }

        myMap = myMap.entrySet().stream().filter(e -> e.getValue().getNumDuplication() == 1).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
        System.out.println("The number of unique values is " + myMap.size());

        for (var entry : myMap.entrySet()) {
            System.out.println("Value " + entry.getKey() + " occurs " + entry.getValue().getNumDuplication() + " time(s) in index " + entry.getValue().getIndex());
        }

    }
}

class MyStorage { //this is the storage for the number of duplications and their indices for each element of the array
    private int numDuplication;
    private int index;

    MyStorage(int numDuplication, int index) {
        this.numDuplication = numDuplication;
        this.index = index;
    }

    public int getNumDuplication() {
        return numDuplication;
    }

    public void setNumDuplication(int numDuplication) {
        this.numDuplication = numDuplication;
    }

    public int getIndex() {
        return index;
    }

    public void incNumDuplication() {
        this.numDuplication = ++this.numDuplication;
    }
}



