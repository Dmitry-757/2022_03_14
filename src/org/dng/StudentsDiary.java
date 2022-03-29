package org.dng;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Написать программу «Ведомость оценок ученика». Программа должна позволять:
 * 1. При запуске вводит предметы (строки) и оценки (числа int).
 * 2. Далее программа предлагает пользователю действия (в цикле):
 * ОБЯЗАТЕЛЬНЫЙ ФУНКЦИОНАЛ
 * • получить средний балл (дробный в том числе)
 * • вывести все оценки
 * • вывести максимальную оценку и название предмета
 * • вывести минимальную оценку и название соответствующего предмета
 * БОНУСНЫЙ ФУНКЦИОНАЛ:
 * • реализовать возможность редактирования оценок – изменение оценки по нужному предмету
 * • добавление новых предметов/ удаление предметов
 * 3. Требование к программе: дружелюбный консольный интерфейс, отсутствие опечаток, использование массивов, циклов, условий.
 * и трошки потренируюсь юзать stream ))
 * Так же нельзя позволять устанавливать оценки кроме 2, 3, 4, 5 – т.е. валидировать входные данные.
 */

class DynemicalArr<T> {
    private final int INIT_SIZE = 2;
    private final int RESIZE_QUANTITY = 2;
    private final int CONDITION4CUT = 2;
    private int pointerOnLastEl = 0;
    private Object[] array = new Object[INIT_SIZE];

    public void add(T item) {
        if (pointerOnLastEl == array.length) {
            Object[] newArray = new Object[array.length + RESIZE_QUANTITY];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        array[pointerOnLastEl] = item;
        pointerOnLastEl++;
    }

    public void set(int idx, T item) {
        if ((0 < idx) && (idx <= pointerOnLastEl)) {
            array[idx] = item;
        }
    }


    public void remove(int idx) {
        Object[] newArray = new Object[array.length - 1];
        if (idx > 0) {
            System.arraycopy(array, 0, newArray, 0, idx);
        }
        System.arraycopy(array, idx + 1, newArray, idx, pointerOnLastEl - idx - 1);
        array = newArray;
        newArray = null;
        pointerOnLastEl--;

        if (CONDITION4CUT < (array.length - pointerOnLastEl)) {
            newArray = new Object[pointerOnLastEl];
            System.arraycopy(array, 0, newArray, 0, pointerOnLastEl);
            array = newArray;
        }
    }

    public void show(String oper) {
        System.out.println("***** " + oper + " ****");
        for (Object i : array) {
            System.out.println(i);
        }
        System.out.println("*********");
    }

    //we will send a copy of the array outside, not the arrey itself - in order not to violate the principle of Encapsulation and "Open Closed Principle"  ))
    public Object[] getArr() {
        Object[] newArray = new Object[array.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    public int getArrLenght() {
        return array.length;
    }
    public T getItem(int idx) {
        return (T)array[idx];
    }

    public boolean isValuePresent(T topic) {
        boolean isPresent = Stream.of(array)
                .filter(v -> v != null)
                .anyMatch(v -> (v.equals(topic)));
        return isPresent;
    }

    public int getIndexOfVal(T topic) {
        OptionalInt result = OptionalInt.of(-1);
        if (isValuePresent(topic)) {
            result =
                    IntStream
                            .range(0, array.length)
                            .filter(i -> array[i].equals(topic))
                            .findFirst();
        }
        return result.getAsInt();
    }

}

class DiaryService {

    static void add(String topic, int mark) {
        //check for inputting topic is new
        if (!StudentsDiary.topic.isValuePresent(topic)) {
            StudentsDiary.topic.add(topic);
            StudentsDiary.mark.add(mark);
        }
        else{
            System.out.println("Mark for the topic "+topic + " already present!");
        }
    }

    static void edit(String topic, int mark) {
        //check for inputting topic is new
        if (StudentsDiary.topic.isValuePresent(topic)) {
            int idx = StudentsDiary.topic.getIndexOfVal(topic);
            StudentsDiary.topic.set(idx, topic);
            StudentsDiary.mark.set(idx, mark);
        }
        else{
            System.out.println("topic "+topic + " isn`t present!");
        }
    }


    static void remove(String topic) {
        int idx = StudentsDiary.topic.getIndexOfVal(topic);
        if (idx >= 0) {
            StudentsDiary.topic.remove(idx);
            StudentsDiary.mark.remove(idx);
            System.out.println("topic "+topic +" was removed.");
        }
        else{
            System.out.println("we don't study this kind of crap...");
        }
    }

    static void getAllMarks() {
        for (int i = 0; i < StudentsDiary.mark.getArrLenght(); i++) {
            if (StudentsDiary.topic.getItem(i) != null) {
                System.out.println("topic = " + StudentsDiary.topic.getItem(i) + " Mark = " + StudentsDiary.mark.getItem(i));
            }
        }
    }

    static void getAverageMark() {
        int [] arr = new int[StudentsDiary.mark.getArrLenght()];
        for (int i = 0; i < StudentsDiary.mark.getArrLenght(); i++) {
            if (StudentsDiary.topic.getItem(i) != null) {
                arr[i] = StudentsDiary.mark.getItem(i);
            }
        }
        System.out.println("Average mark is "+IntStream.of(arr).average().getAsDouble());
    }

    static void getMaxMark() {
        int max = StudentsDiary.mark.getItem(0);
        int idx = 0;
        for (int i = 0; i < StudentsDiary.mark.getArrLenght(); i++) {
            if (StudentsDiary.topic.getItem(i) != null) {
                if (max < StudentsDiary.mark.getItem(i)) {
                    max = StudentsDiary.mark.getItem(i);
                    idx = i;
                }
            }
        }
        System.out.println("Max mark is "+max+ " on topic "+ StudentsDiary.topic.getItem(idx));
    }

    static void getMinMark() {
        int min = StudentsDiary.mark.getItem(0);
        int idx = 0;
        for (int i = 0; i < StudentsDiary.mark.getArrLenght(); i++) {
            if (StudentsDiary.topic.getItem(i) != null) {
                if (min > StudentsDiary.mark.getItem(i)) {
                    min = StudentsDiary.mark.getItem(i);
                    idx = i;
                }
            }
        }
        System.out.println("Min mark is "+min+ " on topic "+ StudentsDiary.topic.getItem(idx));
    }

}

public class StudentsDiary {
    static DynemicalArr<String> topic = new DynemicalArr<>();
    static DynemicalArr<Integer> mark = new DynemicalArr<>();

    public static void main(String[] args) {

        Pattern topicPattern = Pattern.compile("^[a-zA-Zа-яА-Я]+-?[a-zA-Zа-яА-Я]*");
        Pattern markPattern = Pattern.compile("\\s\\d+");
        try (Scanner sc = new Scanner(System.in)) {
            boolean stop = false;
            int choice = -1;
            String line;
            while (!stop) {
                System.out.println();
                System.out.println("Enter your choice: 1 - add topic and mark, 2 - remove topic");
                System.out.println("3 - show average mark, 4 - show all marks, 5 - show max mark and topic, 6 - show min mark and topic, 7 - edit the mark for the topic");
                System.out.println("0 - exit");
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                }
                sc.nextLine();
                switch (choice) {
                    case 1 -> {
                        System.out.println("Enter topic and mark. Example: mathematics 5");
                        try {
                            if (sc.hasNextLine()) {
                                String topic = null;
                                int mark = 0;
                                line = sc.nextLine();
                                Matcher topicMatcher = topicPattern.matcher(line);
                                if (topicMatcher.find()) {
                                    topic = topicMatcher.group();
                                    System.out.println("topic = " + topic);
                                } else {
                                    throw new Exception("wrong input - can`t find topic...");
                                }

                                Matcher markMatcher = markPattern.matcher(line);
                                if (markMatcher.find()) {
                                    mark = Integer.valueOf(markMatcher.group().trim()).intValue();
                                    if((mark<2) || (mark > 5)){
                                        throw new Exception("wrong input - mark must be in interval of 2-5...");
                                    }
                                    System.out.println("mark = " + mark);
                                } else {
                                    throw new Exception("wrong input - mark must be in interval of 2-5...");
                                }

                                DiaryService.add(topic, mark);

                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            continue;
                        }
                    }
                    case 2 -> {
                        System.out.println("Enter topic for removal. Example: Marksizm-Lenenizm");
                        try {
                            if (sc.hasNextLine()) {
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
                            continue;
                        }
                    }
                    case 3 ->{
                        DiaryService.getAverageMark();
                    }

                    case 4 ->{
                        System.out.println("All marks by all topics are :");
                        DiaryService.getAllMarks();
                    }

                    case 5 ->{
                        DiaryService.getMaxMark();
                    }

                    case 6 ->{
                        DiaryService.getMinMark();
                    }

                    case 7 -> {
                        System.out.println("Enter topic for reset mark. Example: Marksizm-Lenenizm 3");
                        try {
                            if (sc.hasNextLine()) {
                                String topic = null;
                                int mark = 0;
                                line = sc.nextLine();
                                Matcher topicMatcher = topicPattern.matcher(line);
                                if (topicMatcher.find()) {
                                    topic = topicMatcher.group();
                                    System.out.println("topic = " + topic);
                                } else {
                                    throw new Exception("wrong input - can`t find topic...");
                                }

                                Matcher markMatcher = markPattern.matcher(line);
                                if (markMatcher.find()) {
                                    mark = Integer.valueOf(markMatcher.group().trim()).intValue();
                                    System.out.println("mark = " + mark);
                                } else {
                                    throw new Exception("wrong input - mark must be in interval of 2-5...");
                                }

                                DiaryService.edit(topic, mark);
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            continue;
                        } ;
                    }

                    case 0 -> {
                        stop = true;
                        System.out.println("Our great program is ended ;) !");
                    }
                    default -> {
                        System.out.println("In our roulette you can only for choice: 1, 2, 3, 4, 5, 6, 7 or 0 ;)");
                    }
                }

            }
        }

    }

}
