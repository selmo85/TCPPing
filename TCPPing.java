package TCPPing;

import java.io.IOException;

//java TCPPing -c -bind 192.168.0.20 -port 9900
//java TCPPing –p –port 9900 –mps 30 –size 1000 T440s
class TCPPing {
    public static void main(String[] args) throws IOException {
      /*  switch (args[0]) {
            case "-c":
                StartCatcher(args);
                break;
            case "-p":
                StartPitcher(args);
                break;
            default:
                System.out.println("Invalid command arguments!");
                break;
        }*/

        Catcher c = new Catcher();
        c.startCatcher(9900, "192.168.1.2");

        Pitcher p = new Pitcher(9900, 1000, 500, "DESKTOP-H0D8824");
        p.startPitcher();
    }

    private static void StartCatcher(String[] args) {
        int port = 0;
        String ipAddress = "";

        for (int i = 1; i < args.length; i += 2) {
            switch (args[i]) {
                case "-bind":
                    ipAddress = args[i + 1];
                    break;
                case "-port":
                    port = Integer.valueOf(args[i + 1]);
                    break;
                default:
                    System.out.println("Invalid command arguments!");
                    break;
            }
        }
        Catcher c = new Catcher();
        c.startCatcher(port, ipAddress);
    }

    private static void StartPitcher(String[] args) {
        int port = 0;
        int size = 300;
        int mps = 0;
        String hostname = "";


        for (int i = 1; i < args.length; i += 2) {
            switch (args[i]) {
                case "-mps":
                    mps = Integer.valueOf(args[i + 1]);
                    break;
                case "-port":
                    port = Integer.valueOf(args[i + 1]);
                    break;
                case "-size":
                    size = Integer.valueOf(args[i + 1]);
                    break;
                default:
                    hostname = args[i];
                    i--;
                    break;
            }
        }
        Pitcher p = new Pitcher(port, mps, size, hostname);
        p.startPitcher();
    }
}