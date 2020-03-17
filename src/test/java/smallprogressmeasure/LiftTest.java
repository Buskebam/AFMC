package smallprogressmeasure;

import paritygame.ParityGame;
import paritygame.ParityGameFactory;

import java.io.File;

public class LiftTest {
    @org.junit.jupiter.api.Test
    public void testLift1() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture8.gm").getPath());

        ParityGame game = ParityGameFactory.getParityGame(file);

        SmallProgressMeasure solver = new SmallProgressMeasure(game);

        solver.lift(3);
        solver.lift(4);
        solver.lift(2);
        solver.lift(1);
        solver.lift(0);
        solver.print();
    }
}
