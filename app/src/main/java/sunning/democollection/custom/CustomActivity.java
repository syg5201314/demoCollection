package sunning.democollection.custom;

import sunning.democollection.FrameActivity;

/**
 * Created by sunning on 16/2/17.
 */
public class CustomActivity extends FrameActivity {
    @Override
    protected String[] getSecondLevel() {
        return new String[]{
                ".custom.view.ViewActivity"
                ,".custom.viewgroup.ViewGroupActivity"
                ,".custom.coordinate.CoordinateActivity"
                ,".custom.paging.PagingLoadingActivity"
                ,".custom.bezier.BezierActivity"
                ,".custom.health.QQHealthActivity"
                ,".custom.recyclercard.RecyclerCardActivity"
                ,".custom.welcome.WelcomeActivity"
                ,".custom.learn.MeasureActivity"};
    }
}
