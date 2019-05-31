
public class SimObject {
    // common fields for multiple classes
    protected int x;
    protected int y;
    protected static PApplet object;
    protected PImage image;
    protected boolean remove = false;

    public SimObject(String imagePath) {
        if (object == null)
            throw new IllegalStateException(
                            "SimObject.setProcessing() must be called before constructing any SimObjects.");

        image = object.loadImage(imagePath);
        x = Utility.randomInt(Main.WIDTH); // randomizes x(and y) for each fish
        y = Utility.randomInt(Main.HEIGHT);
    }

    public SimObject(String imagePath, int x, int y) {
        if (object == null)
            throw new IllegalStateException(
                            "SimObject.setProcessing() must be called before constructing any SimObjects.");

        image = object.loadImage(imagePath);
        this.x = x;
        this.y = y;
    }

    // initialize the PApplet reference that is used by all SimObjects
    public static void setProcessing(PApplet processing) {
        object = processing;
    }

    public void update() {}

    /**
     * Calculates a distance between 2 objects,
     * 
     * @param x passes in hook x-coordinates
     * @param y passes in hook y-coordinates
     * @return the distance between the objects
     */
    public float distanceTo(int x, int y) {
        return (float) Math.sqrt(Math.pow((this.x - x), 2) + Math.pow((this.y - y), 2));
    }

    public void tryToInteract(SimObject other) {}

    public boolean shouldBeRemoved() {
        return remove;
    }
}
