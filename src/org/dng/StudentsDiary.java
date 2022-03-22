package org.dng;

import java.util.OptionalInt;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class DynemicalArr<T>{
    private final int INIT_SIZE = 2;
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

    //we will send a copy of the array outside, not the arrey itself - in order not to violate the principle of Encapsulation and "Open Closed Principle"  ))
    public Object[] getArr(){
        Object[] newArray = new Object[array.length-1];
        newArray = new Object[array.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    public boolean isValuePresent(String topic){
        boolean isPresent = Stream.of(array)
                .filter(v -> v!= null)
                .map(v -> v.toString())
                .anyMatch(v -> (v.equals(topic)) );
        return isPresent;
    }

    public int getIndexOfVal(String topic){
        OptionalInt result = OptionalInt.of(-1) ;
        if (isValuePresent(topic)) {
            if (array[0].getClass().getName() == "java.lang.String") {
                result =
                        IntStream
                                .range(0, array.length)
                                .filter(i -> array[i].equals(topic))
                                .findFirst();
            }
        }
        return result.getAsInt();
    }

}

class DiaryService{
    static void add(String topic, int mark){
        //check for inputting topic is new
        if(!StudentsDiary.topic.isValuePresent(topic)) {
            StudentsDiary.topic.add(topic);
            StudentsDiary.mark.add(mark);

            StudentsDiary.topic.show("+");
            StudentsDiary.mark.show("+");
        }
    }

    static void remove(String topic){
        int idx = StudentsDiary.topic.getIndexOfVal(topic);
        if (idx >= 0) {
            StudentsDiary.topic.remove(idx);
            StudentsDiary.mark.remove(idx);

            StudentsDiary.topic.show("-");
            StudentsDiary.mark.show("-");
        }
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
//        topic.add("maths");
//        topic.add("phisics");
//        topic.show("+");
//        mark.add(9);
//        mark.add(1);
//        mark.add(4);
//        mark.add(2);
//        mark.show("+");
//
//        System.out.println(MarkService.getAverageMark(mark.getArr()));
//        System.out.println(MarkService.getMaxMark(mark.getArr()));
//        System.out.println(MarkService.getMinMark(mark.getArr()));


        Pattern topicPattern = Pattern.compile("^[a-zA-Z]+");
        Pattern markPattern = Pattern.compile("[2-5]");
        try (Scanner sc = new Scanner(System.in)){
            boolean stop = false;
            int choice = 0;
            String line;
            while (!stop){
                System.out.println("Enter your choice: 1 - add topic and mark, 2 - remove theme, 3 - exit");
                if(sc.hasNextInt()){
                    choice = sc.nextInt();
                    sc.nextLine();
                }
                switch (choice){
                    case 1 ->{
                        System.out.println("Enter topic and mark. Example: mathematics 5");
                        try{
                            if(sc.hasNextLine()) {
                                String topic = null;
                                int mark = 0;
                                line = sc.nextLine();
                                Matcher topicMatcher = topicPattern.matcher(line);
                                if (topicMatcher.find()) {
                                    topic = topicMatcher.group();
                                    System.out.println("topic = " + topic);
                                } else {
                                    throw new Exception("wrong input - can`t reed topic...");
                                }

                                Matcher markMatcher = markPattern.matcher(line);
                                if (markMatcher.find() ) {
                                    mark = Integer.valueOf(markMatcher.group()).intValue();
                                    System.out.println("mark = " + mark);
                                } else {
                                    throw new Exception("wrong input - can`t reed mark...");
                                }

                                DiaryService.add(topic, mark);

                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case 2 ->{
                        System.out.println("Enter topic for removal. Example: MarksizmLenenizm");
                        try{
                            if(sc.hasNextLine()) {
                                String topic = null;
                                line = sc.nextLine();
                                Matcher topicMatcher = topicPattern.matcher(line);
                                if (topicMatcher.find()) {
                                    topic = topicMatcher.group();
                                    System.out.println("topic = " + topic);
                                } else {
                                    throw new Exception("wrong input - can`t reed topic...");
                                }
                                DiaryService.remove(topic);
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    case 3 ->{
                        stop = true;
                        System.out.println("Our great program is ended ;) !");
                    }
                    default -> {
                        System.out.println("In our roulette you can only three option for choice: 1, 2 or 3 ;)");
                    }
                }

            }
        }

    }

}
