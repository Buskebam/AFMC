package smallprogressmeasure;

import paritygame.ParityGame;
import paritygame.ParityGameFactory;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static smallprogressmeasure.SmallProgressMeasure.NAIVE_SCHEDULE;
import static smallprogressmeasure.SmallProgressMeasure.NO_ITERATION;

public class SmallProgressMeasureTest {

    @org.junit.jupiter.api.Test
    public void testProgressNaive1(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture8_1.gm").getPath());

        ParityGame game = ParityGameFactory.getParityGame(file);
        SmallProgressMeasure solver = new SmallProgressMeasure(game);
        solver.calculate(NAIVE_SCHEDULE,NO_ITERATION,0);

        assertEquals("[false, false, false, false, false]",solver.getOddWins());

    }

    @org.junit.jupiter.api.Test
    public void testProgressNaive2(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture8_2.gm").getPath());

        ParityGame game = ParityGameFactory.getParityGame(file);
        SmallProgressMeasure solver = new SmallProgressMeasure(game);
        solver.calculate(NAIVE_SCHEDULE,NO_ITERATION,0);

        assertEquals("[true, true, true, true, true, true, true]",solver.getOddWins());

    }

    @org.junit.jupiter.api.Test
    public void testProgressNaive3(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture8_3.gm").getPath());

        ParityGame game = ParityGameFactory.getParityGame(file);
        SmallProgressMeasure solver = new SmallProgressMeasure(game);
        solver.calculate(NAIVE_SCHEDULE,NO_ITERATION,0);

        assertEquals("[false, false, false, false]",solver.getOddWins());

    }

    @org.junit.jupiter.api.Test
    public void testProgressNaive4(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture7_1.gm").getPath());

        ParityGame game = ParityGameFactory.getParityGame(file);
        SmallProgressMeasure solver = new SmallProgressMeasure(game);
        solver.calculate(NAIVE_SCHEDULE,NO_ITERATION,0);

        assertEquals("[false, false, false, true, false, true]",solver.getOddWins());

    }
    @org.junit.jupiter.api.Test
    public void testProgressNaive5(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture7_2.gm").getPath());

        ParityGame game = ParityGameFactory.getParityGame(file);
        SmallProgressMeasure solver = new SmallProgressMeasure(game);
        solver.calculate(NAIVE_SCHEDULE,NO_ITERATION,0);

        assertEquals("[true, false, true]",solver.getOddWins());

    }



}
