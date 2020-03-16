package smallprogressmeasure;


import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ProgressMeasureTest {
    @org.junit.jupiter.api.Test
    public void testProgess1(){
        int priority = 0;
        boolean odd = priority%2==1;

        PriorityInformation info = new PriorityInformation(new int[]{0,2,0,0});
        PriorityInformation M = new PriorityInformation(new int[]{0, 2, 0, 1});

        assertEquals("[0, 0, 0, 0]",info.progressMeasure(M,priority,odd).toString());
    }
    @org.junit.jupiter.api.Test
    public void testProgess2(){
        int priority = 1;
        boolean odd = priority%2==1;

        PriorityInformation info = new PriorityInformation(new int[]{0,2,0,0});
        PriorityInformation M = new PriorityInformation(new int[]{0, 2, 0, 1});

        assertEquals("maxed",info.progressMeasure(M,priority,odd).toString());
    }
    @org.junit.jupiter.api.Test
    public void testProgess3(){
        int priority = 3;
        boolean odd = priority%2==1;

        PriorityInformation info = new PriorityInformation(new int[]{0,2,0,0});
        PriorityInformation M = new PriorityInformation(new int[]{0, 2, 0, 1});

        assertEquals("[0, 2, 0, 1]",info.progressMeasure(M,priority,odd).toString());
    }
    @org.junit.jupiter.api.Test
    public void testProgess4(){
        int priority = 1;
        boolean odd = priority%2==1;

        PriorityInformation info = new PriorityInformation(new int[]{0,1,0,1});
        PriorityInformation M = new PriorityInformation(new int[]{0, 3, 0, 1});

        assertEquals("[0, 2, 0, 0]",info.progressMeasure(M,priority,odd).toString());
    }

    @org.junit.jupiter.api.Test
    public void testProgess5(){
        int priority = 2;
        boolean odd = priority%2==1;

        PriorityInformation info = new PriorityInformation(new int[]{0,1,0,1});
        PriorityInformation M = new PriorityInformation(new int[]{0, 3, 0, 1});

        assertEquals("[0, 1, 0, 0]",info.progressMeasure(M,priority,odd).toString());
    }

}
