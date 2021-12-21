package jijumbeck.directed_acyclic_graph.points;

/*
Point is a physical point in space with coordinate Coord2D position.
 */
public class Point {
    Coord2D position;

    public Point(double x, double y){
        position = new Coord2D(x, y);
    }

    public Coord2D getPosition(){
        return this.position;
    }

    public void setPosition(Coord2D newValue){
        position = newValue;
    }

    /*
    Returns original bound box.
     */
    public BoundBox getBoundBox() {
        return new BoundBox(position, position);
    }

    /*
    Returns offset bound box, but for point it is original.
     */
    public BoundBox getOffsetBoundBox() {
        return getBoundBox();
    }
}
