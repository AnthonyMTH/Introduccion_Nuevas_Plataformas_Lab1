package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private static final String FILE_NAME = "registro.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        EditText edtNames = binding.edtNames;
        EditText edtLastNames = binding.edtLastNames;
        EditText edtPhone = binding.edtPhone;
        EditText edtCorreo = binding.edtCorreo;
        EditText edtGrpSang = binding.edtGrpSang;
        Button btnRegister = binding.btnRegister;
        Button btnRead = binding.btnRead;

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String names = edtNames.getText().toString();
                String lastNames = edtLastNames.getText().toString();
                String phone = edtPhone.getText().toString();
                String correo = edtCorreo.getText().toString();
                String grpSang = edtGrpSang.getText().toString();

                String data = "Nombres: " + names + "\nApellidos: " + lastNames + "\nTeléfono: " + phone +
                        "\nCorreo: " + correo + "\nGrupo Sanguíneo: " + grpSang + "\n\n";

                try (FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE)) {
                    fos.write(data.getBytes());
                    Log.d("MainActivity", "Datos registrados exitosamente");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("MainActivity", "Error al registrar los datos", e);
                }
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try (FileInputStream fis = openFileInput(FILE_NAME);
                     InputStreamReader isr = new InputStreamReader(fis);
                     BufferedReader br = new BufferedReader(isr)) {

                    StringBuilder data = new StringBuilder();
                    String line;

                    while ((line = br.readLine()) != null) {
                        data.append(line).append("\n");
                    }

                    Log.d("MainActivity", "Datos leídos:\n" + data.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("MainActivity", "Error al leer los datos", e);
                }
            }
        });
    }
}