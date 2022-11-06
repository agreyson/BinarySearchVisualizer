public class ListElement {
    private int id;
    private int x;
    private int y;
    private int size;
    private int fill;

    public ListElement(int id, int x, int y, int size){
        this.id = id;
        this.x = x;
        this.y = y;
        this.size = size;
        this.fill = 255;
    }

    public void draw(){
        Main.app.fill(this.fill, 0, 0);
        Main.app.ellipse(x, y, size, size);
        Main.app.textAlign(Main.app.CENTER, Main.app.CENTER);
        Main.app.fill(255);
        Main.app.text(id, x, y);
    }

    public int getID(){
        return id;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void wasCompared(){
        fill = 0;
    }

    public void reset(){
        this.fill = 255;
    }
}