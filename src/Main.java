import processing.core.PApplet;

import java.util.ArrayList;

public class Main extends PApplet {
    public static Main app;
    private ArrayList<ListElement> elements;
    final private int numElements = 32;

    private int low;
    private int high;
    private int whereFound;
    private boolean notFound;
    private String targetString;

    // https://processing.github.io/processing-javadocs/core/processing/core/PApplet.html

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    public Main() {
        super();
        app = this;
        elements = new ArrayList<>();
        reset();
    }

    @Override
    public void settings() {
        super.settings();
        size(1000, 500);
    }

    @Override
    public void setup() {
        super.setup();
        int size = width/numElements;
        for (int i = 0; i < numElements; i++){
            int x = (i * size) + (size/2);
            elements.add(new ListElement(i+10, x, height/2, size));
        }
    }

    @Override
    public void draw() {
        background(0);
        for (int i = 0; i < elements.size(); i++){
            elements.get(i).draw();
            text(i, elements.get(i).getX(), elements.get(i).getY() + 30);
        }
        text("SEARCHING FOR " + targetString, width/2, height/2 + 100);
        if (whereFound >= 0){
            text("FOUND AT " + whereFound, width/2, height/2 + 150);
        } else if (notFound){
            text("NOT FOUND.", width/2, height/2 + 150);

        }

        text("PRESS A NUMERIC KEY (0-9) TO ENTER THE TARGET", width/2, height/4);
        text("PRESS THE SPACE KEY TO TRIGGER EACH ITERATION OF THE SEARCH.", width/2, height/4 + 30);
        text ("CLICK THE SCREEN TO RESET THE VISUALIZER.", width/2, height/4 + 60);
    }

    @Override
    public void mouseClicked() {
        super.mouseClicked();
        reset();
    }

    @Override
    public void keyPressed() {
        super.keyPressed();
        if (key == ' ') {
            int targetNum = Integer.parseInt(targetString);
            whereFound = binarySearchWhileLoop(targetNum);
        } else if (Character.isDigit(key)){
            targetString = targetString + key;
        }
    }

    private int binarySearchRecursive(int low, int high, int target){
        if (low > high){
            return -1;
        }
        int mid = (low + high)/2;
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
        } else {
            notFound = true;
        }
        return -1;
    }

    private void reset(){
        low = 0;
        high = numElements-1;
        whereFound = -1;
        notFound = false;
        targetString = "";
        for (ListElement e : elements){
            e.reset();
        }
    }
}