package standalone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Sample {

    public static void main(String args[]){

        int netInterface = 1;
        String cmd = "tshark -T fields -e frame.number -e frame.time_relative -e _ws.col.Protocol -e ip.proto -e ip.src -e ip.dst -e tcp.srcport -e tcp.dstport -e udp.srcport -e udp.dstport -E header=n -E separator=, -E quote=d -E occurrence=f -i " + netInterface;

        try {
/**
 * Opening a file
 */
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()), 512);
/** creating the scanner
 *
 */
            Scanner scan = new Scanner(output);

            /// Reads the data
            while (scan.hasNextLine()){
                System.out.println(scan.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
