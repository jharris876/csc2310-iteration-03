package standalone;

import org.graphstream.algorithm.generator.Generator;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.stream.AttributeSink;
import org.graphstream.stream.ElementSink;
import org.graphstream.stream.Sink;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class GeneratorJunior implements Generator {


    private Graph graph;
    private Scanner scan;

    public GeneratorJunior(Graph graph) {
        this.graph = graph;
    }

    @Override
    public abstract void begin();


    @Override
    public boolean nextEvents() {
        Graph graph = this.graph;
        boolean result = false;
        while (scan.hasNext()) {
            TSharkData data = new TSharkData(scan.nextLine());
            String toNode = data.getDstIP();
            String fromNode = data.getSrcIP();
            String edge = fromNode + ":" + toNode;
            if (!this.isTabu(fromNode) && !this.isTabu(toNode)) {
                Edge e = graph.getEdge(edge);
                if (e == null) {
                    Node n0 = graph.getNode(fromNode);
                    if (n0 == null) {
                        n0 = this.graph.addNode(fromNode);
                        n0.setAttribute("label", fromNode);
                    }
                    Node n1 = graph.getNode(toNode);
                    if (n1 == null) {
                        n1 = this.graph.addNode(toNode);
                        n1.setAttribute("label", toNode);
                    }
                    e = this.graph.addEdge(edge, n0, n1, true);
                    e.setAttribute("label", 1);
                } else {
                    // Any time an edge has been found, count it as a message
                    // Increase the weight by 1 when observed
                    Integer weight = (Integer) e.getAttribute("label");
                    e.setAttribute("label", weight + 1);
                }
                // At least one node was read, so set the method result to true;
                result = true;
            }
        }
        return result;
    }

    public void setScan(Scanner scan) {
        this.scan = scan;
    }


    public boolean isTabu(String id){
        return (id.contentEquals("") || id.contentEquals("ip.dst") || id.contentEquals("ip.src") || id.contentEquals("255.255.255.255") || id.contentEquals("0.0.0.0"));
    }


    @Override
    public void end() {
        scan.close();
    }

    @Override
    public void addSink(Sink sink) {
        this.graph = (Graph) sink;
    }

    @Override
    public void removeSink(Sink sink) {
        this.graph = null;
    }

    @Override
    public void addAttributeSink(AttributeSink attributeSink) {
        this.graph = (Graph) attributeSink;
    }

    @Override
    public void removeAttributeSink(AttributeSink attributeSink) {
        this.graph = null;
    }

    @Override
    public void addElementSink(ElementSink elementSink) {
        this.graph = (Graph) elementSink;
    }

    @Override
    public void removeElementSink(ElementSink elementSink) {
        this.graph = null;
    }

    @Override
    public void clearElementSinks() {
        this.graph = null;
    }

    @Override
    public void clearAttributeSinks() {
        this.graph = null;
    }

    @Override
    public void clearSinks() {
        this.graph = null;
    }

}
