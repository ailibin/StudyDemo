package com.aiitec.studydemo.test.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.aiitec.studydemo.R;

/**
 * @Author: ailibin
 * @Time: 2019/02/28
 * @Description: 反转字符串...
 * @Email: ailibin@qq.com
 */
public class ResultActivity extends AppCompatActivity {

    private TextView tv_result;
    private EditText et_param;
    private int type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        type = getIntent().getIntExtra("type", 0);
        tv_result = findViewById(R.id.tv_result);
        et_param = findViewById(R.id.et_param);
        Button btn_test = findViewById(R.id.btn_test);


        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击测试按钮
                switch (type) {
                    case 0:
                        //字符串反转
                        if (TextUtils.isEmpty(et_param.getText().toString())) {
                            Toast.makeText(ResultActivity.this, "请输入参数", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        char[] testCharArray = new char[]{'h', 'e', 'l', 'l', 'o'};
                        reverseString(testCharArray);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 反转一个字符数组:
     * Input: ["h","e","l","l","o"]
     * Output: ["o","l","l","e","h"]
     *
     * @param s
     */
    public  void reverseString(char[] s) {

        //将一个char数组转换成String
        String str = String.valueOf(s);
        String result = "";
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
            result = sb.toString();
        }
        tv_result.setText(result);
        char[] resultArray = result.toCharArray();
    }
}
