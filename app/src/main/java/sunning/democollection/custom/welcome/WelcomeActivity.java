package sunning.democollection.custom.welcome;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import sunning.democollection.BaseActivity;
import sunning.democollection.R;

/*
 * Created by sunning on 2017/5/8.
 */

public class WelcomeActivity extends BaseActivity {

    ViewPager viewPager;
    private int[] layouts = {
            R.layout.welcome1,
            R.layout.welcome2,
            R.layout.welcome3
//			R.layout.welcome1,
//			R.layout.welcome2,
//			R.layout.welcome3
    };

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.welcome);
        viewPager = (ViewPager) findViewById(R.id.welcome_viewpager);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment f = new TranslateFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("layoutId", layouts[position]);
                bundle.putInt("pageIndex", position);
                f.setArguments(bundle );
                return f;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
    }

}
