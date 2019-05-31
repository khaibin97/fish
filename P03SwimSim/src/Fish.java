
public class Fish {
	private PApplet processing;
	private int x;
	private int y;
	private PImage fishImage;
	
	public Fish(PApplet object) {
		processing = object;
		x = Utility.randomInt(Main.tWidth); 	//randomizes x(and y) for each fish
		y = Utility.randomInt(Main.tHeight);
		fishImage = processing.loadImage("images" + java.io.File.separator + "FISH.png");		
	}
	
	public void update() {
		if ( x > Main.tWidth -1)
			x = 0;		//wraparound
		else
			x = x+1;	//movement
		
		processing.image(fishImage, x, y);
	}
	
	public void tryToEat(Food food) {
		if (food.distanceTo(x,y) <= 40) 
			food.getEaten();
	}
	
	public float distanceTo(int x, int y) {
		return (float)Math.sqrt(Math.pow((this.x - x),2) + Math.pow((this.y - y),2));
	}
	
	public void getCaught() {
		x = 0;				//reinitializes the fish to the left with 
		y = Utility.randomInt(Main.tHeight);//  randomized y-coordinates
	} 
	
}
