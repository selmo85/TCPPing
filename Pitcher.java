package TCPPing;

import java.util.Timer;

class Pitcher {
    private final Result r;
    private final int port;
    private final int mps;
    private final int size;
    private final String hostname;
    private int counter;

    public Pitcher(int port, int mps, int size, String hostname) {

        this.port = port;
        this.mps = mps;
        this.size = size;
        this.hostname = hostname;
        this.counter = 0;
        r = new Result();
    }

    public void startPitcher() {
        Timer timer = new Timer();
        // send new message
        timer.schedule(new SendMessage(port, size, hostname, counter, r), 0, 1000 / mps);
        // display result every sec
        timer.schedule(new DisplayResult(r), 0, 1000);
        counter++;
    }

}
