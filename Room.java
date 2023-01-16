public class Room{

    private int length; 
    private int width;
    private Door leftDoor; 
    private Door rightDoor; 
    private Door aboveDoor; 
    private Door downDoor; 

    Room(int length,int width){
        this.length = length;
        this.width = width; 
    }

    public int getLength(){
        return this.length; 
    }

    public int getWidth(){
        return this.width; 
    }

    public Door getLeftDoor(){
        return this.leftDoor;
    }

    public void setLeftDoor(Door newDoor){
        this.leftDoor = newDoor; 
    }

    public Door getRightDoor(){
        return this.rightDoor;
    }

    public void setRightDoor(Door newDoor){
        this.rightDoor = newDoor; 
    }

    public Door getAboveDoor(){
        return this.aboveDoor;
    }

    public void setAboveDoor(Door newDoor){
        this.aboveDoor = newDoor; 
    }
    
    public Door getDownDoor(){
        return this.downDoor;
    }

    public void setDownDoor(Door newDoor){
        this.downDoor = newDoor; 
    }
}