package parityGame;

import paritygame.ParityGame;
import paritygame.ParityGameFactory;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParityParserTest {
    @org.junit.jupiter.api.Test
    public void testFile1(){checkFile("ccp_games/german_linear_2.infinite_run_no_access.gm");}
    @org.junit.jupiter.api.Test
    public void testFile2(){checkFile("ccp_games/german_linear_2.invariantly_eventually_fair_shared_access.gm");}
    @org.junit.jupiter.api.Test
    public void testFile3(){checkFile("ccp_games/german_linear_3.infinite_run_no_access.gm");}
    @org.junit.jupiter.api.Test
    public void testFile4(){checkFile("ccp_games/german_linear_3.invariantly_eventually_fair_shared_access.gm");}


    @org.junit.jupiter.api.Test
    public void testFile5(){checkFile("dining_games/dining_2.invariantly_inevitably_eat.gm");}
    @org.junit.jupiter.api.Test
    public void testFile6(){checkFile("dining_games/dining_2.invariantly_plato_starves.gm");}
    @org.junit.jupiter.api.Test
    public void testFile7(){checkFile("dining_games/dining_2.invariantly_possibly_eat.gm");}
    @org.junit.jupiter.api.Test
    public void testFile8(){checkFile("dining_games/dining_2.plato_infinitely_often_can_eat.gm");}
    @org.junit.jupiter.api.Test
    public void testFile9(){checkFile("dining_games/dining_3.invariantly_inevitably_eat.gm");}
    @org.junit.jupiter.api.Test
    public void testFile10(){checkFile("dining_games/dining_3.invariantly_plato_starves.gm");}
    @org.junit.jupiter.api.Test
    public void testFile11(){checkFile("dining_games/dining_3.invariantly_possibly_eat.gm");}
    @org.junit.jupiter.api.Test
    public void testFile12(){checkFile("dining_games/dining_3.plato_infinitely_often_can_eat.gm");}
    @org.junit.jupiter.api.Test
    public void testFile13(){checkFile("dining_games/dining_4.invariantly_inevitably_eat.gm");}
    @org.junit.jupiter.api.Test
    public void testFile14(){checkFile("dining_games/dining_4.invariantly_plato_starves.gm");}
    @org.junit.jupiter.api.Test
    public void testFile15(){checkFile("dining_games/dining_4.invariantly_possibly_eat.gm");}
    @org.junit.jupiter.api.Test
    public void testFile16(){checkFile("dining_games/dining_4.plato_infinitely_often_can_eat.gm");}
    @org.junit.jupiter.api.Test
    public void testFile17(){checkFile("dining_games/dining_5.invariantly_inevitably_eat.gm");}
    @org.junit.jupiter.api.Test
    public void testFile18(){checkFile("dining_games/dining_5.invariantly_plato_starves.gm");}
    @org.junit.jupiter.api.Test
    public void testFile19(){checkFile("dining_games/dining_5.invariantly_possibly_eat.gm");}
    @org.junit.jupiter.api.Test
    public void testFile20(){checkFile("dining_games/dining_5.plato_infinitely_often_can_eat.gm");}


    @org.junit.jupiter.api.Test
    public void testFile21(){checkFile("elevator_games/elevator1_2.gm");}
    @org.junit.jupiter.api.Test
    public void testFile22(){checkFile("elevator_games/elevator1_3.gm");}
    @org.junit.jupiter.api.Test
    public void testFile23(){checkFile("elevator_games/elevator1_4.gm");}
    @org.junit.jupiter.api.Test
    public void testFile25(){checkFile("elevator_games/elevator2_2.gm");}
    @org.junit.jupiter.api.Test
    public void testFile26(){checkFile("elevator_games/elevator2_3.gm");}
    @org.junit.jupiter.api.Test
    public void testFile27(){checkFile("elevator_games/elevator2_4.gm");}


    private void checkFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getPath());


        ParityGame game = ParityGameFactory.getParityGame(file);

        String original = null;
        try {
            original = Files.readString(Paths.get(file.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(game.toString(),original );
    }
}
