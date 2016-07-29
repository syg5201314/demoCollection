package sunning.global.common;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by sunning on 15/12/29.
 */
public class ButtonFactory {

    private Context context;

    public ButtonFactory(Context context) {
        this.context = context;
    }

    public View create(View.OnClickListener listener){
        return this.create("",listener);
    }

    public View create(String buttonText , View.OnClickListener listener){
        if(context != null){
            Button button = new Button(context);
            if(!TextUtils.isEmpty(buttonText)){
                button.setText(buttonText);
            }else{
                button.setText("button");
            }
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            button.setLayoutParams(params);
            button.setPadding(10,10,10,10);
            button.setOnClickListener(listener);
            return button;
        }
        return null;
    }
}
