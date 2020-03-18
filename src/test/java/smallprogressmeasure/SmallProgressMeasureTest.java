package smallprogressmeasure;

import paritygame.ParityGame;
import paritygame.ParityGameFactory;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmallProgressMeasureTest {

    @org.junit.jupiter.api.Test
    public void testProgressNaive1(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture8_1.gm").getPath());

        ParityGame game = ParityGameFactory.getParityGame(file);
        SmallProgressMeasure solver = new SmallProgressMeasure(game);
        solver.calculateNaive();

        assertEquals("[false, false, false, false, false]",solver.getOddWins());

    }

    @org.junit.jupiter.api.Test
    public void testProgressNaive2(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture8_2.gm").getPath());

        ParityGame game = ParityGameFactory.getParityGame(file);
        SmallProgressMeasure solver = new SmallProgressMeasure(game);
        solver.calculateNaive();

        assertEquals("[true, true, true, true, true, true, true]",solver.getOddWins());

    }

    @org.junit.jupiter.api.Test
    public void testProgressNaive3(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture8_3.gm").getPath());

        ParityGame game = ParityGameFactory.getParityGame(file);
        SmallProgressMeasure solver = new SmallProgressMeasure(game);
        solver.calculateNaive();

        assertEquals("[false, false, false, false]",solver.getOddWins());

    }

    @org.junit.jupiter.api.Test
    public void testProgressNaive4(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture7_1.gm").getPath());

        ParityGame game = ParityGameFactory.getParityGame(file);
        SmallProgressMeasure solver = new SmallProgressMeasure(game);
        solver.calculateNaive();

        assertEquals("[false, false, false, true, false, true]",solver.getOddWins());

    }
    @org.junit.jupiter.api.Test
    public void testProgressNaive5(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture7_2.gm").getPath());

        ParityGame game = ParityGameFactory.getParityGame(file);
        SmallProgressMeasure solver = new SmallProgressMeasure(game);
        solver.calculateNaive();

        assertEquals("[true, false, true]",solver.getOddWins());

    }

    @org.junit.jupiter.api.Test
    public void testProgressNaiveRandom1(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture8_1.gm").getPath());

        ParityGame game = ParityGameFactory.getParityGame(file);
        SmallProgressMeasure solver = new SmallProgressMeasure(game);
        solver.calculateRandomNaive(0);

        assertEquals("[false, false, false, false, false]",solver.getOddWins());

    }

    @org.junit.jupiter.api.Test
    public void testProgressNaiveRandom2(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture8_2.gm").getPath());

        ParityGame game = ParityGameFactory.getParityGame(file);
        SmallProgressMeasure solver = new SmallProgressMeasure(game);
        solver.calculateRandomNaive(0);

        assertEquals("[true, true, true, true, true, true, true]",solver.getOddWins());

    }

    @org.junit.jupiter.api.Test
    public void testProgressNaiveRandom3(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture8_3.gm").getPath());

        ParityGame game = ParityGameFactory.getParityGame(file);
        SmallProgressMeasure solver = new SmallProgressMeasure(game);
        solver.calculateRandomNaive(0);

        assertEquals("[false, false, false, false]",solver.getOddWins());

    }

    @org.junit.jupiter.api.Test
    public void testProgressNaiveRandom4(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture7_1.gm").getPath());

        ParityGame game = ParityGameFactory.getParityGame(file);
        SmallProgressMeasure solver = new SmallProgressMeasure(game);
        solver.calculateRandomNaive(0);

        assertEquals("[false, false, false, true, false, true]",solver.getOddWins());

    }
    @org.junit.jupiter.api.Test
    public void testProgressNaiveRandom5(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture7_2.gm").getPath());

        ParityGame game = ParityGameFactory.getParityGame(file);
        SmallProgressMeasure solver = new SmallProgressMeasure(game);
        solver.calculateRandomNaive(0);

        assertEquals("[true, false, true]",solver.getOddWins());

    }
    @org.junit.jupiter.api.Test
    public void testProgressIterative1(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture8_1.gm").getPath());

        ParityGame game = ParityGameFactory.getParityGame(file);
        SmallProgressMeasure solver = new SmallProgressMeasure(game);
        solver.calculateIterative();

        assertEquals("[false, false, false, false, false]",solver.getOddWins());

    }

    @org.junit.jupiter.api.Test
    public void testProgressIterative2(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture8_2.gm").getPath());

        ParityGame game = ParityGameFactory.getParityGame(file);
        SmallProgressMeasure solver = new SmallProgressMeasure(game);
        solver.calculateIterative();

        assertEquals("[true, true, true, true, true, true, true]",solver.getOddWins());

    }

    @org.junit.jupiter.api.Test
    public void testProgressIterative3(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture8_3.gm").getPath());

        ParityGame game = ParityGameFactory.getParityGame(file);
        SmallProgressMeasure solver = new SmallProgressMeasure(game);
        solver.calculateIterative();

        assertEquals("[false, false, false, false]",solver.getOddWins());

    }

    @org.junit.jupiter.api.Test
    public void testProgressIterative4(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture7_1.gm").getPath());

        ParityGame game = ParityGameFactory.getParityGame(file);
        SmallProgressMeasure solver = new SmallProgressMeasure(game);
        solver.calculateIterative();

        assertEquals("[false, false, false, true, false, true]",solver.getOddWins());

    }
    @org.junit.jupiter.api.Test
    public void testProgressIterative5(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture7_2.gm").getPath());

        ParityGame game = ParityGameFactory.getParityGame(file);
        SmallProgressMeasure solver = new SmallProgressMeasure(game);
        solver.calculateIterative();

        assertEquals("[true, false, true]",solver.getOddWins());

    }

    @org.junit.jupiter.api.Test
    public void testProgressIterativeRandom1(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture8_1.gm").getPath());

        ParityGame game = ParityGameFactory.getParityGame(file);
        SmallProgressMeasure solver = new SmallProgressMeasure(game);
        solver.calculateRandomIterative(0);

        assertEquals("[false, false, false, false, false]",solver.getOddWins());

    }

    @org.junit.jupiter.api.Test
    public void testProgressIterativeRandom2(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture8_2.gm").getPath());

        ParityGame game = ParityGameFactory.getParityGame(file);
        SmallProgressMeasure solver = new SmallProgressMeasure(game);
        solver.calculateRandomIterative(0);

        assertEquals("[true, true, true, true, true, true, true]",solver.getOddWins());

    }

    @org.junit.jupiter.api.Test
    public void testProgressIterativeRandom3(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture8_3.gm").getPath());

        ParityGame game = ParityGameFactory.getParityGame(file);
        SmallProgressMeasure solver = new SmallProgressMeasure(game);
        solver.calculateRandomIterative(0);

        assertEquals("[false, false, false, false]",solver.getOddWins());

    }

    @org.junit.jupiter.api.Test
    public void testProgressIterativeRandom4(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture7_1.gm").getPath());

        ParityGame game = ParityGameFactory.getParityGame(file);
        SmallProgressMeasure solver = new SmallProgressMeasure(game);
        solver.calculateRandomIterative(0);

        assertEquals("[false, false, false, true, false, true]",solver.getOddWins());

    }
    @org.junit.jupiter.api.Test
    public void testProgressIterativeRandom5(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture7_2.gm").getPath());

        ParityGame game = ParityGameFactory.getParityGame(file);
        SmallProgressMeasure solver = new SmallProgressMeasure(game);
        solver.calculateRandomIterative(0);

        assertEquals("[true, false, true]",solver.getOddWins());

    }

}
