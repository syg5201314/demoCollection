package sunning.democollection.ipc.aidl.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import sunning.democollection.ipc.aidl.ICompute;

/**
 * Created by sunning on 2016/10/27.
 */

public class IComputeService extends Service {

    private IBinder mICompute = new ICompute.Stub() {
        @Override
        public int add(int a, int b) throws RemoteException {
            return a + b;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mICompute;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
