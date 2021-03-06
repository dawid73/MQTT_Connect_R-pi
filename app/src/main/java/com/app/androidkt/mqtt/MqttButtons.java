package com.app.androidkt.mqtt;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class MqttButtons extends AppCompatActivity {

    private MqttAndroidClient client;
    private String TAG = "MqttButtons";
    private PahoMqttClient pahoMqttClient;

    private Button pin11_0_btn, pin12_0_btn, pin11_1_btn, pin12_1_btn, alarm_on_btn, domofon_open_btn, wol_lenovo_btn, subskrybuj_btn;


    String brokerUrl, username, password, clientId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mqtt_buttons);

        try {
            brokerUrl = Helper.getProperty("mqtthost",getApplicationContext());
            username = Helper.getProperty("username",getApplicationContext());
            password = Helper.getProperty("password",getApplicationContext());
            clientId = Helper.getProperty("clientid", getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }

        pahoMqttClient = new PahoMqttClient();

        pin11_0_btn = (Button) findViewById(R.id.pin11_0_btn);
        pin12_0_btn = (Button) findViewById(R.id.pin12_0_btn);

        pin11_1_btn = (Button) findViewById(R.id.pin11_1_btn);
        pin12_1_btn = (Button) findViewById(R.id.pin12_1_btn);

        alarm_on_btn = (Button) findViewById(R.id.btn_alarm_on);

        domofon_open_btn = (Button) findViewById(R.id.btn_domofon_door);

        subskrybuj_btn = (Button) findViewById(R.id.btn_subskrybuj);

        wol_lenovo_btn = (Button) findViewById(R.id.btn_wol_lenovo);


        client = pahoMqttClient.getMqttClient(getApplicationContext(), brokerUrl, clientId, username, password);

//        if (!topic.isEmpty()) {
//            try {
//                pahoMqttClient.subscribe(client, topic, 1);
//            } catch (MqttException e) {
//                e.printStackTrace();
//            }
//        }
//


        subskrybuj_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic = "dev/alarm".trim();
                if (!topic.isEmpty()) {
                    try {
                        pahoMqttClient.subscribe(client, topic, 1);
                        Toast.makeText(MqttButtons.this, "Subsrybcja włączona poprawnie",
                                Toast.LENGTH_LONG).show();
                    } catch (MqttException e) {
                        Toast.makeText(MqttButtons.this, "Błąd przy włączeniu subsrypcji",
                                Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
            }
        });








        pin11_0_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "0";
                try {
                    pahoMqttClient.publishMessage(client, msg, 1, "dev/pin11");
                } catch (MqttException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });

        pin12_0_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "0";
                try {
                    pahoMqttClient.publishMessage(client, msg, 1, "dev/pin12");
                } catch (MqttException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });

        pin11_1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "1";
                try {
                    pahoMqttClient.publishMessage(client, msg, 1, "dev/pin11");
                } catch (MqttException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });

        pin12_1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "1";
                try {
                    pahoMqttClient.publishMessage(client, msg, 1, "dev/pin12");
                } catch (MqttException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });

        wol_lenovo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "1";
                try {
                    pahoMqttClient.publishMessage(client, msg, 1, "dev/wol_pc");
                    Toast.makeText(MqttButtons.this, "Wysłano pakiet WOL do Lenovo",
                            Toast.LENGTH_LONG).show();
                } catch (MqttException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });


        alarm_on_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg1 = "1";
                String msg0 = "0";
                try {
                    pahoMqttClient.publishMessage(client, msg0, 1, "dev/pin13");
                    Toast.makeText(MqttButtons.this, "PIN 13 - ON ",
                            Toast.LENGTH_SHORT).show();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pahoMqttClient.publishMessage(client, msg1, 1, "dev/pin13");
                    Toast.makeText(MqttButtons.this, "PIN 13 - OFF ",
                            Toast.LENGTH_SHORT).show();
                } catch (MqttException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });

        domofon_open_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg1 = "1";
                String msg0 = "0";
                try {
                    pahoMqttClient.publishMessage(client, msg0, 1, "dev/pin15");
                    Toast.makeText(MqttButtons.this, "PIN 15 - ON ",
                            Toast.LENGTH_SHORT).show();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pahoMqttClient.publishMessage(client, msg1, 1, "dev/pin15");
                    Toast.makeText(MqttButtons.this, "PIN 15 - OFF ",
                            Toast.LENGTH_SHORT).show();
                } catch (MqttException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });


        Intent intent = new Intent(MqttButtons.this, MqttMessageService.class);
        startService(intent);
    }
}
