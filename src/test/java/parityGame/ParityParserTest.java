package parityGame;

import paritygame.ParityGame;
import paritygame.ParityGameFactory;


import java.io.File;


public class ParityParserTest {
    @org.junit.jupiter.api.Test
    public void testFile1(){
        checkFile("ccp_games/german_linear_2.infinite_run_no_access.gm");
    }

    public void testFile2(){
        checkFile("dining_games/dining_2.invariantly_possibly_eat.gm");
    }

    public void testFile3(){
        checkFile("elavator_games/elevator1_4.gm");
    }





    private void checkFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getPath());


        ParityGame game = ParityGameFactory.getParityGame(file);

        //compare original with printed parsed version
        //assertEquals(original, formula.toString());
    }
}
