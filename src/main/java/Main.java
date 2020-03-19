import java.io.File;
import java.text.DecimalFormat;
import java.util.Formatter;
import java.util.concurrent.Callable;

import paritygame.ParityGame;
import paritygame.ParityGameFactory;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import smallprogressmeasure.SmallProgressMeasure;

import static smallprogressmeasure.SmallProgressMeasure.*;


public class Main implements Callable<Integer> {
    @Parameters(index = "0", paramLabel = "GM_FILE", description = "Path to the parity game file (*.gm)")
    private File gameFile;

    @Option(names = { "-l", "--lift-selection" },  description = "NAIVE_NO_ITERATION, RANDOM_NO_ITERATION, PREDECESSOR_NO_ITERATION, " +
                                                                 "NAIVE_MAX_ITERATION, RANDOM_MAX_ITERATION, PREDECESSOR_MAX_ITERATION, " +
                                                                 "NAIVE_SELF_ITERATION, RANDOM_SELF_ITERATION, PREDECESSOR_SELF_ITERATION...")
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


        switch (algorithm)
        {
            case "PREDECESSOR_NO_ITERATION":
            case "PREDECESSOR_MAX_ITERATION":
            case "PREDECESSOR_SELF_ITERATION":
                game.calculatePredecessors();
                break;
        }

        SmallProgressMeasure solver = new SmallProgressMeasure(game);

        long startTime = System.nanoTime();

        switch(algorithm)
        {
            case "NAIVE_NO_ITERATION":
                solver.calculate(NAIVE_SCHEDULE,NO_ITERATION,seed);
                break;

            case "RANDOM_NO_ITERATION":
                solver.calculate(RANDOM_SCHEDULE,NO_ITERATION,seed);
                break;

            case "PREDECESSOR_NO_ITERATION":
                solver.calculate(PREDECESSOR_SCHEDULE,NO_ITERATION,seed);
                break;

            case "NAIVE_MAX_ITERATION":
                solver.calculate(NAIVE_SCHEDULE,MAX_ITERATION,seed);
                break;

            case "RANDOM_MAX_ITERATION":
                solver.calculate(RANDOM_SCHEDULE,MAX_ITERATION,seed);
                break;

            case "PREDECESSOR_MAX_ITERATION":
                solver.calculate(PREDECESSOR_SCHEDULE,MAX_ITERATION,seed);
                break;

            case "NAIVE_SELF_ITERATION":
                solver.calculate(NAIVE_SCHEDULE,SELF_ITERATION,seed);
                break;

            case "RANDOM_SELF_ITERATION":
                solver.calculate(RANDOM_SCHEDULE,SELF_ITERATION,seed);
                break;

            case "PREDECESSOR_SELF_ITERATION":
                solver.calculate(PREDECESSOR_SCHEDULE,SELF_ITERATION,0);
                break;

            default:
                System.out.println("Didnt recognize algorithm: " + algorithm);
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);

        System.out.println("-------------------------------------------------------------");

        if(printAll) {
            solver.printResults();
        }
        else
        {
            solver.printResultZeroIdentifier();
        }
        System.out.println("-------------------------------------------------------------");

        DecimalFormat formatter = new DecimalFormat("0,000,000,000");

        System.out.println("EvaluationTime: " + formatter.format(duration) + "ns");
        System.out.println("LiftCounter:    " + solver.getLiftCounter());

        return 0;
    }

    public static void main(String[] args) {
        new CommandLine(new Main()).execute(args);
    }

}