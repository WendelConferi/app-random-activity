package com.example.sorteadornumero;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


int contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carregarElementos();
    }

    public void carregarElementos(){

        ArrayList<Atividades> atividadesListadas = new ArrayList<Atividades>();



        EditText editAtividade = (EditText) findViewById(R.id.editAtividade);
        Button btnAdicionar = (Button) findViewById(R.id.btnAdicionar);
        CheckBox check1 = (CheckBox) findViewById(R.id.check1);
        CheckBox check2 = (CheckBox) findViewById(R.id.check2);
        CheckBox check3 = (CheckBox) findViewById(R.id.check3);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Atividades atividadeVO = new Atividades();
                atividadeVO.setSomaImportancia(0);

                if(editAtividade.getText() != null) {

                    if (check1.isChecked()) {
                        atividadeVO.setIndImportancia(1);
                        atividadeVO.setDesNome(String.valueOf(editAtividade.getText()));
                    }
                    if (check2.isChecked()) {
                        atividadeVO.setIndImportancia(2);
                        atividadeVO.setDesNome(String.valueOf(editAtividade.getText()));
                    }
                    if (check3.isChecked()) {
                        atividadeVO.setIndImportancia(3);
                        atividadeVO.setDesNome(String.valueOf(editAtividade.getText()));
                    } else {
                        atividadeVO.setIndImportancia(1);
                        atividadeVO.setDesNome(String.valueOf(editAtividade.getText()));
                    }

                    atividadeVO.setMinimoImportancia(contador);

                    atividadeVO.setSomaImportancia(atividadeVO.getIndImportancia() + contador);
                    contador = atividadeVO.getSomaImportancia();

                    atividadesListadas.add(atividadeVO);
                    Toast.makeText(MainActivity.this, "Adicionado " + atividadeVO.getDesNome(), Toast.LENGTH_SHORT).show();
                    check1.setChecked(false);
                    check2.setChecked(false);
                    check3.setChecked(false);
                    editAtividade.setText("");
                }else{
                    Toast.makeText(MainActivity.this, "Favor preecher o campo do nome", Toast.LENGTH_SHORT).show();
                }

            }
        });

        TextView txtSelecionado = (TextView)findViewById(R.id.txtSelecionado);
        Button btnsortear = (Button) findViewById(R.id.btnsortear);
        Random random = new Random();
        btnsortear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numero = random.nextInt(contador);

                for(Atividades at: atividadesListadas){
                    if(at.getMinimoImportancia() <= numero && at.getSomaImportancia() >= numero){
                        txtSelecionado.setText(at.getDesNome());
                    }
                }
            }
        });
    }
}