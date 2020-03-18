package smallprogressmeasure;

import paritygame.ParityGame;
import paritygame.ParityNode;

import java.util.Arrays;
import java.util.Random;

public class SmallProgressMeasure {

    PriorityInformation M = null;

    PriorityInformation[] parityProgress = null;

    ParityGame game = null;

    int liftCounter = 0;

    public SmallProgressMeasure(ParityGame game){

        this.game = game;

        M = new PriorityInformation(game.getHighestPriority());

        for(int i = 0; i< game.getHighestNodeIdentifier() + 1; i++)
        {
            ParityNode node = game.getNode(i);

            if(node.isPriorityOdd()) { //increase count on odd priorities
                M.increaseCountPriority(node.getPriority());
            }
        }

        parityProgress = new PriorityInformation[game.getHighestNodeIdentifier() + 1];

        for(int i = 0; i< game.getHighestNodeIdentifier() + 1; i++)
        {
            parityProgress[i] = new PriorityInformation(game.getHighestPriority());
        }
    }

    public void calculateNaive(){

        boolean somethingLifted = true;

        //We keep looping untill nothing is lifted anymore
        while(somethingLifted)
        {
            somethingLifted = false;

            //In the naive aproach we go through all indentifiers in order and lift
            //them untill they do not change anymore.
            for(int i = 0; i< parityProgress.length; i++) {

                PriorityInformation current = parityProgress[i];
                if (!current.isMaxed())
                {
                    lift(i);
                    if (!current.equals(parityProgress[i])){
                        somethingLifted = true;
                    }
                }
            }
        }
    }

    public void calculateRandomNaive(int seed)
    {
        //make schedule
        int[] schedule = new int[parityProgress.length];

        //fil it with regular order
        for(int i = 0; i< schedule.length; i++) {
            schedule[i] = i;
        }

        //randomize schedule
        Random rand = new Random(seed);

        for (int i = 0; i < schedule.length; i++) {
            int randomIndexToSwap = rand.nextInt(schedule.length);
            int temp = schedule[randomIndexToSwap];
            schedule[randomIndexToSwap] = schedule[i];
            schedule[i] = temp;
        }

        //similar to naive but using schedule

        boolean somethingLifted = true;

        //We keep looping untill nothing is lifted anymore
        while(somethingLifted)
        {
            somethingLifted = false;

            //In the naive aproach we go through all indentifiers in order and lift
            //them untill they do not change anymore.
            for(int i = 0; i< parityProgress.length; i++) {

                int index = schedule[i];

                PriorityInformation current = parityProgress[index];
                if (!current.isMaxed())
                {
                    lift(index);
                    if (!current.equals(parityProgress[index])){
                        somethingLifted = true;
                    }
                }
            }
        }
    }

    public void calculateIterative(){

        boolean somethingLifted = true;

        //We keep looping untill nothing is lifted anymore
        while(somethingLifted)
        {
            somethingLifted = false;

            //In the naive aproach we go through all indentifiers in order and lift
            //them untill they do not change anymore.
            for(int i = 0; i< parityProgress.length; i++) {

                PriorityInformation current = parityProgress[i];

                if (!current.isMaxed())
                {
                    lift(i);

                    while (!current.equals(parityProgress[i])){
                        current = parityProgress[i];
                        lift(i);
                        somethingLifted = true;
                    }
                }
            }
        }
    }

    public void calculateRandomIterative(int seed)
    {
        //make schedule
        int[] schedule = new int[parityProgress.length];

        //fil it with regular order
        for(int i = 0; i< schedule.length; i++) {
            schedule[i] = i;
        }

        //randomize schedule
        Random rand = new Random(seed);

        for (int i = 0; i < schedule.length; i++) {
            int randomIndexToSwap = rand.nextInt(schedule.length);
            int temp = schedule[randomIndexToSwap];
            schedule[randomIndexToSwap] = schedule[i];
            schedule[i] = temp;
        }

        //similar to naive but using schedule

        boolean somethingLifted = true;

        //We keep looping untill nothing is lifted anymore
        while(somethingLifted)
        {
            somethingLifted = false;

            //In the naive aproach we go through all indentifiers in order and lift
            //them untill they do not change anymore.
            for(int i = 0; i< parityProgress.length; i++) {

                int index = schedule[i];

                PriorityInformation current = parityProgress[index];
                if (!current.isMaxed())
                {
                    lift(index);

                    while (!current.equals(parityProgress[index])){
                        current = parityProgress[index];
                        lift(index);
                        somethingLifted = true;
                    }
                }
            }
        }
    }


    void lift(int identifier){

        liftCounter++;

        ParityNode node = game.getNode(identifier);

        int[] successors = node.getSuccessors();

        PriorityInformation info = parityProgress[successors[0]]
                    .progressMeasure(M, node.getPriority(), node.isPriorityOdd());

        //For a even node we are intereset in smallest value and for the odd the biggest
        if(!node.isOwnerOdd()) {

            for (int i = 1; i < successors.length; i++) {

                PriorityInformation smallProgress = parityProgress[successors[i]]
                            .progressMeasure(M,node.getPriority(),node.isPriorityOdd());



                info = info.getSmallest(smallProgress);
            }
        }
        else {

            for (int i = 1; i < successors.length; i++) {

                PriorityInformation smallProgress = parityProgress[successors[i]]
                        .progressMeasure(M,node.getPriority(),node.isPriorityOdd());

                info = info.getBiggest(smallProgress);

            }
        }
        //save result
        parityProgress[identifier] = info;
    }


    public void printResults(){
        if(liftCounter==0) {
            System.out.println("No results available");
        }
        else
        {
            for (int i = 0; i < parityProgress.length; i++) {
                System.out.println("Identifier: " + i + " OddWins: " + parityProgress[i].isMaxed());
            }
        }
    }

    String getOddWins(){

        boolean[] oddWins = new boolean[parityProgress.length];

        for(int i = 0; i< parityProgress.length; i++)
        {
            oddWins[i] = parityProgress[i].isMaxed();
        }
        return  Arrays.toString(oddWins);
    }

    public void print()
    {
        for(int i = 0; i< parityProgress.length; i++) {
            System.out.println("Identifer: " + i + " Found: " + parityProgress[i] );
        }
    }

    public int getLiftCounter() {
        return liftCounter;
    }
}
