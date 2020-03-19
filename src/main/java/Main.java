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
    private File gameFile;

    @Option(names = { "-l", "--lift-selection" },  description = "NAIVE, RANDOM_NAIVE, ITERATIVE, RANDOM_ITERATIVE, SELF_ITERATIVE, RANDOM_SELF_ITERATIVE, ...")
    private String algorithm = "NAIVE";

    @Option(names = { "-s", "--seed" },  description = "Seed used for random generator")
    private int seed = 0;

    @Option(names = { "-a", "--print-all" },  description = "Print for every identifier who won")
    private boolean printAll = false;

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

            case "RANDOM_NAIVE":
                solver.calculateRandomNaive(seed);
                break;
            case "ITERATIVE":
                solver.calculateIterative();
                break;

            case "RANDOM_ITERATIVE":
                solver.calculateRandomIterative(seed);
                break;

            case "SELF_ITERATIVE":
                solver.calculateSelfIterative();
                break;

            case "RANDOM_SELF_ITERATIVE":
                solver.calculateRandomSelfIterative(seed);
                break;

            default:
                System.out.println("Didnt recognize algorithm: " + algorithm);
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);

        if(printAll) {
            solver.printResults();
        }
        else
        {
            solver.printResultZeroIdentifier();
        }
        System.out.println("-------------------------------------------------------------");
        System.out.println("EvaluationTime: " + duration + "ns");
        System.out.println("LiftCounter: " + solver.getLiftCounter());

        return 0;
    }

    public static void main(String[] args) {
        new CommandLine(new Main()).execute(args);
    }

}