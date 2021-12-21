package jijumbeck.directed_acyclic_graph.exceptions;

/*
Особый вид исключения, когда в DAG появляется цикл.
 */
public class DAGConstraintException extends Exception {
    public DAGConstraintException(){
        super();
    }

    public DAGConstraintException(String message) {
        super(message);
    }
}
