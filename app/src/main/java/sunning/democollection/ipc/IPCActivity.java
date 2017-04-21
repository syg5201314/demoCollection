package sunning.democollection.ipc;

import sunning.democollection.FrameActivity;

/**
 * Created by sunning on 2016/10/27.
 */

public class IPCActivity extends FrameActivity {
    @Override
    protected String[] getSecondLevel() {
        return new String[]{".ipc.aidl.AIDLActivity"};
    }
}
