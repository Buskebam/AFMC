package smallprogressmeasure;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BiggestSmallest {

    @org.junit.jupiter.api.Test
    public void testBiggest1(){
        PriorityInformation x = new PriorityInformation(new int[]{0,2,0,0});
        PriorityInformation y = new PriorityInformation(new int[]{0,2,0,1});

        String biggest = x.getBiggest(y).toString();

        assertEquals("[0, 2, 0, 1]",biggest);
    }

    @org.junit.jupiter.api.Test
    public void testBiggest2(){
        PriorityInformation x = new PriorityInformation(new int[]{0,2,0,0});
        PriorityInformation y = new PriorityInformation(new int[]{0,2,0,1});

        x.setMaxed(true);

        String biggest = x.getBiggest(y).toString();

        assertEquals("maxed",biggest);
    }

    @org.junit.jupiter.api.Test
    public void testBiggest3(){
        PriorityInformation x = new PriorityInformation(new int[]{0,2,4,0}); //ignore even entries
        PriorityInformation y = new PriorityInformation(new int[]{0,2,0,1});

        String biggest = x.getBiggest(y).toString();

        assertEquals("[0, 2, 0, 1]",biggest);
    }

    @org.junit.jupiter.api.Test
    public void testBiggest4(){
        PriorityInformation x = new PriorityInformation(new int[]{0,2,0,3});
        PriorityInformation y = new PriorityInformation(new int[]{0,2,0,1});

        String biggest = x.getBiggest(y).toString();

        assertEquals("[0, 2, 0, 3]",biggest);
    }

    @org.junit.jupiter.api.Test
    public void testSmallest1(){
        PriorityInformation x = new PriorityInformation(new int[]{0,2,0,0});
        PriorityInformation y = new PriorityInformation(new int[]{0,2,0,1});

        String smallest = x.getSmallest(y).toString();

        assertEquals("[0, 2, 0, 0]",smallest);
    }

    @org.junit.jupiter.api.Test
    public void testSmallest2(){
        PriorityInformation x = new PriorityInformation(new int[]{0,2,0,0});
        PriorityInformation y = new PriorityInformation(new int[]{0,2,0,1});

        x.setMaxed(true);

        String smallest = x.getSmallest(y).toString();

        assertEquals("[0, 2, 0, 1]",smallest);
    }

    @org.junit.jupiter.api.Test
    public void testSmallest3(){
        PriorityInformation x = new PriorityInformation(new int[]{0,2,4,0}); //ignore even entries
        PriorityInformation y = new PriorityInformation(new int[]{0,2,0,1});

        String smallest = x.getSmallest(y).toString();

        assertEquals("[0, 2, 4, 0]",smallest);
    }

    @org.junit.jupiter.api.Test
    public void testSmallest4(){
        PriorityInformation x = new PriorityInformation(new int[]{0,2,0,3});
        PriorityInformation y = new PriorityInformation(new int[]{0,2,0,1});

        String smallest = x.getSmallest(y).toString();

        assertEquals("[0, 2, 0, 1]",smallest);
    }
}
