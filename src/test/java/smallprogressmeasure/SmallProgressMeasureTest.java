package smallprogressmeasure;

import paritygame.ParityGame;
import paritygame.ParityGameFactory;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmallProgressMeasureTest {

    @org.junit.jupiter.api.Test
    public void testProgress1(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture8_1.gm").getPath());

        ParityGame game = ParityGameFactory.getParityGame(file);
        SmallProgressMeasure solver = new SmallProgressMeasure(game);
        solver.calculateNaive();

        assertEquals("[false, false, false, false, false]",solver.getOddWins());

    }

    @org.junit.jupiter.api.Test
    public void testProgress2(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture8_2.gm").getPath());

        ParityGame game = ParityGameFactory.getParityGame(file);
        SmallProgressMeasure solver = new SmallProgressMeasure(game);
        solver.calculateNaive();

        assertEquals("[true, true, true, true, true, true, true]",solver.getOddWins());

    }

    @org.junit.jupiter.api.Test
    public void testProgress3(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture8_3.gm").getPath());

        ParityGame game = ParityGameFactory.getParityGame(file);
        SmallProgressMeasure solver = new SmallProgressMeasure(game);
        solver.calculateNaive();

        assertEquals("[false, false, false, false]",solver.getOddWins());

    }

    @org.junit.jupiter.api.Test
    public void testProgress4(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture7_1.gm").getPath());

        ParityGame game = ParityGameFactory.getParityGame(file);
        SmallProgressMeasure solver = new SmallProgressMeasure(game);
        solver.calculateNaive();

        assertEquals("[false, false, false, true, false, true]",solver.getOddWins());

    }
    @org.junit.jupiter.api.Test
    public void testProgress5(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture7_2.gm").getPath());

        ParityGame game = ParityGameFactory.getParityGame(file);
        SmallProgressMeasure solver = new SmallProgressMeasure(game);
        solver.calculateNaive();

        assertEquals("[true, false, true]",solver.getOddWins());

    }

}
