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

        // 이전 액티비티에서 보낸 ID 받기
        Intent mainIntent = getIntent();
        String id = mainIntent.getStringExtra("id");

        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextAge = findViewById(R.id.editTextAge);
        RadioButton radioFemale = findViewById(R.id.radioFemale);
        CheckBox cBInfo = findViewById(R.id.cBInfo);
        CheckBox cBAI = findViewById(R.id.cBAI);
        CheckBox cBSecurity = findViewById(R.id.cBSecurity);
        Button btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent(); // 변수명 구분

                // [수정] 괄호 오타 해결: (("id", id) -> "id", id
                resultIntent.putExtra("id", id);
                resultIntent.putExtra("name", editTextName.getText().toString());
                resultIntent.putExtra("age", editTextAge.getText().toString());

                if (radioFemale.isChecked()) {
                    resultIntent.putExtra("gender", "여자");
                } else {
                    resultIntent.putExtra("gender", "남자");
                }

                String strLicense = "";
                if (cBInfo.isChecked())    strLicense += "\n정보처리기사";
                if (cBAI.isChecked())     strLicense += "\n인공지능데이터전문가";
                if (cBSecurity.isChecked()) strLicense += "\n정보보안기사";

                resultIntent.putExtra("license", strLicense);

                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
