package sunning.democollection;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sunning
 * @date 2015-11-16
 */
public class MainActivity extends BaseActivity {
	private ListView mainListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findView();
		Intent intent = getIntent();
		String path = intent.getStringExtra("com.example.android.apis.Path");

		if (path == null) {
			path = "";
		}
		mainListView.setAdapter(initAdapter(path));
		setListener();
	}

	private void setListener() {
		mainListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(position);
				Intent intent = (Intent) map.get("intent");
				startActivity(intent);
			}
		});
	}

	private SimpleAdapter initAdapter(String path) {
		return new SimpleAdapter(this, getData(path), android.R.layout.simple_list_item_1, new String[] { "title" }, new int[] { android.R.id.text1 });
	}

	protected List<Map<String, Object>> getData(String prefix) {
		List<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();

		Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory("com.demo.example");

		PackageManager pm = getPackageManager();
		List<ResolveInfo> list = pm.queryIntentActivities(mainIntent, 0);

		if (null == list)
			return myData;

		String[] prefixPath;
		String prefixWithSlash = prefix;

		if (prefix.equals("")) {
			prefixPath = null;
		} else {
			prefixPath = prefix.split("/");
			prefixWithSlash = prefix + "/";
		}

		int len = list.size();

		Map<String, Boolean> entries = new HashMap<String, Boolean>();

		for (int i = 0; i < len; i++) {
			ResolveInfo info = list.get(i);
			CharSequence labelSeq = info.loadLabel(pm);
			String label = labelSeq != null ? labelSeq.toString() : info.activityInfo.name;

			if (prefixWithSlash.length() == 0 || label.startsWith(prefixWithSlash)) {

				String[] labelPath = label.split("/");

				String nextLabel = prefixPath == null ? labelPath[0] : labelPath[prefixPath.length];

				if ((prefixPath != null ? prefixPath.length : 0) == labelPath.length - 1) {
					addItem(myData, nextLabel, activityIntent(info.activityInfo.applicationInfo.packageName, info.activityInfo.name));
				} else {
					if (entries.get(nextLabel) == null) {
						addItem(myData, nextLabel, browseIntent(prefix.equals("") ? nextLabel : prefix + "/" + nextLabel));
						entries.put(nextLabel, true);
					}
				}
			}
		}

		Collections.sort(myData, sDisplayNameComparator);

		return myData;
	}

	private void findView() {
		mainListView = (ListView) findViewById(R.id.main_listview);

	}

	protected Intent activityIntent(String pkg, String componentName) {
		Intent result = new Intent();
		result.setClassName(pkg, componentName);
		return result;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	protected Intent browseIntent(String path) {
		Intent result = new Intent();
		result.setClass(this, MainActivity.class);
		result.putExtra("com.example.android.apis.Path", path);
		return result;
	}

	protected void addItem(List<Map<String, Object>> data, String name, Intent intent) {
		Map<String, Object> temp = new HashMap<String, Object>();
		temp.put("title", name);
		temp.put("intent", intent);
		data.add(temp);
	}

	private final static Comparator<Map<String, Object>> sDisplayNameComparator = new Comparator<Map<String, Object>>() {
		private final Collator collator = Collator.getInstance();

		public int compare(Map<String, Object> map1, Map<String, Object> map2) {
			return collator.compare(map1.get("title"), map2.get("title"));
		}
	};

}
