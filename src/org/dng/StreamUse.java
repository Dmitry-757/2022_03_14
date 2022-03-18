package org.dng;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamUse {
    public static void main(String[] args) {
        int[] arr1 = {1,2,2,3,3,4};
        Set<Integer> mySet = IntStream.of(arr1).boxed().collect(Collectors.toSet());
        Set<Integer> mySet2 = Arrays.stream(arr1).boxed().collect(Collectors.toSet());
        Integer[] arr2 = mySet.toArray(new Integer[mySet.size()]);
        int[] arr3 = Arrays.stream(arr2).mapToInt(Integer::intValue).toArray();
        for (int i : arr3){
            System.out.printf("%d,",i);
        }
        System.out.println();

    }

}
