package com.example.apps_agenda.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apps_agenda.R;
import com.example.apps_agenda.dao.PersonagemDAO;
import com.example.apps_agenda.model.Personagem;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class FormularioPersonagemActivity extends AppCompatActivity {

    //Chaves de Título
    public static final String NOVO_PERSONAGEM_TITLE = "Adicionar Personagem";
    public static final String EDITAR_PERSONAGEM_TITLE = "Editar Personagem";
    public static final String CHAVE_PERSONAGEM = "personagem";

    private EditText nome;
    private EditText altura;
    private EditText nascimento;
    private EditText telefone;
    private EditText endereco;
    private EditText cep;
    private EditText rg;
    private EditText genero;
    private final PersonagemDAO dao = new PersonagemDAO();
    private Personagem personagem;


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.activity_formulario_personagem_menu_salvar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        int itemID = item.getItemId();
        if (itemID == R.id.activity_formulario_personagem_menu_salvar)
        {
            FinalizaFormulario();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);

        //Encontra os campos pelos id
        EncontraCampos();
        //Função ao clicar no botão de salvar
        OnClickSalvar();
        //Pega os dados do personagem
        PegaDados();
    }

    /*@Override
    protected void onResume()
    {
        super.onResume();
    }*/

    private void PegaDados()
    {
        //Recebe os dados
        Intent dados = getIntent();

        //Se for um personagem
        if (dados.hasExtra(CHAVE_PERSONAGEM))
        {
            //Seta o título para edição de personagem
            setTitle(EDITAR_PERSONAGEM_TITLE);
            //Recebe os dados do personagem
            personagem = (Personagem) dados.getSerializableExtra(CHAVE_PERSONAGEM);
            //Preenche os campos correspondente ao personagem clicado
            PreencheCampos();
        }

        //Caso o personagem não possua chave
        else
        {
            //Seta o título para adição de personagem
            setTitle(NOVO_PERSONAGEM_TITLE);
            //Cria um personagem
            personagem = new Personagem();
        }
    }

    //Função para preencher os campos ao clicar em um personagem para editar
    private void PreencheCampos() {
        nome.setText(personagem.getSaveNome());
        altura.setText(personagem.getSaveAltura());
        nascimento.setText(personagem.getSaveNascimento());
        telefone.setText(personagem.getSaveTelefone());
        endereco.setText(personagem.getSaveEndereco());
        cep.setText(personagem.getSaveCEP());
        rg.setText(personagem.getSaveRG());
        genero.setText(personagem.getSaveGenero());
    }

    //Função para o botão de salvar
    private void OnClickSalvar()
    {
        //Encontra o botão
        Button botaoSalvar = findViewById(R.id.buttonSalvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Chama a função para o formulario
                FinalizaFormulario();
            }
        });
    }

    private void FinalizaFormulario()
    {
        //Preenche os campos
        PreenchePersonagem();
        //Se o ID for válido (de um personagem que já existe)
        if (personagem.ValidaID())
        {
            //Edita o personagem
            dao.Editar(personagem);
            //Fecha a Activity
            //finish();
        }
        else
        {
            //Salva um personagem
            dao.Salvar(personagem);
        }
        //Fecha a Activity no final
        finish();
    }

    private void EncontraCampos()
    {
        //Encontra os campos do Nome, Altura e Nascimento
        nome = findViewById(R.id.textNome);
        altura = findViewById(R.id.textAltura);
        nascimento = findViewById(R.id.textNascimento);
        telefone = findViewById(R.id.textTelefone);
        endereco = findViewById(R.id.textEndereco);
        cep = findViewById(R.id.textCEP);
        rg = findViewById(R.id.textRG);
        genero = findViewById(R.id.textGenero);

        SimpleMaskFormatter smfAltura = new SimpleMaskFormatter("N,NN");
        MaskTextWatcher mtwAltura = new MaskTextWatcher(altura, smfAltura);
        altura.addTextChangedListener(mtwAltura);

        SimpleMaskFormatter smfNascimento = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtwNascimento = new MaskTextWatcher(nascimento, smfNascimento);
        nascimento.addTextChangedListener(mtwNascimento);

        SimpleMaskFormatter smfTelefone = new SimpleMaskFormatter("(NN)NNNNN-NNNN");
        MaskTextWatcher mtwTelefone = new MaskTextWatcher(telefone, smfTelefone);
        telefone.addTextChangedListener(mtwTelefone);

        SimpleMaskFormatter smfCEP = new SimpleMaskFormatter("NNNNN-NNN");
        MaskTextWatcher mtwCEP = new MaskTextWatcher(cep, smfCEP);
        cep.addTextChangedListener(mtwCEP);

        SimpleMaskFormatter smfRG = new SimpleMaskFormatter("NN.NNN.NNN-N");
        MaskTextWatcher mtwRG = new MaskTextWatcher(rg, smfRG);
        rg.addTextChangedListener(mtwRG);

    }

    private void PreenchePersonagem()
    {
        //Converte os textos para String
        String saveNome = nome.getText().toString();
        String saveAltura = altura.getText().toString();
        String saveNascimento = nascimento.getText().toString();
        String saveTelefone = telefone.getText().toString();
        String saveEndereco = endereco.getText().toString();
        String saveCEP = cep.getText().toString();
        String saveRG = rg.getText().toString();
        String saveGenero = genero.getText().toString();

        //Seta os campos
        personagem.setSaveNome(saveNome);
        personagem.setSaveAltura(saveAltura);
        personagem.setSaveNascimento(saveNascimento);
        personagem.setSaveTelefone(saveTelefone);
        personagem.setSaveEndereco(saveEndereco);
        personagem.setSaveCEP(saveCEP);
        personagem.setSaveRG(saveRG);
        personagem.setSaveGenero(saveGenero);
    }

}