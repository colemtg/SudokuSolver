public class Square {
    private int number;
    private int[] possibilities = {1,1,1,1,1,1,1,1,1};
    public Square(int number)
    {
        this.number=number;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int newNum)
    {
        number=newNum;
    }

    public int[] getPossibilities() {
        return possibilities;
    }
    public void printChoices()
    {
        if(number!=0)
        {
            System.out.print(number);
        }
        else
        {
            for(int i=0; i<getPossibilities().length; i++)
            {
                if(getPossibilities()[i]!=0)
                {
                    System.out.print(i+1 + " ");
                }
            }
        }
    }
}
