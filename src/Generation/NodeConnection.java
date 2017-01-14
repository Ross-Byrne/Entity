package Generation;


/**
 * Created by Ross Byrne on 14/01/17.
 */
public class NodeConnection {

    private Node selectedNode;
    private Node targetNode;
    private double distance;

    public Node getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(Node selectedNode) {
        this.selectedNode = selectedNode;
    }

    public Node getTargetNode() {
        return targetNode;
    }

    public void setTargetNode(Node targetNode) {
        this.targetNode = targetNode;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

} // class
