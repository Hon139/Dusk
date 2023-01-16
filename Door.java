public class Door{
    private boolean isAirLock; 
    private boolean isOpen;
    private int squareSideLength; 
    private Room connectingRoom; 

    Door(boolean isAirLock){
        this.isAirLock = isAirLock; 
        this.isOpen = false; 
        this.squareSideLength = 2; 
    }

    public int getLength(){
        return this.squareSideLength; 
    }

    public boolean getIsAirLock(){
        return this.isAirLock;
    }

    public boolean isOpen(){
        return this.isOpen;
    }

    public void toggleOpen(){
        this.isOpen = !(this.isOpen);
    }

    public Room getConnectingRoom(){
        return this.connectingRoom;
    }

    public void setConnectingRoom(Room newRoom){
        this.connectingRoom = newRoom; 
    }
}