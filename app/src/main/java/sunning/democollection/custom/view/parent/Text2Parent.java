package sunning.democollection.custom.view.parent;

import android.content.Intent;
import android.view.View;

import sunning.global.common.ButtonFactory;

/**
 * Created by sunning on 16/1/11.
 */
public class Text2Parent extends ViewParentDemo {
    @Override
    public View getView() {
        return new ButtonFactory(this).create(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 startActivity(new Intent(Text2Parent.this,Text1Parent.class));
            }
        });
    }
}
