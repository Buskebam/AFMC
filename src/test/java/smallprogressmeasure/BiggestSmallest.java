package smallprogressmeasure;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BiggestSmallest {

    @org.junit.jupiter.api.Test
    public void testBiggest1(){
        PriorityInformation x = new PriorityInformation(new int[]{0,2,0,0});
        PriorityInformation y = new PriorityInformation(new int[]{0,2,0,1});

        PriorityInformation biggest = x.getBiggest(y);

        assertEquals(y,biggest);
    }

    @org.junit.jupiter.api.Test
    public void testBiggest2(){
        PriorityInformation x = new PriorityInformation(new int[]{0,2,0,0});
        PriorityInformation y = new PriorityInformation(new int[]{0,2,0,1});

        x.setMaxed(true);

        PriorityInformation biggest = x.getBiggest(y);

        assertEquals(x,biggest);
    }

    @org.junit.jupiter.api.Test
    public void testBiggest3(){
        PriorityInformation x = new PriorityInformation(new int[]{0,2,4,0}); //ignore even entries
        PriorityInformation y = new PriorityInformation(new int[]{0,2,0,1});

        PriorityInformation biggest = x.getBiggest(y);

        assertEquals(y,biggest);
    }

    @org.junit.jupiter.api.Test
    public void testBiggest4(){
        PriorityInformation x = new PriorityInformation(new int[]{0,2,0,3});
        PriorityInformation y = new PriorityInformation(new int[]{0,2,0,1});

        PriorityInformation biggest = x.getBiggest(y);

        assertEquals(x,biggest);
    }

    @org.junit.jupiter.api.Test
    public void testSmallest1(){
        PriorityInformation x = new PriorityInformation(new int[]{0,2,0,0});
        PriorityInformation y = new PriorityInformation(new int[]{0,2,0,1});

        PriorityInformation biggest = x.getSmallest(y);

        assertEquals(x,biggest);
    }

    @org.junit.jupiter.api.Test
    public void testSmallest2(){
        PriorityInformation x = new PriorityInformation(new int[]{0,2,0,0});
        PriorityInformation y = new PriorityInformation(new int[]{0,2,0,1});

        x.setMaxed(true);

        PriorityInformation biggest = x.getSmallest(y);

        assertEquals(y,biggest);
    }

    @org.junit.jupiter.api.Test
    public void testSmallest3(){
        PriorityInformation x = new PriorityInformation(new int[]{0,2,4,0}); //ignore even entries
        PriorityInformation y = new PriorityInformation(new int[]{0,2,0,1});

        PriorityInformation biggest = x.getSmallest(y);

        assertEquals(x,biggest);
    }

    @org.junit.jupiter.api.Test
    public void testSmallest4(){
        PriorityInformation x = new PriorityInformation(new int[]{0,2,0,3});
        PriorityInformation y = new PriorityInformation(new int[]{0,2,0,1});

        PriorityInformation biggest = x.getSmallest(y);

        assertEquals(y,biggest);
    }
}
