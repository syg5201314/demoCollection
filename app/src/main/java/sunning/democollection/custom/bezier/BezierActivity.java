package sunning.democollection.custom.bezier;

import android.graphics.Matrix;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import sunning.democollection.BaseActivity;
import sunning.democollection.R;

/**
 * Created by sunning on 16/8/3.
 */

public class BezierActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.bezier);
        ImageView auto = (ImageView) findViewById(R.id.audi);
        Matrix matrix = auto.getMatrix();
        Toast.makeText(this, "matrix"+ matrix.toShortString(), Toast.LENGTH_SHORT).show();
        matrix.postScale(0.5f,0.5f);
    }
}
