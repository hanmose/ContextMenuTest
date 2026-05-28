package kr.ac.kopo.contextmenutest;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnZoomin;
    Button btnRotation;
    LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnZoomin = findViewById(R.id.btn_change);
        linear = findViewById(R.id.main);

        Button btnAlert = findViewById(R.id.btn_alert);
        btnAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 대화상자내의 메시지를 "배경색을 파랑색으로 변경할까요? 로 변경
                // 대화상자내의 확인 버튼을 클릭: 액티비티의 LinearLayout 배경색 파랑으로 변경
                // 대화상자내의 취소 버튼을 클릭: 배경색 변화 없음
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("대화상자연습");
                dialog.setMessage("배경색을 파랑색으로 변경할까요?");
                dialog.setIcon(R.drawable.icon);
                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        linear.setBackgroundColor(Color.BLUE);
                    }
                });
                dialog.setNegativeButton("취소", null);
                dialog.show();
            }
        });

        registerForContextMenu(btnRotation);
        registerForContextMenu(btnZoomin);
    }
}