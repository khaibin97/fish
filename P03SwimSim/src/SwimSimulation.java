
public class SwimSimulation {
	private PApplet processing;
	private Fish []fish = new Fish [Main.fishNum];
	private Food []food = new Food [Main.foodNum];
	private Hook hook;
	
	public SwimSimulation(PApplet object) {
		processing = object; //pass the related object's reference
		for (int i = 0; i < fish.length; i++)//create a reference point for each fish object
			fish[i] = new Fish(object);
		for (int i = 0; i < food.length; i++)//same as fish
			food[i] = new Food(object);
		hook = new Hook(object);//only 1 hook
	}
	
	public void update() {
		
		processing.background(0,255,255); //fill tank (empty)
		
		for (int i = 0; i < food.length; i++) {//updates all food
			food[i].update();
		}
		for (int i = 0; i < fish.length; i++) {  //updates all fish
			fish[i].update();
			hook.tryToCatch(fish[i]);//hook tries to catch all fish
			for (int j = 0; j < food.length; j++) {//tries to eat all food
				fish[i].tryToEat(food[j]);
			}
		}
		
		hook.update();//move hook
	}
	
	public void handleClick(int mouseX, int mouseY) {
		hook.handleClick(mouseX, mouseY);
	}
	
}
