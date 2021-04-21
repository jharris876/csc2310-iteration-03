package standalone;

import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.Graph;

public class VizSharkJunior {

    private Graph graph;
    private CSVJunior genJr;
//    private GeneratorJunior genJr;

    public VizSharkJunior(String cmd, String arg) {
        // Set the program to run using javafx GUI system
        System.setProperty("org.graphstream.ui", "javafx");

        // Create a new multigraph
        this.graph = new MultiGraph("g");

        // Create a stylesheet for nicely displaying the graph nodes and edges
        this.graph.setAttribute("ui.stylesheet", "url('file://graph.css')");

        // Configure the graph by identifying the generator
        this.graph.display();

        // Polymorphic call to instantiate one of the two supported generators
        if (cmd.contentEquals("--csv"))
            this.genJr = new CSVJunior(graph, arg);
//        else
//            this.genJr = new TSharkJunior(graph, Integer.parseInt(arg));

        // Begin reading and displaying data on the graph
        this.genJr.begin();
        this.genJr.nextEvents();
        this.genJr.end();
    }

    /**
     * Expects command-line arguments as follows:
     *
     * java -cp %CLASSPATH% standalone.VizSharkJunior {--csv filename | --live interface }
     *
     * where filename is a path to a csv file and interface is the integer for the network interface to monitor
     *
     * @param args
     */
    public static void main(String[] args) {
        if ((args.length < 2) || ((!args[0].contentEquals("--csv")) && (!args[0].contentEquals("--live")))) {
            System.err.println("Usage: java -cp %CLASSPATH% standalone.VizSharkJunior {--csv filename | --live interface }");
        } else {
            VizSharkJunior client = new VizSharkJunior(args[0], args[1]);
        }
    }
}
