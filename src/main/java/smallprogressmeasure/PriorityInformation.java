package smallprogressmeasure;

public class PriorityInformation {

    int[] info = null;
    boolean maxed = false;

    PriorityInformation(int maximumPriority)
    {
        info = new int[maximumPriority+1];
    }

    PriorityInformation progressMeasure(PriorityInformation M, int priority, boolean isOdd )
    {
        PriorityInformation leastM = new PriorityInformation(info.length-1);

        if(!isOdd)
        {

        }
        else
        {
            currentIndex = 1;

            while()
            {


            }
        }

        return leasM;
    }

    void increaseCountPriority(int priority)
    {
        info[priority]++;
    }

    public boolean isMaxed() {
        return maxed;
    }

    public void setMaxed(boolean maxed) {
        this.maxed = maxed;
    }
}
