package jijumbeck.directed_acyclic_graph.points;

import java.util.Objects;

/*
Class of coordinate of two-dimension space (x, y).
 */
public class Coord2D {
    private final double x;
    private final double y;

    public Coord2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    @Override
    public boolean equals(Object o){
        if(o == null){
            return false;
        }

        if (this == o) {
            return true;
        }

        if (getClass() != o.getClass()) {
            return false;
        }

        Coord2D anotherCoord2D = (Coord2D)o;
        return (this.x == anotherCoord2D.x) && (this.y == anotherCoord2D.y);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.x, this.y);
    }
}
