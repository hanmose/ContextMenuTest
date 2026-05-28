package kr.ac.kopo.contextmenutest;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnRotation, btnZoomin;
    LinearLayout linear;

    int rotationDegree;

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

        linear = findViewById(R.id.main);

        btnZoomin = findViewById(R.id.btn_change);

        Button btnAlert = findViewById(R.id.btn_alert);
        btnAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();

        if (v == btnRotation) {
            menu.setHeaderTitle("배경색변경");
            menuInflater.inflate(R.menu.context_menu1, menu);
        }

        if (v == btnZoomin) {
            menu.setHeaderTitle("버튼 변경");
            menuInflater.inflate(R.menu.context_menu2, menu);
        }
    }

}