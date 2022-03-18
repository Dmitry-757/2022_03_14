package org.dng;

/**
 Преобразование int [] в Integer [] и наоборот:

 int[] arr1 = {1, 2, 3};

 Integer[] arr2 = Arrays.stream(arr1).boxed().toArray(Integer[]::new);
 or
 Integer[] arr2 = IntStream.of( arr1 ).boxed().toArray( Integer[]::new );

 int[] arr3 = Arrays.stream(arr2).mapToInt(Integer::intValue).toArray();

 int[] aint = {1,2,3,4,5,6,7,8,9,10};
 Integer[] aInt = new Integer[aint.length];
 Arrays.setAll(aInt, i -> aint[i]);

 int[] arr1 = {1,1,2,2,3};

 Set<Integer> mySet = Arrays.stream(arr1).boxed().collect(Collectors.toSet());//создали и заполнили set

 Integer[] arr2 = mySet.toArray(new Integer[mySet.size()]); //преобразовали set в Integer[]

 int[] arr3 = Arrays.stream(arr2).mapToInt(Integer::intValue).toArray();//преобразовали Integer[] в int[]

 IntStream boxed()
 возвращает поток, состоящий из элементов этого потока, каждый из которых упакован в целое число
 */

public class Main {

    public static void main(String[] args) {
	// write your code here
    }
}
