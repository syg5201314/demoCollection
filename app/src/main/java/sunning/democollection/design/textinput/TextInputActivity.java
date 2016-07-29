package sunning.democollection.design.textinput;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import sunning.democollection.BaseActivity;
import sunning.democollection.R;

/**
 * Created by sunning on 15/12/29.
 */

public class TextInputActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.input_layout);
        initView();
    }

    private void initView() {
        final TextInputLayout inputLayout = (TextInputLayout) findViewById(R.id.til_userName);
        inputLayout.setHint("Password");
        EditText editText = (EditText) findViewById(R.id.edit_temp);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() > 4) {
                    inputLayout.setError("Password error");
                    inputLayout.setErrorEnabled(true);
                } else {
                    inputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
