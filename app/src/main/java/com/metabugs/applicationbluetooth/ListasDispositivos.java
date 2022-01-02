package com.metabugs.applicationbluetooth;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Set;

public class ListasDispositivos extends ListActivity {
    private BluetoothAdapter meuBluetoothAdapter2 = null;
    static final String ENDERECO_MAC = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> arrayBluetooth = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        meuBluetoothAdapter2 = BluetoothAdapter.getDefaultAdapter();

        //traz as informações dos dispositivos pareados
        Set<BluetoothDevice> dispositivosPareados = meuBluetoothAdapter2.getBondedDevices();

        if(dispositivosPareados.size() > 0){
            for(BluetoothDevice dispositivo : dispositivosPareados){
                String nomeBluetooth = dispositivo.getName();
                String macBluetooth = dispositivo.getAddress();
                arrayBluetooth.add(nomeBluetooth + "\n" + macBluetooth);
            }
        }

        setListAdapter(arrayBluetooth);

    }

    //Pega um item da lista que eu clicar
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String informacaoGeral = ((TextView) v).getText().toString();
        //pegando somente o endreco mac da string informacaoGeral
        String mac = informacaoGeral.substring(informacaoGeral.length() - 17);
        Intent retornaMac = new Intent();
        //criando uma ponte para que a variável retorne para o main armazenando o valor em ENDERECO_MAC
        retornaMac.putExtra(ENDERECO_MAC, mac);
        //Usando o setResult para retornar o ok passando a intent retornaMac
        setResult(RESULT_OK, retornaMac);
        finish();
    }
}
