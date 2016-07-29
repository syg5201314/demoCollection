package sunning.democollection.learn._0316;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import sunning.democollection.BaseActivity;
import sunning.democollection.R;

/**
 * Created by sunning on 16/3/16.
 */
public class ViewDragHelperActivity extends BaseActivity {

    private ViewDrag mView;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.view_drag_layout);
        mView = (ViewDrag) findViewById(R.id.layout_id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_vertical_drawer_layout, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mView.isDrawerOpened()) {
            mView.closeDrawer();
        } else {
            mView.openDrawer();
        }
        return super.onOptionsItemSelected(item);
    }
}
