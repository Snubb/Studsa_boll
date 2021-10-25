package mvc.Shapes;

/**
 * 2D Triangle in screen coordinates
 * Vertices must come in clockwise order
 * Created 2021-03-31
 *
 * @author Magnus Silverdal
 */
public class Triangle extends mvc.Shapes.Shape {
    private mvc.Shapes.Point[] vertices = new mvc.Shapes.Point[3];
    private mvc.Shapes.Line[] edges = new mvc.Shapes.Line[3];

    public Triangle(mvc.Shapes.Point p0, mvc.Shapes.Point p1, mvc.Shapes.Point p2) {
        vertices[0] = p0;
        vertices[1] = p1;
        vertices[2] = p2;
        sortVertices();
        edges[0] = new mvc.Shapes.Line(vertices[0],vertices[1]);
        edges[1] = new mvc.Shapes.Line(vertices[1],vertices[2]);
        edges[2] = new mvc.Shapes.Line(vertices[2],vertices[0]);
    }

    public mvc.Shapes.Line[] getEdges() {
        return edges;
    }
    public mvc.Shapes.Point[] getVertices() {
        return vertices;
    }

    // Find the ordering of vertices for Bresenhamstyle fill...
    public void sortVertices() {
        int leftmost = 0;
        if (vertices[0].getX() > vertices[1].getX()) {
            leftmost = 1;
        }
        if (vertices[leftmost].getX() > vertices[2].getX()) {
            leftmost = 2;
        }
        if (leftmost == 1) {
            mvc.Shapes.Point tmp = vertices[0];
            vertices[0] = vertices[1];
            vertices[1] = vertices[2];
            vertices[2] = tmp;
        }
        if (leftmost == 2) {
            mvc.Shapes.Point tmp = vertices[0];
            vertices[0] = vertices[2];
            vertices[2] = vertices[1];
            vertices[1] = tmp;
        }
    }
}
