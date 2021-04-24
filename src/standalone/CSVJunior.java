package standalone;

import org.graphstream.algorithm.generator.Generator;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.stream.AttributeSink;
import org.graphstream.stream.ElementSink;
import org.graphstream.stream.Sink;
import standalone.TSharkData;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSVJunior extends GeneratorJunior {

    private String filename;

    public CSVJunior(Graph graph, String fn) {
        super(graph);
        this.filename = fn;
    }

    public void begin() {
        File file = new File(filename);
        try {
            this.setScan(new Scanner(file));
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
        }
    }
}
