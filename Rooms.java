import java.util.Arrays;

import javax.rmi.CORBA.Util; 
public class Rooms{    

    
    float[][] floorPlan;


    Rooms(int size){
        generateMap(10,10,.5);
    }

    private void generateMap(int x,int y,double nonSingleRoomPercent){
        floorPlan = new float[x][y];
        int totalTiles = x*y; 
        int nonDefaultTiles = (int)(totalTiles*nonSingleRoomPercent); 

        int counter = 1;
        for (int i = 0; i < floorPlan.length;i++){
            for (int u = 0; u < floorPlan[i].length;u++){
                floorPlan[i][u] = counter;
                counter++;
            }
        }

        int nonDefaultTilesLong = (int)(nonDefaultTiles*0.66); 
        int nonDefaultTilesLargeSquare = nonDefaultTiles - nonDefaultTilesLong; 
        while ((nonDefaultTilesLargeSquare+nonDefaultTilesLong) > 0){

            int rand = Utilities.getWeightedRandom(new int[]{2,1}); // [0] = long room, [1] = squared room
            int randX = Utilities.getRandomInt(0, floorPlan.length);
            int randY = Utilities.getRandomInt(0, floorPlan[randX].length);
            int[] initialPoint = new int[]{randX,randY}; 

            if(rand == 0){
                int randDirection = Utilities.getRandomInt(0, 3); // 0 = North, 1 = East, 2 = South, 3 = West
                int t = (int)(randX+(randDirection%2)*(Math.pow(-1,(randDirection-1)/2)));
                int p = (int)(randY +((randDirection+1)%2)*(Math.pow(-1,(randDirection/2)+1)));
                
                if (Utilities.isInRange(0, , p)){

                }

            } else if (rand ==1){

            }

        }



            String lineSeparator = System.lineSeparator();
            StringBuilder sb = new StringBuilder();

            for (float[] row : floorPlan) {
                sb.append(Arrays.toString(row))
                .append(lineSeparator);
            }

            System.out.println(sb.toString());

    }





}