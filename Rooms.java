public class Rooms{    
    private Door rootAirLock; 

    
    private Room longRoom = new Room(4,8);
    private Room smallRoom = new Room();
    private Room bigRoom = new Room();
    private Room wideROom = new Room(); 




    Rooms(int size){
        this.rootAirLock = new Door(true);
        generate();
    }

    private void generate(){

    }






    private int getRandomInt(int upperBound, int lowerBound){
        return (int)(lowerBound+Math.random()*upperBound);
    }
}