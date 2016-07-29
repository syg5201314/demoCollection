package sunning.democollection.custom.view.parent;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sunning.democollection.R;
import sunning.global.common.ButtonFactory;

/**
 * Created by sunning on 16/1/11.
 */
public class Text1Parent extends ViewParentDemo {
    @Override
    public View getView() {
        ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
        View view = LayoutInflater.from(this).inflate(R.layout.input_layout, null);
        viewGroup.addView(new ButtonFactory(this).create("点我", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Text1Parent.this,Text2Parent.class));
            }
        }));
        return view.findViewById(R.id.edit_temp);
    }
}
