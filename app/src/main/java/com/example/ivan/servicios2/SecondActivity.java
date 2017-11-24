package com.example.ivan.servicios2;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/* Esta clase representaria a la activity de informacion de
 * las mesas de examenes, osea, la aplicacion principal.
* */
public class SecondActivity extends AppCompatActivity {

    private TextView txtNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txtNombre = (TextView)findViewById(R.id.lblNombre);

        Log.i("SecondActivity....","dasdsadasdadsda");


        /* Lo que hago aca es obtener el intent que envia MyService cuando yo estoy usando la aplicacion principal
        *  entonces obtengo los datos de ese intent, que serian las materias a las que me puedo anotar,
        *  y las obtengo a partir del bundle.
        * */
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter filter = new IntentFilter("SecondActivity");
        //filter.addAction(CountService.EXTRA_COUNT_TARGET);
        broadcastManager.registerReceiver(
                new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        Log.i("SecondActivity....","encontre el mensaje brodcasteado!");
                        Bundle bundle = intent.getExtras();
                        txtNombre.setText(bundle.getString("Nombre"));

                        //aca elimino la notificacion
                        NotificationManager mNotifyMgr =(NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
                        mNotifyMgr.cancel(1);
                    }
                },
                filter
        );


    }
}
