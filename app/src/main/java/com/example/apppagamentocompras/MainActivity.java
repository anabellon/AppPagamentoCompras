package com.example.apppagamentocompras;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


/* --------------- PALOMAKOBA TURMA 3 ---------------
*
* Módulo: Desenvolvimento na Plataforma Android 1
* Docente: Dra. Liliane da Silva Coelho Jacon
* Discente: Ana Paula Rodrigues Bellon
*
*/
public class MainActivity extends AppCompatActivity {

    Button btTotal, btDesconto, btPagamento;
    RadioGroup grupo, grupo2;
    RadioButton rbSemDesconto, rbCinco, rbDez, rbQuinze;
    EditText edValorPago;
    TextView exibeTotal, exibeDesconto;
    CheckBox cbArroz, cbCarne, cbPao, cbLeite, cbOvos;
    double valortotal,descontototal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btTotal = findViewById(R.id.btTotal);
        btDesconto = findViewById(R.id.btDesconto);
        btPagamento = findViewById(R.id.btPagamento);
        grupo = findViewById(R.id.grupo);
        grupo2 = findViewById(R.id.grupo2);
        rbSemDesconto = findViewById(R.id.rbSemDesconto);
        rbCinco = findViewById(R.id.rbCinco);
        rbDez = findViewById(R.id.rbDez);
        rbQuinze = findViewById(R.id.rbQuinze);
        edValorPago = findViewById(R.id.edValorPago);
        exibeTotal = findViewById(R.id.exibeTotal);
        exibeDesconto = findViewById(R.id.exibeDesconto);
        cbArroz = findViewById(R.id.cbArroz);
        cbCarne = findViewById(R.id.cbCarne);
        cbPao = findViewById(R.id.cbPao);
        cbLeite = findViewById(R.id.cbLeite);
        cbOvos = findViewById(R.id.cbOvos);

        btTotal.setOnClickListener(new View.OnClickListener(){ // CheckBox
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View view) {
                double total = 0;

                if (cbArroz.isChecked())
                    total += 3.50;
                if (cbCarne.isChecked())
                    total += 12.30;
                if (cbPao.isChecked())
                    total += 2.20;
                if (cbLeite.isChecked())
                    total += 5.50;
                if (cbOvos.isChecked())
                    total += 7.50;

                valortotal = total;
                exibeTotal.setText(String.format("Valor: R$ %.2f", total));
            }
        });

        grupo2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { //RadioButtons
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) { // s

                if (i == R.id.rbCinco)
                    descontototal = valortotal * 0.05;

                else if (i == R.id.rbDez)
                    descontototal = valortotal * 0.10;

                else if (i == R.id.rbQuinze)
                    descontototal = valortotal * 0.15;

                else
                    descontototal = 0;
            }
        });

        btDesconto.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View view) {

                exibeDesconto.setText(String.format("Desconto: R$ %.2f\nValor final: R$ %.2f", descontototal,(valortotal-descontototal)));
            }
        });

        btPagamento.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View view) {
                double troco, valorfinal;

                valorfinal = valortotal - descontototal;

                if (edValorPago.getText().length() != 0) { // Verificando se houve entrada de valores

                    double valor = Double.parseDouble(edValorPago.getText().toString());
                    troco = valor - valorfinal;

                    if ((valor >= valorfinal) && (valortotal != 0)) {

                        AlertDialog.Builder janela = new AlertDialog.Builder(MainActivity.this);
                        janela.setTitle(R.string.app_name);
                        janela.setMessage(String.format("Valor total da compra: R$ %.2f\n" +
                                "Desconto: R$ %.2f\n" +
                                "Valor pago: R$ %.2f\n" +
                                "Troco: R$ %.2f", valortotal, descontototal, valor, troco));
                        janela.setNeutralButton("OK", null);
                        janela.show();
                    } else {

                        AlertDialog.Builder janelaAviso = new AlertDialog.Builder(MainActivity.this);
                        janelaAviso.setTitle("Aviso!");
                        janelaAviso.setMessage("Valor incompatível com a compra!");
                        janelaAviso.setNeutralButton("OK", null);
                        janelaAviso.show();
                    }

                } else { // Caso não tenha entrada de valores

                    AlertDialog.Builder janelaAviso = new AlertDialog.Builder(MainActivity.this);
                    janelaAviso.setTitle("Aviso!");
                    janelaAviso.setMessage("Valor incompatível com a compra!");
                    janelaAviso.setNeutralButton("OK", null);
                    janelaAviso.show();
                }
            }
        });
    }
}