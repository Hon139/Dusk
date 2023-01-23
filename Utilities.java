import java.awt.Image;
import javax.swing.ImageIcon;

public class Utilities{
 
    public static ImageIcon scaleImage(ImageIcon image,int newWidth,int newHeight){
        Image newImage = image.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }

    public static int getRandomInt(int lowerBound, int upperBound){
        return (int)((lowerBound)+(Math.random()*(upperBound-lowerBound+1)));
    }

    public static int getWeightedRandom(int[] ticketCounts){
        int totalProbabilityTickets = 0; 
        for (int i =0; i<ticketCounts.length;i++){
            totalProbabilityTickets += ticketCounts[i];
        }

        int randNum = (int)(Math.random()*totalProbabilityTickets);

        for (int i = 0; i< ticketCounts.length ;i++){
            if (randNum <= ticketCounts[i]){
                return i; 
            } else {
                randNum -= ticketCounts[i];
            }
        }
        return -1; // mathamatically impossible, unless array is empty
    }

    public static boolean isInRange(int lowerBound, int upperBound, int input ){

        return ((lowerBound<=input)&&(upperBound>=input)); 


    }


    private Utilities(){}
}