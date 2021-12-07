package com.example.terratec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    //variables funcionales
    EditText txtemp;
    EditText txhume;
    Button inval;
    Button  inmot;
    Button  inven;
    Button  incal;
    Button  btnenv;
    TextView tempdef;
    TextView humedef;
    TextView tempact;
    TextView humeact;
    TextView valdt;
    TextView motdt;
    TextView vendt;
    TextView caldt;
    TextView ip;
    private DatabaseReference Clases ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//genera la conexion con la base de datos
        //inval = FirebaseDatabase.getInstance().getReference();
        Clases = FirebaseDatabase.getInstance().getReference();
        //asigna variables con objetos de xml
        setContentView(R.layout.activity_main);
        ip =(TextView)findViewById(R.id.txtip);
        valdt=(TextView)findViewById(R.id.valdt);
        motdt=(TextView)findViewById(R.id.motdt);
        vendt=(TextView)findViewById(R.id.vendt);
        caldt=(TextView)findViewById(R.id.caldt);
        txtemp=(EditText) findViewById(R.id.txtemp);
        txhume=(EditText) findViewById(R.id.txhume);
        inval=(Button) findViewById(R.id.inval);
        inmot=(Button) findViewById(R.id.inmot);
        inven=(Button) findViewById(R.id.inven);
        incal=(Button) findViewById(R.id.incal);
        btnenv=(Button) findViewById(R.id.btnenv);
        tempdef=(TextView) findViewById(R.id.tempdef);
        humedef=(TextView) findViewById(R.id.humedef);
        tempact=(TextView) findViewById(R.id.tempact);
        humeact=(TextView) findViewById(R.id.humeact);
        Clases.addValueEventListener(new ValueEventListener() {
            //Trae valores de firebase y los pone en textview
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String tempa = snapshot.child("Datos").child("tempact").getValue().toString();
                String huma = snapshot.child("Datos").child("humeact").getValue().toString();
                String tempd = snapshot.child("Datos").child("tempdef").getValue().toString();
                String humad = snapshot.child("Datos").child("humedef").getValue().toString();
                String ipl = snapshot.child("Datos").child("ip").getValue().toString();
                String val = snapshot.child("Valvula").child("estado").getValue().toString();
                String mot = snapshot.child("Motor").child("estado").getValue().toString();
                String ven = snapshot.child("Ventilador").child("estado").getValue().toString();
                String cal = snapshot.child("Calentador").child("estado").getValue().toString();
                valdt.setText(val);
                vendt.setText(ven);
                caldt.setText(cal);
                motdt.setText(mot);
                ip.setText(ipl);
                tempdef.setText(tempd);
                humedef.setText(humad);
                tempact.setText(tempa);
                humeact.setText(huma);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //encendido manual
        inval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dtv = valdt.getText().toString();
                int val = Integer.parseInt(dtv);

                switch (val) {
                    case 1:
                        int   valv=0;
                        inval valin = new inval(valv);
                        Clases.child("Valvula").setValue(valin);
                        Toast.makeText(MainActivity.this,"Valvula apagada",Toast.LENGTH_SHORT).show();
                        break;
                    case 0:
                        int   valvc=1;
                        inval valinc = new inval(valvc);
                        Clases.child("Valvula").setValue(valinc);
                        Toast.makeText(MainActivity.this,"Valvula encendida",Toast.LENGTH_SHORT).show();
                        break;
                }


            }

        });

        inven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dtv = vendt.getText().toString();
                int ven = Integer.parseInt(dtv);

                switch (ven) {
                    case 1:
                        int   valve=0;
                        inven valin = new inven(valve);
                        Clases.child("Ventilador").setValue(valin);
                        Toast.makeText(MainActivity.this,"Ventilador apagado",Toast.LENGTH_SHORT).show();
                        break;
                    case 0:
                        int   valvec=1;
                        inven valinc = new inven(valvec);
                        Clases.child("Ventilador").setValue(valinc);
                        Toast.makeText(MainActivity.this,"Ventilador encendido",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        inmot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dtm = motdt.getText().toString();
                int mot = Integer.parseInt(dtm);

                switch (mot) {
                    case 1:
                        int   valm=0;
                        inval valinmo = new inval(valm);
                        Clases.child("Motor").setValue(valinmo);
                        Toast.makeText(MainActivity.this,"Motor apagado",Toast.LENGTH_SHORT).show();
                        break;
                    case 0:
                        int   valmc=1;
                        inval valinmoc = new inval(valmc);
                        Clases.child("Motor").setValue(valinmoc);
                        Toast.makeText(MainActivity.this,"Motor encendido",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        incal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dtc = caldt.getText().toString();
                int cal = Integer.parseInt(dtc);

                switch (cal) {
                    case 1:
                        int   valc=0;
                        inval valinc = new inval(valc);
                        Clases.child("Calentador").setValue(valinc);
                        Toast.makeText(MainActivity.this,"Calentador apagado",Toast.LENGTH_SHORT).show();
                        break;
                    case 0:
                        int   valcc=1;
                        inval valincc = new inval(valcc);
                        Clases.child("Calentador").setValue(valincc);
                        Toast.makeText(MainActivity.this,"Calentador encendido",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });



//envia datos a firebase
        btnenv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regisclas ();
            }
        });

    }

    //envia la actulizacion de datos
    public void regisclas (){
        int temp = Integer.parseInt(txtemp.getText().toString());
        float hume = Float.parseFloat(txhume.getText().toString());
        String iplo = ip.getText().toString();
        String humac = humeact.getText().toString();
        String tempac =tempact.getText().toString();
       // String vte = txtemp.getText().toString();
       // String vhm = txhume.getText().toString();
        Clases tempe = new Clases( temp, hume,humac,tempac,iplo);
            //

                Clases.child("Datos").setValue(tempe);

                Toast.makeText(this, "Datos actualizados", Toast.LENGTH_LONG).show();



     //


    }
}