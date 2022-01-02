package com.metabugs.applicationbluetooth;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final int SOLICITAR_ATIVACAO = 1;
    private final int SOLICITAR_CONEXAO = 2;
    BluetoothAdapter mBluetoothAdapter = null;
    Button btConectar, btLed1, btLed2, btLed3;
    boolean conection = false;

    private static String MAC = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        btConectar = findViewById(R.id.bt_conectar);
        btLed1 = findViewById(R.id.bt_led_1);
        btLed2 = findViewById(R.id.bt_led_2);
        btLed3 = findViewById(R.id.bt_led_3);

        btConectar.setOnClickListener(this);

        if(mBluetoothAdapter == null){
            Toast.makeText(this, "Não existe bluetooth", Toast.LENGTH_SHORT).show();
        }else if(!mBluetoothAdapter.isEnabled()){
            Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            //noinspection deprecation
            startActivityForResult(enableBluetooth, SOLICITAR_ATIVACAO);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case SOLICITAR_ATIVACAO:
                if(resultCode == Activity.RESULT_OK){
                    Toast.makeText(this, "O bluetooth foi ativado", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Bluetooth não ativado, encerrando app", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                }
            case SOLICITAR_CONEXAO:
                if(resultCode == Activity.RESULT_OK){
                    MAC = data.getExtras().getString(ListasDispositivos.ENDERECO_MAC);
                    Toast.makeText(this, "MAC final: "+MAC, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Falha ao obter o MAC", Toast.LENGTH_SHORT).show();
                }

        }

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == btConectar.getId()){
            if(conection){
                //desconectar
            }else{
                //conectar
                Intent abreLista = new Intent(this, ListasDispositivos.class);
                //noinspection deprecation
                startActivityForResult(abreLista, SOLICITAR_CONEXAO);
            }
        }
    }
}