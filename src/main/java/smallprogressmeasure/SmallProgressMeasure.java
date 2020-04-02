package smallprogressmeasure;

import paritygame.ParityGame;
import paritygame.ParityNode;

import javax.swing.*;
import java.util.Arrays;
import java.util.Random;

import static helperfunctions.ArrayHelper.contains;

public class SmallProgressMeasure {

    public final static int NAIVE_SCHEDULE = 0;
    public final static int PREDECESSOR_SCHEDULE = 1;
    public final static int RANDOM_SCHEDULE = 2;

    public final static int NO_ITERATION = 3;
    public final static int SELF_ITERATION = 4;
    public final static int MAX_ITERATION = 5;

    PriorityInformation M = null;

    PriorityInformation[] parityProgress = null;

    ParityGame game = null;

    long liftCounter = 0;

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


    public void calculate(int scheduleMode, int iterationMode, int seed)
    {
        //make schedule
        int[] schedule = new int[parityProgress.length];

        switch (scheduleMode){

            case NAIVE_SCHEDULE:

                System.out.println("Schedule: naive");

                //fil it with regular order
                for(int i = 0; i< schedule.length; i++) {
                    schedule[i] = i;
                }

                break;

            case PREDECESSOR_SCHEDULE:

                System.out.println("Schedule: predecessor");

                boolean[] scheduled = new boolean[parityProgress.length];

                int currentNode = -1;
                int everythingSheduledUntill = 0;

                for(int i = 0; i< schedule.length;i++) {

                    if(currentNode == -1) //all predesessors scheduled so new node has to be found or start state
                    {
                        //look for first not scheduled node
                        for(int j = everythingSheduledUntill; j<scheduled.length;j++)
                        {
                            if(!scheduled[j])
                            {
                                everythingSheduledUntill = j;
                                currentNode = j;
                                break;
                            }
                        }
                    }

                    schedule[i] = currentNode;
                    scheduled[currentNode] = true;

                    int[] currentPredecessors =  game.getNode(currentNode).getPredecessors();

                    currentNode = -1; //untill better found no new current

                    for (int j = 0; j < currentPredecessors.length; j++) {

                        int potentialPredecessor = currentPredecessors[j];

                        if(!scheduled[potentialPredecessor])
                        {
                            currentNode = potentialPredecessor;
                            break;
                        }
                    }

                }
                break;


            case RANDOM_SCHEDULE:
                System.out.println("Schedule: random");

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

                break;

            default:
                System.out.println("Did not recognize schedule");
        }


        //System.out.println(Arrays.toString(schedule));

        boolean somethingLifted = true;

        switch (iterationMode){

            case NO_ITERATION:

                System.out.println("Iteration: no");

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
                break;

            case MAX_ITERATION:

                System.out.println("Iteration: max");

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
                break;

            case SELF_ITERATION:

                System.out.println("Iteration: self");

                //We keep looping untill nothing is lifted anymore
                while(somethingLifted)
                {
                    somethingLifted = false;

                    //In the naive aproach we go through all indentifiers in order and lift
                    //them untill they do not change anymore.
                    for(int i = 0; i< parityProgress.length; i++) {

                        int index = schedule[i];

                        PriorityInformation current = parityProgress[index];

                        int[] successors = game.getNode(index).getSuccessors();
                        if (!current.isMaxed()) {
                            if (contains(successors, index)) {
                                lift(index);

                                while (!current.equals(parityProgress[index])) {
                                    current = parityProgress[index];
                                    lift(index);
                                    somethingLifted = true;
                                }

                            } else {

                                lift(index);
                                if (!current.equals(parityProgress[index])) {
                                    somethingLifted = true;
                                }

                            }
                        }
                    }
                }
                break;

            default:
                System.out.println("Did not recognize iteration mode");
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

        /*
        System.out.println("########################3");
        System.out.println(identifier);
        System.out.println("########################3");
        print();
        System.out.println("");

         */
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

    public void printResultZeroIdentifier()
    {
        if(liftCounter==0) {
            System.out.println("No results available");
        }
        else
        {
            System.out.println("Identifier: 0 OddWins: " + parityProgress[0].isMaxed());
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

    public long getLiftCounter() {
        return liftCounter;
    }
}
