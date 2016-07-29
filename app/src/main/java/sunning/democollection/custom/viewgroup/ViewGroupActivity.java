package sunning.democollection.custom.viewgroup;

import sunning.democollection.FrameActivity;

/**
 * Created by sunning on 16/2/17.
 */
public class ViewGroupActivity extends FrameActivity{
    @Override
    protected String[] getSecondLevel() {
        return new String[]{".custom.viewgroup.first.MyViewGroupActivity",
        ".custom.viewgroup.flowlayout.FlowActivity"};
    }
}
