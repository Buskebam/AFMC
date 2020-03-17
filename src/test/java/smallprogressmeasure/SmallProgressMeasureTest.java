package smallprogressmeasure;

import paritygame.ParityGame;
import paritygame.ParityGameFactory;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmallProgressMeasureTest {

    @org.junit.jupiter.api.Test
    public void testProgress1(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sheet_examples/lecture8.gm").getPath());


        ParityGame game = ParityGameFactory.getParityGame(file);

        SmallProgressMeasure solver = new SmallProgressMeasure(game);
        solver.calculate();
        solver.print();
        solver.printResults();

    }

}
