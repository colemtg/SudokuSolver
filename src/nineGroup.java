import java.util.ArrayList;
import java.util.Arrays;

public class nineGroup {
    private Square[] group = new Square[9];
    private int[] in = {0,0,0,0,0,0,0,0,0};
    private int count=0;

    public void addSquare(Square newSquare)
    {
        if(count<9) {
            group[count] = newSquare;
            count++;
        }
    }

    public boolean update()
    {
        boolean update=false;
        int index, count;
        //update the possible numbers in the group
        //and the possible numbers for each square in the group
        for(int i=0; i<group.length; i++) {
            if (group[i].getNumber() != 0) {
                if(in[group[i].getNumber() - 1] != 1) {
                    update=true;
                    in[group[i].getNumber() - 1] = 1;
                    for (int j = 0; j < group.length; j++) {
                        group[j].getPossibilities()[group[i].getNumber() - 1] = 0;
                    }
                }
            }
        }

        //loop through each square in group
        for(int i=0; i<group.length; i++) {
            if(group[i].getNumber()==0) //if square does not have a value
            {
                //loop through possibilities of what current square could be
                for(int j=0; j<group[i].getPossibilities().length; j++)
                {
                    if(group[i].getPossibilities()[j]==1) //if 1 means can be that number index + 1
                    {
                        index=j; //number
                        count=0; //number of times number occurs
                        for(int k=0; k<group.length; k++) //loop through each square
                        {
                            if(group[k].getNumber()==0) // if square not already filled
                            {
                                //check if that square can also be current number
                                if(group[k].getPossibilities()[index]==1)
                                {
                                    count++; //if so increment count
                                    if(count>1)
                                    {
                                        k=group.length;
                                    }
                                }

                            }
                        }
                        // System.out.println(count);
                        if(count==1) //if count is 1 only sees itself
                        {
                            update=true;
                            group[i].setNumber(index+1); //by exclusion, square must be the number
                            for(int k=0; k<group.length; k++)
                            {
                                //update possibilities of each other square in group
                                group[k].getPossibilities()[group[i].getNumber()-1]=0;
                            }
                            j=group[i].getPossibilities().length;
                        }
                    }
                }
            }
        }
        //Naked subset
        if(!update || update) //not sure if need to up !update
        {
            int pos = 0;
            int same = 0;
            ArrayList<Integer> nums = new ArrayList<>();
            for (int i = 0; i < group.length; i++) {
                if (group[i].getNumber() == 0) {
                    pos = 0;
                    same = 0;
                    nums.clear();
                    for (int j = 0; j < group[i].getPossibilities().length; j++) {
                        //System.out.println("for loop: " + j);
                        if (group[i].getPossibilities()[j] != 0) {
                            pos++;
                            //      System.out.println("adding " + j);
                            nums.add(j);
                        }
                    }
                    //System.out.println(pos);
                    //group[i].printChoices();
                    //System.out.println();
                    for (int j = 0; j < group.length; j++) {
                        if(group[j].getNumber()==0) {
                            //group[j].printChoices();
                            //System.out.println();
                            if (Arrays.hashCode(group[j].getPossibilities()) == Arrays.hashCode(group[i].getPossibilities())) {
                                same++;
                                //System.out.println("found same: ");
                                //group[j].printChoices();
                                //System.out.println();
                            }
                        }
                    }
                    //System.out.println(same);
                    if (same == pos)
                    {
                        for (int j = 0; j < group.length; j++) {
                            //any square that does not have the same possibilities
                            if (Arrays.hashCode(group[j].getPossibilities()) != Arrays.hashCode(group[i].getPossibilities())) {
                                for (int k = 0; k < nums.size(); k++) {
                                    if (group[j].getPossibilities()[nums.get(k)] != 0) {
                                        update = true;
                                        group[j].getPossibilities()[nums.get(k)] = 0;
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }

        return update;
    }

    public boolean compUpdate()
    {
        boolean update=false;


        //hidden subset, should not update this and naked at same time

        if(!update)
        {
            ArrayList<ArrayList<Square>> squaresPerNum = new ArrayList<>();
            for(int i=0; i<squaresPerNum.size(); i++)
            {
                squaresPerNum.set(i,new ArrayList<>());
            }
            //Add all squares to respective place
            for(int i=0; i<group.length; i++)
            {
                if(group[i].getNumber()==0)
                {
                    for(int j=0; j<group[i].getPossibilities().length; j++)
                    {
                        if(group[i].getPossibilities()[j]!=0)
                        {
                            squaresPerNum.get(j).add((group[i]));
                        }
                    }
                }
            }
            int pos=0;
            int same=0;
            for(int i=0; i<squaresPerNum.size(); i++)
            {
                pos=squaresPerNum.get(i).size();
                for(int j=0; j<squaresPerNum.size(); j++)
                {
                    if(squaresPerNum.get(i).size()==squaresPerNum.get(j).size())
                    {
                        for(int k=0; k<squaresPerNum.get(i).size(); k++)
                        {
                            if(squaresPerNum.get(i).get(k).hashCode()!=squaresPerNum.get(j).get(k).hashCode())
                            {
                                same=same-1;
                                k=squaresPerNum.get(i).size();
                            }
                        }
                        same++;
                    }
                }
                if(same-1 == pos) //counts itself
                {
                    same=0;
                    for(int j=0; j<group.length; j++)
                    {
                        if (group[j].getPossibilities()[i]!=0)
                        {
                            same++;
                            group[j].getPossibilities()[i] = 0;
                        }
                    }
                    if(same!=pos)
                    {
                        System.out.println("hello");
                        update=true;
                    }
                    for(int j=0; j<squaresPerNum.get(i).size(); j++)
                    {
                        squaresPerNum.get(i).get(j).getPossibilities()[i]=1;
                    }
                }
            }
        }

        return update;
    }

    public Square[] getSquares() {
        return group;
    }

    public int[] getIn() {
        return in;
    }
    public void print()
    {
        for(int i=0; i<group.length; i++)
        {
            System.out.print(group[i].getNumber() + " ");
        }
    }
    public void printPossibilities()
    {
        for(int i=0; i<in.length; i++)
        {
            if(in[i]!=1)
            {
                System.out.print((i+1) + " ");
            }
        }
        System.out.println();
    }
    public void printgroupIn()
    {
        System.out.println();
        for(int i=0; i<group.length; i++)
        {
            System.out.println("square " + (i+1));
            group[i].printChoices();
            System.out.println();
        }
    }
}
