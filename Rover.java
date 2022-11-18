import java.util.Hashtable;
import java.util.ArrayList;
/** Creates a rover object */
public class Rover implements Contract{

    /**
     * Defines the default leg size
     */
    int size = 1; 
    /** Number to be added to to label each sample */
    int samplenum = 0;
    /** A hashtable of samples being observed at the moment */
    public Hashtable <String, String> samples;
    /** Array list of samples added to the permanent collection */
    ArrayList <String> officialsamplecollection;
    /** default x geographical location */
    int x = 0;
    /** default y geographical location */
    int y = 0;

    /** Constructor 
     * @param collectioncapacity number of rock samples the rover can hold
    */
    public Rover(int collectioncapacity){
        officialsamplecollection = new ArrayList<String>(collectioncapacity);  
        this.samples = new Hashtable<String, String>();
    }

    /** picks up a rock sample, labels it, and stores it in the temporary samples HashTable 
     * @param item the rock picked up
    */
    public void grab(String item){ 
        this.samplenum+=1;
        item = item + this.samplenum;
        this.samples.put(item, "unprocessed");
        System.out.println("grabbed sample " + item);
    }

    /** drops rock sample by removing it from the temporary samples Hashtable
     * @param item rock sample
     * @return the string "dropped item" 
     */
    public String drop(String item){ 
        this.samples.remove(item);
        return "Dropped item";
    }

    /** Changes the value of the rock sample to "processed"
     * @param item rock sample
     */
    public void examine(String item){
        this.samples.replace(item, "processed");
        System.out.println(item + " has been processed.");
    }

    /** Adds sample to the permanent ArrayList collection of rock samples
     * @param item rock sample
    */
    public void use(String item){ 
        if (this.samples.get(item) == "processed"){
            this.officialsamplecollection.add(item);
            System.out.println(item + " has been added to the permanent sample collection");
        }else{
            throw new RuntimeException("Please process rock sample before adding to collection");
        }
    }

    /** Changes x and y values of rover depending on direction inputted
     * @param direction right, left, up, or down
     * @return true if change in position was carried out
     */
    public boolean walk(String direction){
        if (this.size == 0){
            throw new RuntimeException("Rover is resting. Extend Rover's legs using the Grow function to walk.");
        }
        else{
            if (direction.equals("left")){
                x=-this.size;   
            } else if (direction.equals("right")){
                x=+this.size;
            } else if (direction.equals("up")){
                y+=this.size;
            } else if (direction.equals("down")){
                y-=this.size;
            } else {
                throw new RuntimeException("Invalid direction entered. Please enter left, right, up, or down depending on which way you want Rover to move.");
            }
            System.out.println("Rover new location (" + this.x + ", " + this.y + ")");
            return true;
        } 
    }

    /** Changes x and y values depending on inputted values
     * @param x change in x value
     * @param y change in y value
     * @return true if fly was carried out
     */
    public boolean fly(int x, int y){ 
        if (x >= 30 || x<=-30|| y >= 30 || y<=-30){
            throw new RuntimeException("Rover is not able to fly that distance. Please enter a shorter distance.");
        }else{
            this.x+=x;
            this.y+=y;
            System.out.println("Rover new location (" + this.x + ", " + this.y + ")");
            return true;
        }
    }

    /** Decreases leg length
     * @return new leg length
     */
    public Number shrink(){ 
        if (this.size == 1){
            throw new RuntimeException("Rover is at it's minimum height. If you wish to stop Rover from moving, put it in rest mode.");
        }
        else{
            this.size-=1;
            System.out.println("Rover leg length = " + this.size);
        }
        return this.size;
    }

    /** increases leg length
     * @return new leg length
     */
    public Number grow(){ 
        if (this.size == 3){
            throw new RuntimeException("Rover is at it's max height.");
        }
        else{
            this.size+=1;
            System.out.println("Rover leg length = " + this.size);
        }
        return this.size;
    }

    /** Changes leg length to 0 */
    public void rest(){ 
        this.size = 0;
        System.out.println("Rover leg length = 0");
    }

    /** Changes both x and y back to 0 */
    public void undo(){ 
        this.x = 0;
        this.y = 0;
        System.out.println("Rover new location (0, 0)");
    }

    public static void main(String[] args){
        Rover rover = new Rover(30);
        rover.grab("Red rock");
        rover.grab("Blue rock");
        rover.examine("Blue rock2");
        rover.use("Blue rock2");
        rover.walk("left");
        rover.fly(-1, 4);
        rover.grow();
        rover.grow();
        rover.rest();
        rover.grow();
        rover.undo();
        rover.walk("right");
        rover.grab("Brown rock");
        rover.examine("Brown rock3");
        rover.fly(0, 29);
        rover.rest();
    }
}
