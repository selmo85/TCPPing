package TCPPing;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.TimerTask;

class SendMessage extends TimerTask {
    private final Result r;
    private final int port;
    private final int size;
    private final String hostname;
    private final int counter;

    public SendMessage(int port, int size, String hostname, int counter, Result r) {
        this.port = port;
        this.size = size;
        this.hostname = hostname;
        this.counter = counter;
        this.r = r;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(hostname, port);
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());

            // set data needed for calculations
            Message msg = new Message();
            msg.SetId(counter);
            msg.SetPitcherStartTime();
            msg.SetAdditionalData(size, SizeHelper.SizeOfObject(msg));
            r.AddNewSentMsg(counter);
            os.writeObject(msg);

            InputStream in = socket.getInputStream();
            ObjectInputStream is = new ObjectInputStream(in);
            Message receivedMsg = (Message) is.readObject();
            socket.close();

            // calculate new values
            receivedMsg.SetPitcherEndTime();
            r.AddNewReceivedMsg(counter);
            r.CalculateNewValues(receivedMsg.GetTotalTime(), receivedMsg.GetReceiveTime(), receivedMsg.GetSendTime());
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
