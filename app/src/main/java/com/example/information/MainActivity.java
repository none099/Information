package com.example.information;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_INFO = 1;
    TextView tResult, tResultLabel,tVResultLabel, tVResult;
    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                public void onActivityResult(ActivityResult o) {
                    if (o.getResultCode() == RESULT_OK) {
                        Intent data = o.getData();
                        tVResultLabel.setText("전송\n정보\n출력");
                        String result = "아이디: " + data.getStringExtra("id");
                        result = result + "\n이름: " + data.getStringExtra("name");
                        result = result + "\n나이: " + data.getStringExtra("age");
                        result = result + "\n성별: " + data.getStringExtra("gender");
                        result = result + "\n자격증: " + data.getStringExtra("license");
                        tVResult.setText(result);
                    }
                }
            });

    // 0. TestView 찾아서 맴버필드에 저장
    protected void onCreate(Bundle savedInstanceState) {
        // 1. 명령버튼 획득 클릭 리스너 장착
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnReq = (Button) findViewById(R.id.btnRequest);
        Button btnEnd = (Button) findViewById(R.id.btnEnd);
        EditText editTextId = findViewById(R.id.editTextID);
        tResultLabel = (TextView) findViewById(R.id.tVResultLabel);
        tResult = (TextView) findViewById(R.id.tVResult);
        btnReq.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // 인텐트 생성 -- 정보를 설정 -- 2번째 엑티비티 실행 요청
                String id = editTextId.getText().toString();
                Intent intent = new Intent(getApplicationContext(), InformationActivity.class);
                intent.putExtra("id", id);
                resultLauncher.launch(intent);
            }
        });
        findViewById(R.id.btnEnd).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
    }
    // 2. 결과처리 메소드 오버라이딩
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == 1) && (resultCode == RESULT_OK)) {
            tVResultLabel.setText("결과\n출력");
            String str = "아이디:";
            str += data.getStringExtra("id");
            str = str + "\n이름:" + data.getStringExtra("name");
            str = str + "\n나이:" + data.getStringExtra("age");
            str = str + "\n성별:" + data.getStringExtra("gender");
            str = str + "\n자격증:" + data.getStringExtra("license");
            tVResult.setText(str);
        }
    }
}