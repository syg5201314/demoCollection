package sunning.democollection;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.InjectView;

/**
 * @author sunning
 * @date 2015-11-16
 */
public class BaseActivity extends AppCompatActivity {
    private Activity currentActivity;
    public
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        currentActivity = this;
        initToolbar();
    }

    private void initToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setTitle("");
//			homeButton.setOnClickListener(new View.OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					onBackPressed();
//				}
//			});
        }
    }


    protected SimpleAdapter getAdapter(String[] array, int flag) throws ClassNotFoundException {
        return new SimpleAdapter(this, getData(array, flag),
                android.R.layout.simple_list_item_1, new String[]{"title"},
                new int[]{android.R.id.text1});
    }

    private List<Map<String, Object>> getData(String[] array, int flag) throws ClassNotFoundException {
        List<Map<String, Object>> myData = new ArrayList();
        Map<String, Object> mapData;
        if (array == null) {
            return myData;
        }
        for (int i = 0; i < array.length; i++) {
            mapData = new HashMap();
            mapData.put("title", getListTitle(array[i]));
            mapData.put("intent", getIntent(array[i], flag));
            myData.add(mapData);
        }
        return myData;
    }

    private String getListTitle(String string) {
        PackageManager pm = getPackageManager();
        ComponentName name = new ComponentName(getPackageName(), getPackageName() + string);
        try {
            CharSequence label = pm.getActivityInfo(name, PackageManager.GET_INTENT_FILTERS).nonLocalizedLabel;
            if (!TextUtils.isEmpty(label)) {
                return label.toString();
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return getClassName(string);
        }
        return getClassName(string);
    }

    private String getClassName(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        return path.substring(path.lastIndexOf(".") + 1);
    }

    private Intent getIntent(String string, int flag) throws ClassNotFoundException {

        Intent intent = new Intent();
        Class<?> clazz = null;
        switch (flag) {
            case 0:
                clazz = Class.forName(getPackageName() + string);
                break;
        }
        intent.setClass(currentActivity, clazz);
        return intent;
    }

    public void setListener(ListView listView) {
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(position);
                startActivity((Intent) map.get("intent"));
            }
        });
    }

    public static void log(Object obj , String content){
        Log.e(obj.getClass().getSimpleName(),content);
    }
}
