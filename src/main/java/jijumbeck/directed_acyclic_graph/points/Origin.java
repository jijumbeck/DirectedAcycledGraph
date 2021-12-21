package jijumbeck.directed_acyclic_graph.points;

import jijumbeck.directed_acyclic_graph.exceptions.DAGConstraintException;

import java.util.HashSet;
import java.util.Set;

/*
Origin is a system of coordinates, offset to position coordinates.
 */
public class Origin extends Point {
    Set<Point> children;

    public Origin(double x, double y) {
        super(x, y);
        children = new HashSet<>();
    }

    public Set<Point> getChildren(){
        return new HashSet<Point>(children);
    }

    public void setChildren(Set<Point> newValue) throws DAGConstraintException {
        Set<Point> temporaryChildren = children;

        try{
            children = newValue;
            Set<Origin> originSet = new HashSet<>();
            originSet.add(this);
            traverseChildren(originSet);
        } catch (DAGConstraintException ex) {
            children = temporaryChildren;
            throw ex;
        }
    }

    /*
    Returns original bound box. It is merging of all Points in children.
     */
    @Override
    public BoundBox getBoundBox(){
        BoundBox originBoundBox = BoundBox.defaultBoundBox();
        for (Point child : children){
            originBoundBox.merge(child.getOffsetBoundBox());
        }
        return originBoundBox;
    }

    /*
    Returns offset bound box. It is original bound box, is offset by position.
     */
    @Override
    public BoundBox getOffsetBoundBox(){
        BoundBox boundBox = getBoundBox();
        if(boundBox.isDefaultForEmptyOrigin){
            return boundBox;
        }
        boundBox.maximumPoint = new Coord2D(boundBox.maximumPoint.getX() + position.getX(),
                boundBox.maximumPoint.getY() + position.getY());
        boundBox.minimalPoint = new Coord2D(boundBox.minimalPoint.getX() + position.getX(),
                boundBox.minimalPoint.getY() + position.getY());
        return boundBox;
    }

    /*
    Checks if Origin is acyclic. Throws DAGConstraintException, if cycle was found.
     */
    private void traverseChildren(Set<Origin> setOfPassedOrigins) throws DAGConstraintException {
        for (Point child : children) {
            if(child.getClass() == Point.class) {
                continue;
            }

            if(setOfPassedOrigins.contains((Origin)child)) {
                throw new DAGConstraintException("Cycle was found.");
            }

            setOfPassedOrigins.add((Origin)child);
            ((Origin)child).traverseChildren(setOfPassedOrigins);
            setOfPassedOrigins.remove(child);
        }
    }
}
