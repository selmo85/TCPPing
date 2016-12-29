package TCPPing;

/*
  Contains data needed for calculations (pitcher/cather start time, id...)
*/

import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

class Message implements Serializable {

    private Integer _id;
    private long _pitcherStartTime;
    private long _pitcherEndTime;
    private long _catcherStartTime;
    private byte[] _additionalData;

    public void SetId(Integer id) {
        this._id = id;
    }

    public void SetPitcherStartTime() {
        this._pitcherStartTime = System.nanoTime();
    }

    public void SetPitcherEndTime() {
        this._pitcherEndTime = System.nanoTime();
    }

    public void SetCatcherStartTim() {
        this._catcherStartTime = System.nanoTime();
    }

    // calculate time needed to send and receive info
    public float GetSendTime() {
        return (this._catcherStartTime - this._pitcherStartTime) / 1000000.0f;
    }

    public float GetReceiveTime() {
        return (this._pitcherEndTime - this._catcherStartTime) / 1000000.0f;
    }

    public float GetTotalTime() {
        return (this._pitcherEndTime - this._pitcherStartTime) / 1000000.0f;
    }

    public void SetAdditionalData(int size, int usedSize) throws IOException {
        int counter = 10;
        while(SizeHelper.SizeOfObject(_additionalData) < size - usedSize)
        {
            _additionalData = new byte[counter++];
            new Random().nextBytes(_additionalData);
        }
    }
    public void ResetAdditionalData() {_additionalData = new byte[0];}



}
