package org.dng;

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
}

public class StudentsDiary {

    public static void main(String[] args) {
//        ArrayList<String> subj = new ArrayList<>();
//        ArrayList<Integer> ratings = new ArrayList<>();
//        Integer[] arr = {1,2,3};
        DynemicalArr<Integer> myDArr = new DynemicalArr<>();
        myDArr.add(1);
        myDArr.add(2);
        myDArr.add(3);
        myDArr.remove(2);
        myDArr.add(4);
        myDArr.add(5);

//        Object[] arr2 = AddValue2Array(arr,4);
//        for (Object i:arr2){
//            System.out.println(i);
//        }

//        String[] subj = null;
//        int[] ratings = null;
//        try (Scanner sc = new Scanner(System.in)){
//            boolean stop = false;
//            while (!stop){
//
//            }
//        }

    }

}
