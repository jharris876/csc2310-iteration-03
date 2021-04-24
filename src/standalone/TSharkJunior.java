package standalone;

//import com.sun.tools.javac.jvm.Gen;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class TSharkJunior extends GeneratorJunior {

    private int networkInterface;

    public TSharkJunior(Graph graph, int arg) {

        super(graph);
        this.networkInterface = arg;
}


    public void begin(){
        int netInterface = 3;
        String cmd = "tshark -T fields -e frame.number -e frame.time_relative -e _ws.col.Protocol -e ip.proto -e ip.src -e ip.dst -e tcp.srcport -e tcp.dstport -e udp.srcport -e udp.dstport -E header=n -E separator=, -E quote=d -E occurrence=f -i " + netInterface;

        try {
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()), 512);
            this.setScan(new Scanner(output));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}