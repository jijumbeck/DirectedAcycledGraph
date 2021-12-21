package jijumbeck.directed_acyclic_graph.points;

/*
Bound box is a limiting rectangle where all Points can fit.
 */
public class BoundBox {
    /*
    Flag for Origin without children.
     */
    public boolean isDefaultForEmptyOrigin;
    public Coord2D minimalPoint;
    public Coord2D maximumPoint;

    public BoundBox(Coord2D minimalPoint, Coord2D maximumPoint){
        if((minimalPoint.getX() > maximumPoint.getX()) || (minimalPoint.getY() > maximumPoint.getY())){
            throw new IllegalArgumentException();
        }

        this.minimalPoint = minimalPoint;
        this.maximumPoint = maximumPoint;
    }

    /*
    Returns offset bound box.
     */
    void offsetBoundBox(double offsetX, double offsetY) {
        if(isDefaultForEmptyOrigin) {
            throw new IllegalCallerException("This boundBox is empty");
        }
        minimalPoint = new Coord2D(minimalPoint.getX() + offsetX, minimalPoint.getY() + offsetY);
        maximumPoint = new Coord2D(maximumPoint.getX() + offsetX, maximumPoint.getY() + offsetY);
    }

    /*
    This bound box merges with another one.
     */
    public void merge(BoundBox anotherBoundBox){
        if(anotherBoundBox.isDefaultForEmptyOrigin) {
            return;
        }

        if(this.isDefaultForEmptyOrigin){
            minimalPoint = anotherBoundBox.minimalPoint;
            maximumPoint = anotherBoundBox.maximumPoint;
            this.isDefaultForEmptyOrigin = false;
            return;
        }

        double minX = Math.min(minimalPoint.getX(), anotherBoundBox.minimalPoint.getX());
        double minY = Math.min(minimalPoint.getY(), anotherBoundBox.minimalPoint.getY());
        double maxX = Math.max(maximumPoint.getX(), anotherBoundBox.maximumPoint.getX());
        double maxY = Math.max(maximumPoint.getY(), anotherBoundBox.maximumPoint.getY());

        minimalPoint = new Coord2D(minX, minY);
        maximumPoint = new Coord2D(maxX, maxY);
    }

    /*
    Returns default bound box.
     */
    static BoundBox defaultBoundBox(){
        BoundBox defaultBoundBox = new BoundBox(new Coord2D(0,0), new Coord2D(0, 0));
        defaultBoundBox.isDefaultForEmptyOrigin = true;
        return defaultBoundBox;
    }
}
