import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class Driver {
    public static void main(String args[]) throws IOException
    {

        URL path = Driver.class.getResource("TestsFiles/test.txt"); //Change to new
        FileReader file =  new FileReader(path.getFile());
        BufferedReader buffer = new BufferedReader(file);
        String tempLine;
        int[][] nums = new int[9][9];
        int row=0;
        //int col=0;
        nineGroup[] rows = {new Row(),new Row(),new Row(),new Row(),new Row(),new Row(),new Row(),new Row(),new Row()};
        nineGroup[] cols = {new Col(),new Col(),new Col(),new Col(),new Col(),new Col(),new Col(),new Col(),new Col()};
        nineGroup[] boxes = {new Box(),new Box(),new Box(),new Box(),new Box(),new Box(),new Box(),new Box(),new Box()};
        while ((tempLine=buffer.readLine())!=null)
        {
            rows[row]=new Row();
            for(int i=0; i<9; i++)
            {
                nums[row][i] = tempLine.charAt(i*2)-'0';
                //rows[row].addSquare(new Square(tempLine.charAt(i*2)-'0'));
            }
            row++;
        }
        //pass an array in here
        System.out.println("2D array");
        for(int i=0; i<nums.length; i++)
        {
            for(int j=0; j<nums[i].length; j++)
            {
                System.out.print(nums[i][j] + " ");
            }
            System.out.println();
        }


        //fill objects (rows)
        for(int i=0; i<nums.length; i++)
        {
            for(int j=0; j<nums[i].length; j++)
            {
                rows[i].addSquare(new Square(nums[i][j]));
            }
           // row++;
        }

        //fill objects (cols+boxes)
        for(int i=0; i<rows.length; i++)
        {
            for(int j=0; j<rows[i].getSquares().length; j++)
            {
                //Add Boxes:
                if(i<3 && j<3)
                {
                    boxes[0].addSquare(rows[i].getSquares()[j]);
                }
                else if(i<6 && j<3)
                {
                    boxes[1].addSquare(rows[i].getSquares()[j]);
                }
                else if(i<9 && j<3)
                {
                    boxes[2].addSquare(rows[i].getSquares()[j]);
                }
                else if(i<3 && j<6)
                {
                    boxes[3].addSquare(rows[i].getSquares()[j]);
                }
                else if(i<6 && j<6)
                {
                    boxes[4].addSquare(rows[i].getSquares()[j]);
                }
                else if(i<9 && j<6)
                {
                    boxes[5].addSquare(rows[i].getSquares()[j]);
                }
                else if(i<3 && j<9)
                {
                    boxes[6].addSquare(rows[i].getSquares()[j]);
                }
                else if(i<6 && j<9)
                {
                    boxes[7].addSquare(rows[i].getSquares()[j]);
                }
                else if(i<9 && j<9)
                {
                    boxes[8].addSquare(rows[i].getSquares()[j]);
                }

                cols[j].addSquare(rows[i].getSquares()[j]);
                //System.out.print(cols[j].getSquares()[j].getNumber() + " ");
                //System.out.print(rows[i].getSquares()[j].getNumber() + " ");
            }
        }
        /*
        System.out.println("rows");
        for(int i=0; i<rows.length; i++)
        {
            rows[i].printRow();
            System.out.println();
        }

        System.out.println("Cols");
        for(int i=0; i<cols.length; i++)
        {
            cols[i].printCol();
            System.out.println();
        }

        System.out.println("Boxes");
        for(int i=0; i<boxes.length; i++)
        {
            boxes[i].printBox();
            System.out.println();
        }
        */
        System.out.println();
        /*
        rows[0].printPossibilities();
        rows[0].printSquaresInRow();
        rows[0].update();
        rows[0].printPossibilities();
        rows[0].printSquaresInRow();
        */
        boolean update=true;
        while (update)
        {
            update=false;
            for (int i = 0; i < rows.length; i++) {
                if(rows[i].update()) update=true;
            }
            for (int i = 0; i < cols.length; i++) {
                if(cols[i].update()) update=true;
            }
            for (int i = 0; i < boxes.length; i++) {
                if(boxes[i].update()) update=true;

            }
            /*
            if(!update)
            {


                for (int i = 0; i < rows.length; i++) {
                    if(rows[i].compUpdate())
                    {
                        update = true;
                        i=rows.length;
                    }
                }

                if(!update) {
                    for (int i = 0; i < cols.length; i++) {
                        if (cols[i].compUpdate()) {
                            update = true;
                            i = rows.length;
                        }
                    }
                }
                if(!update) {
                    for (int i = 0; i < boxes.length; i++) {
                        if (boxes[i].compUpdate()) {
                            update = true;
                            i = rows.length;
                        }
                        //System.out.println("box " + (i+1));
                        //boxes[i].printPossibilities();
                        //boxes[i].printSquaresInBox();
                    }
                }

            }
            */

        }
        /*
        for(int i=0; i<9; i++)
        {
            rows[i].printSquaresInRow();
        }
        */
        System.out.println("Solved:");
        for(int i=0; i<rows.length; i++)
        {
            rows[i].print();
            System.out.println();
        }


    }

}
