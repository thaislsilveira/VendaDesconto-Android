package appvendadesconto.com.br.appvendadesconto;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    //declarar os atributos da Classe
    EditText edtCodigo, edtData, edtVenda;
    ImageButton btnCalcular, btnLimpar;
    RadioGroup rgdDesconto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Atribuir os componentes visuais da activity_main
         * nos atributos da classe
         */

        edtCodigo = (EditText) findViewById(R.id.edtCodigo);
        edtData = (EditText) findViewById(R.id.edtData);
        edtVenda = (EditText) findViewById(R.id.edtVenda);

        btnCalcular = (ImageButton) findViewById(R.id.btnCalcular);
        btnLimpar = (ImageButton) findViewById(R.id.btnLimpar);


        rgdDesconto = (RadioGroup) findViewById(R.id.rgdDesconto);
        //ação do método do botão Calcular

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // esta verificação é de todos os intens ao mesmo tempo, é possivel fazer individualmente

                if (edtCodigo.getText().length() <= 0
                        || edtData.getText().length() <= 0
                        || edtVenda.getText().length() <= 0) {
                    Toast.makeText(getBaseContext(), "Informe TODOS os campos!!!",
                            Toast.LENGTH_SHORT).show();
                    edtCodigo.requestFocus();
                } else {

                    Double valor = Double.parseDouble(edtVenda.getText().toString());

                    Double desconto = 0.00;
                    double valorcdesconto = 0.00;

                    int opcao = rgdDesconto.getCheckedRadioButtonId();
                    switch (opcao) {
                        case R.id.rdbDesconto5:
                            desconto = valor; // valor += 0.95 valor = valor - (valor * desconto)
                            desconto = valor * .05;
                            break;
                        case R.id.rdbDesconto7:
                            desconto = valor * .07;
                            break;
                        case R.id.rdbDesconto10:
                            desconto = valor * .10;
                            break;
                        case R.id.rdbDesconto20:
                            desconto = valor * .20;
                            break;
                        case R.id.rdbDesconto50:
                            desconto = valor * .50;
                            break;
                    }

                    valorcdesconto = valor - desconto;
                    AlertDialog.Builder alerta = new
                            AlertDialog.Builder(MainActivity.this);

                    alerta.setTitle("DADOS DA VENDA");
                    alerta.setMessage("Venda fechada com o valor de R$: "
                            + edtVenda.getText().toString()
                            + "\nDesconto concedido de R$ "
                            + new DecimalFormat("0.00").format(desconto)
                            + "\nValor final da venda com desconto R$ "
                            + new DecimalFormat("0.00").format(valorcdesconto));
                    alerta.setNeutralButton("Fechar", null);
                    alerta.show();
                }
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtCodigo.setText("");
                edtData.setText("");
                edtVenda.setText("");
                edtCodigo.requestFocus();
            }
        });

    }
}
