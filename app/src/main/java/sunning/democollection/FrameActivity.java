package sunning.democollection;

import android.os.Bundle;
import android.widget.ListView;

/**
 * @author sunning
 * @date 2015-11-16
 */
public abstract class FrameActivity extends BaseActivity{
	private ListView list ;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_main) ;
		findView() ;
		setListAdapter() ;
		setListener(list) ;
	}
	

	private void setListAdapter() {
		try {
			list.setAdapter(getAdapter(getSecondLevel(),0)) ;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected abstract String[] getSecondLevel();

	private void findView() {
		list = (ListView) findViewById(R.id.main_listview) ;
	}
}
 