package com.example.ivan.servicios2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;


/* Esta clase representaria a la activity de login
*
* */
public class MainActivity extends AppCompatActivity {

    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    //este metodo se ejecuta cuando apreto boton "startService".
    public void startService(View view)
    {
        Thread thread = new Thread(){
            public void run(){
                Log.i("Empecemos a contar....","wenassssss");
                /* aca lo que hacemos es definir una alarma, que se ejecuta cada cierto tiempo y ejecuta el servicio. */

                //https://developer.android.com/training/scheduling/alarms.html
                alarmMgr = (AlarmManager)getSystemService(ALARM_SERVICE);
                Intent intent = new Intent(MainActivity.this, MyService.class);
                alarmIntent = PendingIntent.getService(MainActivity.this, 0, intent, 0);


                // Set the alarm to start at 14:51 p.m.
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, 16);//8
                calendar.set(Calendar.MINUTE, 17);//30


                // setRepeating() lets you specify a precise custom interval--in this case,
                // 20 minutes.
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        1000 * 60, alarmIntent);//60 segundos

            }
        };
        thread.start();
    }


    /*
    public void startService(View view)
    {
        Intent intent = new Intent(this,MyService.class);
        startService(intent);
    }
    */


    //este metodo se ejecuta cuando apreto boton "stopService".
    public void stopService(View view)
    {
        Intent intent = new Intent(this,MyService.class);
        stopService(intent);
        cancelarAlarma();
    }



    public void cancelarAlarma()
    {
        // If the alarm has been set, cancel it.
        if (alarmMgr!= null) {
            alarmMgr.cancel(alarmIntent);
        }

    }

}

