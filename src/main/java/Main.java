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

    @Option(names = { "-l", "--lift-selection" },  description = "NAIVE, RANDOM_NAIVE, PREDECESSOR_NAIVE, ITERATIVE, RANDOM_ITERATIVE, SELF_ITERATIVE, PREDECESSOR_ITERATIVE, RANDOM_SELF_ITERATIVE, PREDECESSOR_SELF_ITERATIVE...")
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
            case "PREDECESSOR_NAIVE":
            case "PREDECESSOR_ITERATIVE":
            case "PREDECESSOR_SELF_ITERATIVE":
                game.calculatePredecessors();
                break;
        }

        SmallProgressMeasure solver = new SmallProgressMeasure(game);

        long startTime = System.nanoTime();

        switch(algorithm)
        {
            case "NAIVE":
                solver.calculate(NAIVE_SCHEDULE,NO_ITERATION,seed);
                break;

            case "RANDOM_NAIVE":
                solver.calculate(RANDOM_SCHEDULE,NO_ITERATION,seed);
                break;

            case "PREDECESSOR_NAIVE":
                solver.calculate(PREDECESSOR_SCHEDULE,NO_ITERATION,seed);
                break;

            case "ITERATIVE":
                solver.calculate(NAIVE_SCHEDULE,MAX_ITERATION,seed);
                break;

            case "RANDOM_ITERATIVE":
                solver.calculate(RANDOM_SCHEDULE,MAX_ITERATION,seed);
                break;

            case "PREDECESSOR_ITERATIVE":
                solver.calculate(PREDECESSOR_SCHEDULE,MAX_ITERATION,seed);
                break;

            case "SELF_ITERATIVE":
                solver.calculate(NAIVE_SCHEDULE,SELF_ITERATION,seed);
                break;

            case "RANDOM_SELF_ITERATIVE":
                solver.calculate(RANDOM_SCHEDULE,SELF_ITERATION,seed);
                break;

            case "PREDECESSOR_SELF_ITERATIVE":
                solver.calculate(PREDECESSOR_SCHEDULE,SELF_ITERATION,0);
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

        DecimalFormat formatter = new DecimalFormat("000,000,000");

        System.out.println("EvaluationTime: " + formatter.format(duration) + "ns");
        System.out.println("LiftCounter:    " + solver.getLiftCounter());

        return 0;
    }

    public static void main(String[] args) {
        new CommandLine(new Main()).execute(args);
    }

}