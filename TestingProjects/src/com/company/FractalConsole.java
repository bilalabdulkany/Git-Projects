package com.company;

import java.awt.*;
import java.util.Arrays;

public class FractalConsole {

    public static void main(String [] args){
        FractalConsole fc= new FractalConsole();
        fc.drawFractal(12,5, 10, 40, 40);
        Arrays.stream(mPixels).forEach(c-> System.out.println(c));
    }
    static char[][] mPixels = new char[60][60];
    public void drawFractal(int level, int xA, int yA, int xB, int yB) {
        if (level == 0)
            line(xA, yA, xB, yB);
        else {
            // calculate midpoint between (xA, yA) and (xB, yB)
            int xC = (xA + xB) / 2;
            int yC = (yA + yB) / 2;

            // calculate the fourth point (xD, yD) which forms an
            // isosceles right triangle between (xA, yA) and (xC, yC)
            // where the right angle is at (xD, yD)
            int xD = xA + (xC - xA) / 2 - (yC - yA) / 2;
            int yD = yA + (yC - yA) / 2 + (xC - xA) / 2;

            // recursively draw the Fractal
            drawFractal(level - 1, xD, yD, xA, yA);
            drawFractal(level - 1, xD, yD, xC, yC);
            drawFractal(level - 1, xD, yD, xB, yB);
        }
    }



    public static void line(int startRow, int startColumn, int endRow, int endColumn)
    {
        double dY = endRow - startRow;
        double dX = endColumn - startColumn;
        double slope = dX / dY;
        slope = Math.abs(slope);

        int monitorX = mPixels.length;
        int monitorY = mPixels[0].length;

        //if(slope >= 1)
        {
            double progress = -(dY / dX);
            for(int i=startColumn; i<=endColumn; i++)
            {
                double j = startRow - (int) ((i-startColumn) * progress);
                int yLoc = (int) (Math.round( j * 100.0 ) / 100.0);

                mPixels[i][monitorX-yLoc-1] = '*';
            }
        }


    }
}
