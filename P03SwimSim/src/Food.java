
public class Food {
	private PApplet processing;
	private int x;
	private int y;
	private PImage foodImage;
	
	public Food(PApplet object) {
		processing = object;
		x = Utility.randomInt(Main.tWidth);  // generate coordinates
		y = Utility.randomInt(Main.tHeight);
		foodImage = processing.loadImage("images" + java.io.File.separator + "FOOD.png");	
	}
	
	public void update() {
		if ( x < 0 )
			x = Main.tWidth -1;   //wraparound for move left
		else
			x = x-1;
		if ( y > Main.tHeight -1 )
			y = 0;				//wraparound for move down
		else
			y = y+1;
		
		
		processing.image(foodImage, x, y);
	}
	
	public float distanceTo(int x, int y) {
		//distance formula
		return (float)Math.sqrt(Math.pow((this.x - x),2) + Math.pow((this.y - y),2));
	}
	
	public void getEaten() {
		x = Utility.randomInt(Main.tWidth);//randomize new food
		y = 0;//start from top
	}
	
}
