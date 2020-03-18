import java.io.File;
import java.util.concurrent.Callable;

import paritygame.ParityGame;
import paritygame.ParityGameFactory;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import smallprogressmeasure.SmallProgressMeasure;


public class Main implements Callable<Integer> {
    @Parameters(index = "0", paramLabel = "GM_FILE", description = "Path to the parity game file (*.gm)")
    File gameFile;


    @Option(names = { "-l", "--lift-selection" },  description = "NAIVE, ...")
    private String algorithm = "NAIVE";

    @Option(names = { "-h", "--help" }, usageHelp = true, description = "display a help message")
    private boolean helpRequested = false;

    @Override
    public Integer call(){

        if (!gameFile.exists()) {
            System.out.println("ERROR: LTS file does not exist.");
            return -1;
        }

        ParityGame game = ParityGameFactory.getParityGame(gameFile);
        SmallProgressMeasure solver = new SmallProgressMeasure(game);

        long startTime = System.nanoTime();

        switch(algorithm)
        {
            case "NAIVE":
                solver.calculateNaive();
                break;

            default:
                System.out.println("Didnt recognize algorithm: " + algorithm);
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);

        solver.printResults();
        System.out.println("-------------------------------------------------------------");
        System.out.println("EvaluationTime: " + duration + "ns");

        return 0;
    }

    public static void main(String[] args) {
        new CommandLine(new Main()).execute(args);
    }

}