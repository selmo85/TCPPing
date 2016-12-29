package TCPPing;

import java.util.TimerTask;

class DisplayResult extends TimerTask {
    private final Result _r;

    public DisplayResult(Result r) {
        _r = r;
    }

    public void run() {
        _r.SetTotalMessageCount();
        _r.DisplayMessage();
        _r.ResetValues();
    }
}
