import processing.core.PApplet;

import java.util.ArrayList;

public class Main extends PApplet {
    public static Main app;
    private ArrayList<ListElement> elements;
    final private int numElements = 32;

    private int low;
    private int high;
    private int mid;
    private boolean found;
    private int whereFound;

    // https://processing.github.io/processing-javadocs/core/processing/core/PApplet.html

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    public Main() {
        super();
        app = this;
        low = 0;
        high = numElements;
        found = false;
        whereFound = -1;
    }

    @Override
    public void settings() {
        super.settings();
        size(1000, 500);
    }

    @Override
    public void setup() {
        super.setup();
        elements = new ArrayList<ListElement>();
        int size = width/numElements;
        for (int i = 0; i < numElements; i++){
            int x = (i * size) + (size/2);
            elements.add(new ListElement(i*10, x, height/2, size));
        }
    }

    @Override
    public void draw() {
        background(0);
        for (ListElement listElement : elements){
            listElement.draw();
        }
        if (found){
            text(whereFound, width/2, height/2 + 150);
        }
    }

    @Override
    public void mouseClicked() {
        super.mouseClicked();
    }

    @Override
    public void keyPressed() {
        super.keyPressed();
        int target = 120;
        whereFound = binarySearchWhileLoop(target);
        if (whereFound != -1){
            found = true;
        }
    }

    private int binarySearchRecursive(int low, int high, int target){
        int mid = (low + high)/2;

        if (low > high){
            return -1;
        }
        elements.get(mid).wasCompared();
        if (target > elements.get(mid).getID()){
            return binarySearchRecursive(mid+1, high, target);
        } else if (target < elements.get(mid).getID()){
            return binarySearchRecursive(low, mid-1, target);
        } else { // equals target
            return mid;
        }
    }

    private int binarySearchWhileLoop(int target){
        if (low <= high){
            int mid = (low + high)/2;
            elements.get(mid).wasCompared();
            if (target > elements.get(mid).getID()){
                low = mid + 1;
            } else if (target < elements.get(mid).getID()){
                high = mid - 1;
            } else {// found target
                return mid;
            }
        }
        return -1;
    }
}