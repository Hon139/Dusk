public class testEn {
    public static void main(String[] args) {

        int randX = 1;
        int randY = 2;
        int randDirection = Utilities.getRandomInt(0, 3);


        double t = randX+(randDirection%2)*(Math.pow(-1,(randDirection-1)/2));
        double p = randY +((randDirection+1)%2)*(Math.pow(-1,(randDirection/2)+1));

        System.out.println("X: "+randX);
        System.out.println("Y: "+randY+"\n\n");

        System.out.println("Rand: "+randDirection);
        System.out.println("X: "+t);
        System.out.println("Y: "+p);


    }
}
 