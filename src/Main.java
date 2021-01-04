import java.lang.Math;
import java.util.ArrayList;

public class Main {

    public static int rollDice(int sides){

      return (int)Math.floor(Math.random() * sides) + 1;

    }

    public static int returnLowestFromRolls(int rollsCount, int sides){

        ArrayList<Integer> rollsList = new ArrayList<Integer>();
        for(int i = 0; i < rollsCount; i++){
            rollsList.add(rollDice(sides));
        }

        int lowest = sides;
        for(int i = 0; i < rollsCount; i++){
            if(rollsList.get(i) < lowest){
                lowest = rollsList.get(i);
            }
            if(lowest == 1){
                return 1;
            }
        }

        return lowest;

    }

    public static int sumFromRollsButDropLowest(int realRollsCount, int sides){

        ArrayList<Integer> rollsList = new ArrayList<Integer>();
        for(int i = 0; i < realRollsCount; i++){
            rollsList.add(rollDice(sides));
        }

        int lowest = sides;
        for(int i = 0; i < realRollsCount; i++){
            if(rollsList.get(i) < lowest){
                lowest = rollsList.get(i);
            }
            if(lowest == 1){
                break;
            }
        }

        int sum = 0;
        for (Integer roll : rollsList){
            sum += roll;
        }

        sum -= lowest;
        return sum;

    }

    public static int matrixSum(int sides){

        int[][] tiktaktoe = new int[3][3];

        for(int i = 0; i < tiktaktoe.length; i++){
            for(int j = 0; j < tiktaktoe.length; j++){

                tiktaktoe[i][j] = sumFromRollsButDropLowest(4, 6);

            }
        }

        ArrayList<Integer> sums = new ArrayList<Integer>();
        sums.add(tiktaktoe[0][0] + tiktaktoe[1][0] + tiktaktoe[2][0]);
        sums.add(tiktaktoe[0][1] + tiktaktoe[1][1] + tiktaktoe[2][1]);
        sums.add(tiktaktoe[0][2] + tiktaktoe[1][2] + tiktaktoe[2][2]);
        sums.add(tiktaktoe[0][0] + tiktaktoe[0][1] + tiktaktoe[0][2]);
        sums.add(tiktaktoe[1][0] + tiktaktoe[1][1] + tiktaktoe[1][2]);
        sums.add(tiktaktoe[2][0] + tiktaktoe[2][1] + tiktaktoe[2][2]);
        sums.add(tiktaktoe[0][0] + tiktaktoe[1][1] + tiktaktoe[2][2]);
        sums.add(tiktaktoe[0][2] + tiktaktoe[1][1] + tiktaktoe[2][0]);

        /*
        String stringRepresentation = "";
        for(int i = 0; i < tiktaktoe.length; i++){

            stringRepresentation += "[";

            for(int j = 0; j < tiktaktoe.length; j++){

                stringRepresentation += (tiktaktoe[i][j] + ", ");

            }

            stringRepresentation += "]\n";
        }
        System.out.println(stringRepresentation);
         */

        int highest = 0;
        int highestIndex = 0;
        for (int i = 0; i < sums.size(); i++){
            if(sums.get(i) > highest){
                highest = sums.get(i);
                highestIndex = i;
            }
        }

        sums.remove(highestIndex);
        int secondHighest = 0;
        for (int i = 0; i < sums.size(); i++){
            if(sums.get(i) > secondHighest){
                secondHighest = sums.get(i);
            }
        }

        return highest + secondHighest;


    }

    public static void main(String[] args){

        int iterations = 100000;
        int sum = 0;

        for(int i = 0; i < iterations; i++) {

            double thisRoll = matrixSum(6);
            sum += thisRoll;

        }

        System.out.println(((double) sum / (double)iterations) / (double)6);

    }

}
