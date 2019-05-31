
public class Hook {
	private PApplet processing;
	private int x;
	private int y;
	private PImage hookImage;
	
	public Hook(PApplet object) {
		processing = object;
		x = Utility.randomInt(Main.tWidth);    //generate hook position
		y = Utility.randomInt(Main.tHeight);
		hookImage = processing.loadImage("images" + java.io.File.separator + "HOOK.png");	
	}
	
	public void update() {
		if ( y < 0)
			y = Main.tHeight -1;      //wraparound for move up
		else
			y = y -((Main.tHeight+50-y)/50);// formula for hook speed
		
		processing.image(hookImage, x, y);
		processing.line(x+4, 0, x+4, y-12); // draws hook line
	}
	
	public void handleClick(int mouseX, int mouseY) {
		x = mouseX;		//reinitializes position based on mouse click
		y = mouseY;
	}
	
	public void tryToCatch(Fish fish) {
		if (fish.distanceTo(x,y) <= 40) 
			fish.getCaught();
	}
}
