package mvc.Shapes;

/**
 * This is a class
 * Created 2021-10-14
 *
 * @author Magnus Silverdal
 */
public class Circle extends mvc.Shapes.Shape {
    private mvc.Shapes.Point p1;
    private int radius;
    private int color = 0xFFFFFF;

    public Circle(mvc.Shapes.Point p1, int r) {
        this.p1 = p1;
        this.radius = r;
    }

    public Circle(mvc.Shapes.Point p1, int r, int color) {
        this.p1 = p1;
        this.radius = r;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public mvc.Shapes.Point getCenter() {
        return p1;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int r) {
        this.radius = r;
    }

}
