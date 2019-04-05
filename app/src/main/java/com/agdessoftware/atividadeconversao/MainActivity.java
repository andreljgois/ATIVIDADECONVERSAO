package com.agdessoftware.atividadeconversao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.text.DecimalFormat;



public class MainActivity extends AppCompatActivity {

    private Button bConverter, bLimpar;
    private RadioGroup rgConversao;
    private EditText editDollar, editReal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editDollar = findViewById(R.id.editDollar);
        editReal = findViewById(R.id.editReal);
        rgConversao = findViewById(R.id.rgConversao);
        bConverter = findViewById(R.id.bConverter);
        bLimpar = findViewById(R.id.bLimpar);

        bConverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dollartxt = editDollar.getText().toString();
                String realtxt = editReal.getText().toString();
                Double cotacao = 2.10;
                Double valorConvertido;

                int rgConverter = rgConversao.getCheckedRadioButtonId();
                switch (rgConverter){
                    case R.id.rbDR:
                        rgConverter = 1;
                        break;
                    case R.id.rbRD:
                        rgConverter = 2;
                        break;
                }

                if (rgConverter == 1){

                    if ((dollartxt.equals(""))) {
                        Toast.makeText(getApplicationContext(),"Preencher campo dollars!", Toast.LENGTH_LONG).show();
                        //editDollar.setError("Preencher campo");
                    }else{
                        Double dollar = Double.parseDouble(dollartxt.replace
                                (",",".").replace("$",""));
                        valorConvertido = dollar * cotacao;
                        DecimalFormat df = new DecimalFormat();
                        df.applyPattern("R$ 0.00");
                        editReal.setText(df.format(valorConvertido));
                    }
                }else if (rgConverter == 2){
                    if ((realtxt.equals(""))){
                        Toast.makeText(getApplicationContext(),"Preencher campo reais!", Toast.LENGTH_LONG).show();
                        //editReal.setError("Preencher campo");
                    }else{
                        Double real = Double.parseDouble(realtxt.replace
                                (",",".").replace("R$",""));
                        valorConvertido = real / cotacao;
                        DecimalFormat df = new DecimalFormat();
                        df.applyPattern("$ 0.00");
                        editDollar.setText(df.format(valorConvertido));
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Por favor, selecione a conversão!", Toast.LENGTH_LONG).show();
                }
            }
        });

        bLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editDollar.setText("");
                editReal.setText("");
                rgConversao.clearCheck();

            }
        });

    }
}