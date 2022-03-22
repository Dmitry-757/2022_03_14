package org.dng;

import java.util.stream.IntStream;
import java.util.stream.Stream;

class DynemicalArr<T>{
    private final int INIT_SIZE = 10;
    private final int RESIZE_QUANTITY = 5;
    private final int CONDITION4CUT = 5;
    private int pointerOnLastEl = 0;
    private Object[] array = new Object[INIT_SIZE];

    public void add(T item) {
        if(pointerOnLastEl == array.length){
            Object[] newArray = new Object[array.length+RESIZE_QUANTITY];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        array[pointerOnLastEl] = item;
        pointerOnLastEl++;
    }

    public void remove (int idx){
        Object[] newArray = new Object[array.length-1];
        if(idx>0) {
            System.arraycopy(array, 0, newArray, 0, idx);
        }
        System.arraycopy(array, idx+1, newArray, idx, pointerOnLastEl - idx-1);
        array = newArray;
        newArray = null;
        pointerOnLastEl--;

        if(CONDITION4CUT<(array.length - pointerOnLastEl)){
            newArray = new Object[pointerOnLastEl];
            System.arraycopy(array, 0, newArray, 0, pointerOnLastEl);
            array = newArray;
        }
    }
    public void show(String oper){
        System.out.println("***** " + oper + " ****");
        for (Object i:array){
            System.out.println(i);
        }
        System.out.println("*********");
    }

    public Object[] getArr(){
        return array;
    }

}

class MarkService{
    static void add(String topic, int mark){
        StudentsDiary.topic.add(topic);
        StudentsDiary.mark.add(mark);
    }

   static double getAverageMark(Object[] array){
            int[] arr = Stream.of(array)
                    .filter(v -> v!= null)
                    .filter(v -> v.getClass().getName()=="java.lang.Integer")
                    .map(v->(int)v)
                    .mapToInt(Integer::intValue)
                    .toArray();
            return IntStream.of(arr).average().getAsDouble();
    }

    static double getMaxMark(Object[] array){
        int[] arr = Stream.of(array)
                .filter(v -> v!= null)
                .filter(v -> v.getClass().getName()=="java.lang.Integer")
                .map(v->(int)v)
                .mapToInt(Integer::intValue)
                .toArray();
        return IntStream.of(arr).max().getAsInt();
    }

    static double getMinMark(Object[] array){
        int[] arr = Stream.of(array)
                .filter(v -> v!= null)
                .filter(v -> v.getClass().getName()=="java.lang.Integer")
                .map(v->(int)v)
                .mapToInt(Integer::intValue)
                .toArray();
        return IntStream.of(arr).min().getAsInt();
    }

}

public class StudentsDiary {
    static DynemicalArr<String> topic = new DynemicalArr<>();
    static DynemicalArr<Integer> mark = new DynemicalArr<>();

    public static void main(String[] args) {
        mark.add(9);
        mark.add(1);
        mark.add(4);
        mark.add(2);
        mark.show("+");

        System.out.println(MarkService.getAverageMark(mark.getArr()));
        System.out.println(MarkService.getMaxMark(mark.getArr()));
        System.out.println(MarkService.getMinMark(mark.getArr()));

//        try (Scanner sc = new Scanner(System.in)){
//            boolean stop = false;
//            int choice = 0;
//            while (!stop){
//                System.out.println("Enter your choice: 1 - add theme and mark, 2 - remove theme, 3 - exit");
//                if(sc.hasNextInt()){
//                    choice = sc.nextInt();
//                }
//                switch (choice){
//                    case 1 ->{ }
//                }
//            }
//        }

    }

}
