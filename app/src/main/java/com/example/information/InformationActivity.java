package com.example.information;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InformationActivity extends AppCompatActivity {

    @Override // 어노테이션 추가
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        // 1. 실행요청한 인텐트 휙득 >> 정보 획득
        Intent mainIntent = getIntent();
        String id = mainIntent.getStringExtra("id");

        // 2. 각종 입력 취젯 획득
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextAge = findViewById(R.id.editTextAge);
        RadioButton radioFemale = findViewById(R.id.radioFemale);
        CheckBox cBInfo = findViewById(R.id.cBInfo);
        CheckBox cBAI = findViewById(R.id.cBAI);
        CheckBox cBSecurity = findViewById(R.id.cBSecurity);
        Button btnSend = findViewById(R.id.btnSend);


        // 3. 명령버튼 획득 << 클릭리스너 획득
        findViewById(R.id.btnSend).setOnClickListener(new View.OnClickListener() {
            // 3-1. onClick() 획득
            //      - 인텐트 생성

            @Override
            public void onClick(View view) {
                Intent it = new Intent();
                //      - 인텐트의 입력 정보를 설정 << 전송
                String name = editTextName.toString();
                it.putExtra("name", editTextName.getText().toString());
                it.putExtra("age", editTextAge.getText().toString());

                if (radioFemale.isChecked())
                    it.putExtra("gender", "여자");
                else
                    it.putExtra("gender", "남자");


                String license = "";
                if (cBInfo.isChecked())
                    license += "\n   정보처리기사";
                if (cBAI.isChecked())
                    license += "\n   인공지능데이터전문가";
                if (cBSecurity.isChecked())
                    license += "\n   정보보안기사";

                it.putExtra("license", license);

                setResult(RESULT_OK, it);
                finish();
            }
        });
    }
}
