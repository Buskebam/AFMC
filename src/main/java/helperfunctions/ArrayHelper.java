package helperfunctions;

import java.util.ArrayList;

public class ArrayHelper {

    public static boolean contains(int[] array, int element) {

        boolean result = false;

        for (int i = 0; i < array.length; i++) {
            if (i == element) {
                result = true;
                break;
            }
        }

        return result;
    }

    public static int[] listToArray(ArrayList<Integer> list) {

        //convert list to array
        int[] result = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
