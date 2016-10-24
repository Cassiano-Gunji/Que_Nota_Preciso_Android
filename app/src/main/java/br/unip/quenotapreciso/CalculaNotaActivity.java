package br.unip.quenotapreciso;


import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;

public class CalculaNotaActivity extends AppCompatActivity {

    // Constantes usadas ao se salvar/restaurar o estado:
    private static final String NP1 = "NP1";
    private static final String PIM = "PIM";

    // Atributos que armazenam os valores que devem ser mantidos quando
    // o aplicativo reinicia.
    private double np1;
    private double pim;

    // Armazena as referências dos componentes da interface gráfica;
    private EditText np1EditText;
    private EditText np250EditText;
    private EditText np275EditText;
    private EditText np2100EditText;
    private SeekBar pimSeekBar;
    private EditText pimEditText;
    private EditText np2EditText;

    // Método chamado quando a Activity é criada ou reativada.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcula_nota);

        // Obtém referências aos componentes da tela:
        np1EditText = (EditText) findViewById(R.id.np1EditText);
        np250EditText = (EditText) findViewById(R.id.np250EditText);
        np275EditText = (EditText) findViewById(R.id.np275EditText);
        np2100EditText = (EditText) findViewById(R.id.np2100EditText);
        pimSeekBar = (SeekBar) findViewById(R.id.pimSeekBar);
        pimEditText = (EditText) findViewById(R.id.pimEditText);
        np2EditText = (EditText) findViewById(R.id.np2EditText);

        // Cria os ouvintes de eventos para as views interativas:
        np1EditText.addTextChangedListener(ouvinteNp1EditText);
        pimSeekBar.setOnSeekBarChangeListener(ouvintePimSeekBar);

        // Verifica se o aplicativo acabou de ser iniciado ou se está
        // sendo restaurado:
        if (savedInstanceState == null) {
            np1 = 5.0;
            pim = 7.5;
        } else {
            // o aplicativo está sendo restaurado da memória, não está
            // sendo executado a partir do zero. Assim, os valores de
            // np1 e pim são restaurados:
            np1 = savedInstanceState.getDouble(NP1);
            pim = savedInstanceState.getDouble(PIM);
        }

        // Atualiza os componentes gráficos com os valores atualizados:
        np1EditText.setText(String.format("%.1f", np1));
        pimSeekBar.setProgress((int) (pim * 10));
    }

    // Atualiza o valor das notas NP2 para PIM 5.0, 7.5 e 10.0
    private void atualizaNp2Padrao() {
        np250EditText.setText(Calculadora.calculaNp2(np1, 5.0));
        np275EditText.setText(Calculadora.calculaNp2(np1, 7.5));
        np2100EditText.setText(Calculadora.calculaNp2(np1, 10.0));
    }

    // Atualiza o valor da nota NP2 para PIM personalizado:
    private void atualizaNp2Personalizado() {
        np2EditText.setText(Calculadora.calculaNp2(np1, pim));
    }

    //Define o objeto ouviente de mudança de texto do np1EditText:
    private TextWatcher ouvinteNp1EditText = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // Este método deve ser sobrescrito, mas não é usado neste aplicativo.
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            try {
                np1 = Double.parseDouble(np1EditText.getText().toString());
            } catch (NumberFormatException e) {
                np1 = 0.0;
            }
            atualizaNp2Padrao();
            atualizaNp2Personalizado();
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // Este método deve ser sobrescrito, mas não é usado neste aplicativo.
        }
    };

    // Define o objeto ouvinte de mudança no pimSeekBar
    private SeekBar.OnSeekBarChangeListener ouvintePimSeekBar =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    pim = (double) pimSeekBar.getProgress() / 10;
                    pimEditText.setText(String.format("%.1f", pim));
                    atualizaNp2Personalizado();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    // Este método deve ser sobrescrito, mas não é usado neste aplicativo.
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    // Este método deve ser sobrescrito, mas não é usado neste aplicativo.
                }
            };

    // Este método é chamado quando o aplicativo é interrompido. Nele criamos nossa lógica
    // para armazenar as informações que devem ser recuperadas quando o aplicativo é reiniciado.
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putDouble(NP1, np1);
        outState.putDouble(PIM, pim);
    }

}
