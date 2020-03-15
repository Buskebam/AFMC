package smallprogressmeasure;

import paritygame.ParityGame;
import paritygame.ParityNode;

public class SmallProgressMeassure {

    PriorityInformation M = null;

    PriorityInformation[] parityProgress = null;

    ParityGame game = null;

    SmallProgressMeassure(ParityGame game){

        this.game = game;

        M = new PriorityInformation(game.getHighestPriority());

        for(int i = 0; i< game.getHighestNodeIdentifier() ; i++)
        {
            ParityNode node = game.getNode(i);

            if(node.isPriorityOdd()) { //increase count on odd priorities
                M.increaseCountPriority(node.getPriority());
            }
        }

        parityProgress = new PriorityInformation[game.getHighestNodeIdentifier()];

        for(int i = 0; i< game.getHighestNodeIdentifier(); i++)
        {
            parityProgress[i] = new PriorityInformation(game.getHighestPriority());
        }
    }

    PriorityInformation[] calculate(){

    }

    void lift(int identifier){
        PriorityInformation currentInfo = parityProgress[identifier];

        if(!currentInfo.isMaxed()) {
            int[] successors = game.getNode(identifier).getSuccessors();

            for (int i = 0; i < successors.length; i++) {
                PriorityInformation successorInfo = parityProgress[successors[i]];


            }
        }

    }


}
