package helperfunctions;

public class ArrayHelper {

    public static boolean contains(int[] array, int element) {

        boolean result = false;

        for(int i = 0; i< array.length;i++){
            if(i == element){
                result = true;
                break;
            }
        }

        return result;
    }
}
