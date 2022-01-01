package com.metabugs.applicationbluetooth;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.widget.ArrayAdapter;

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
}
