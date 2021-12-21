package jijumbeck.directed_acyclic_graph.points;

import jijumbeck.directed_acyclic_graph.points.Origin;

/*
Space is area where all Origins and Points are located.
worldCoordinateSystem - coordinates of start point.
rootOfGraph - first node of graph.
 */
public class Space {
    Coord2D worldCoordinateSystem;
    final Point rootOfGraph;

    public Space(double x, double y, Point rootOfGraph){
        worldCoordinateSystem = new Coord2D(x, y);
        this.rootOfGraph = rootOfGraph;
    }

    public Point getRootOfGraph(){
        return rootOfGraph;
    }

    public Coord2D getWorldCoordinateSystem() {
        return worldCoordinateSystem;
    }

    public void setWorldCoordinateSystem(Coord2D newValue) {
        double offsetXWCS = newValue.getX() - worldCoordinateSystem.getX();
        double offsetYWCS = newValue.getY() - worldCoordinateSystem.getY();

        this.worldCoordinateSystem = newValue;
        rootOfGraph.setPosition(new Coord2D(rootOfGraph.position.getX() - offsetXWCS,
                rootOfGraph.position.getY() - offsetYWCS));
    }

    public BoundBox getBoundBox() {
        return rootOfGraph.getOffsetBoundBox();
    }
}
