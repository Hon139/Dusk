import java.util.Arrays;
public class Rooms{    

    float[][] floorPlan;

    Rooms(){
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

        while (nonDefaultTiles > 0){
            int rand = Utilities.getWeightedRandom(new int[]{2,1}); // [0] = long room, [1] = squared room
            int randY = Utilities.getRandomInt(0, floorPlan.length-1);
            int randX = Utilities.getRandomInt(0, floorPlan[randY].length-1);

            if(rand == 0){
                int randDirection = Utilities.getRandomInt(0, 3); // 0 = North, 1 = East, 2 = South, 3 = West
                int[] newCoords = getNewCoords(randDirection,new int[]{randX,randY});
                int newX = newCoords[0];
                int newY = newCoords[1];

                if (verifyCoords(floorPlan,newCoords) &&
                floorPlan[randY][randX]%1 ==0 && floorPlan[newY][newX]%1 == 0
                ){
                    floorPlan[newY][newX] = floorPlan[randY][randX];
                    assignTileDecimalRect(new int[]{newX,newY},new int[]{randX,randY},randDirection);

                    nonDefaultTiles -= 2;
                }
            } 
            // else if (rand == 1){
            //     int randDirectionHorizontal = Utilities.getRandomInt(0, 3); // 0 = North, 1 = East, 2 = South, 3 = West
            //     int randDirectionVertical = Utilities.getWeightedRandom(new int[]{1,0,1}); // 0 = North, 1 = East, 2 = South, 3 = West
                
            //     int[] newCoordVertical = getNewCoords(randDirectionVertical,new int[]{randX,randY});
            //     int[] newCoordHorizontal = getNewCoords(randDirectionHorizontal,new int[]{randX,randY});
            //     int[] newCoordHorizontal2 = getNewCoords(randDirectionHorizontal,new int[]{newCoordVertical[0],newCoordVertical[1]});

            //     if (verifyCoords(floorPlan, newCoordHorizontal2) && verifyCoords(floorPlan, newCoordHorizontal) && verifyCoords(floorPlan, newCoordVertical) && floorPlan[randY][randX]%1 ==0
            //      &&floorPlan[newCoordVertical[1]][newCoordVertical[0]]%1==0 && floorPlan[newCoordHorizontal[1]][newCoordHorizontal[0]]%1==0 && floorPlan[newCoordHorizontal2[1]][newCoordHorizontal2[0]]%1==0){

            //         floorPlan[][]
            //     }
            // }
        }
            String lineSeparator = System.lineSeparator();
            StringBuilder sb = new StringBuilder();

            for (float[] row : floorPlan) {
                sb.append(Arrays.toString(row))
                .append(lineSeparator);
            }
            System.out.println(sb.toString());
    }

    private int[] getNewCoords(int direction, int[] oldCoords){
        int newX = (int)(oldCoords[0]+(direction%2)*(Math.pow(-1,(direction-1)/2)));
        int newY = (int)(oldCoords[1] +((direction+1)%2)*(Math.pow(-1,(direction/2)+1)));
        return new int[]{newX,newY};
    }

    private boolean verifyCoords(float[][] map,int[] coords){
        int newX = coords[0];
        int newY = coords[1];
        return (Utilities.isInRange(0,map.length -1,newY) 
        && Utilities.isInRange(0, map[newY].length-1,newX)
        );
    } 

    // private void assignTileDecimalSquare(int[] coord1,int[] coord2,int[] coordH1,int[] coordH2){
    //     /* 0.7  0.8
    //      * 0.6  0.5
    //      */
        
    //     int[][] elements = {coord1,coord2,coordH1,coordH2};
        
    //     int largestX = coord1[0]; 
    //     for (int i =0;i < elements.length;i++){
    //         if (elements[i][0] > largestX){
    //             largestX = elements[i][0];
    //         }
    //     }
    // }

    private void assignTileDecimalRect(int[] coord1,int[] coord2, int direction){
        if (direction == 1 || direction == 3){
            //horizontal 0.1 - 0.2
            if (coord1[0] > coord2[0]){
                floorPlan[coord1[1]][coord1[0]] +=0.2;
                floorPlan[coord2[1]][coord2[0]] +=0.1;
            } else {
                floorPlan[coord1[1]][coord1[0]] +=0.1;
                floorPlan[coord2[1]][coord2[0]] +=0.2;
            }
        } else {
            // Vertical 
            // 0.3 <>
            // 0.4 <>
            if (coord1[1] > coord2[1]){
                floorPlan[coord1[1]][coord1[0]] +=0.4;
                floorPlan[coord2[1]][coord2[0]] +=0.3;
            } else {
                floorPlan[coord1[1]][coord1[0]] +=0.3;
                floorPlan[coord2[1]][coord2[0]] +=0.4;
            }
        }

    }

}