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
        boolean isAllValEqual = true;
        for (int value:arr){
            if(value != arr[0]){
                isAllValEqual = false;
                break;
            }
        }
        return (isAllValEqual);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(arr));
        if (HasDoubleValue(arr)) {
            System.out.println("All elements in array are equals");
        } else {
            System.out.println("Elements of array are not equal");
        }
        int[] arr2 = {2, 2, 2, 2, 2};
        System.out.println(Arrays.toString(arr2));
        if (HasDoubleValue(arr2)) {
            System.out.println("All elements in array are equals");
        } else {
            System.out.println("Elements of array are not equal");
        }
    }
}
