package sunning.democollection.learn._0316;

import android.graphics.Rect;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import sunning.democollection.BaseActivity;
import sunning.democollection.R;
import sunning.global.common.ButtonFactory;

/**
 * Created by sunning on 16/3/16.
 */
public class TestActivity extends BaseActivity{

    private TextView test;

    private Button button;

    private boolean isOpen;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.test);
        findView();
        Log.e("onCreate","===");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);


        Log.e("onWindowFocusChanged",getTextViewHeight(test)+"===");

        Log.e("onWindowFocusChanged",test.getHeight()+"====");


    }

    private void findView() {
        ButtonFactory buttonFactory = new ButtonFactory(this);
        button = (Button) buttonFactory.create(v -> {
            if(isOpen){
                test.setMaxLines(Integer.MAX_VALUE);
                mesureTextView();
            }else{
                test.setMaxLines(3);
                mesureTextView();
            }
            isOpen = !isOpen;
        });
        ViewGroup viewGroup = (ViewGroup) ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        viewGroup.addView(button);
        test = (TextView) findViewById(R.id.test);
        test.setMaxLines(3);
        test.setText("近年来，越来越多的消费者通过外卖订餐网站，可以足不出户享受到各色美食。可是，您可曾想过，订餐网站上一张张绚丽多彩的美食照片，一张张明亮整洁的厨具照片，它们的真实性又有几分呢？请看315记者调查。\n" +
                "　　为了便于调查采访，记者应聘成了“饿了么”一名配送员。不久便接到了一个名为“速客美食”的配送订单。商家上传的照片显示，实体店面宽敞明亮，灶具规整洁，菜品丰富多样，登记的地址是朝阳区建国路93号院万达广场9号楼B2层。然而，记者接单取餐时，却在店主电话的指引下，七拐八拐来到了通州区杨庄小区一栋居民楼前。");
    }

    private void mesureTextView(){
        Rect rect = new Rect();
        test.getLocalVisibleRect(rect);
        Log.e("getLocalVisibleRect",rect.toString()+"===="+rect.height());

        Rect rect1 = new Rect();
        test.getGlobalVisibleRect(rect1);
        Log.e("getGlobalVisibleRect",rect1.toString()+"===="+rect.height());
    }

    private int getTextViewHeight(TextView pTextView) {
        Layout layout = pTextView.getLayout();
        int desired = layout.getLineTop(pTextView.getLineCount());
        int padding = pTextView.getCompoundPaddingTop() + pTextView.getCompoundPaddingBottom();
        return desired + padding;
    }

}
