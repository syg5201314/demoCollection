package sunning.democollection.draw.iconfont;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import sunning.democollection.BaseActivity;
import sunning.democollection.R;

/**
 * Created by sunning on 15/12/29.
 */
public class IconFontActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.icon_font_layout);
        Typeface iconFont = Typeface.createFromAsset(getAssets(), "iconfont/iconfont.ttf");
        TextView textview = (TextView)findViewById(R.id.like);
        textview.setTypeface(iconFont);
    }
}
