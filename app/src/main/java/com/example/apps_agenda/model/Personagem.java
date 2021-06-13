
package com.example.apps_agenda.model;
import androidx.annotation.NonNull;
import java.io.Serializable;

public class Personagem implements Serializable
{
    private String saveNome;
    private String saveAltura;
    private String saveNascimento;
    private String saveTelefone;
    private String saveEndereco;
    private String saveCEP;
    private String saveRG;
    private String saveGenero;
    //ID para identificar qual é a posição do Personagem
    private int saveID = 0;

    public Personagem(String saveNome, String saveAltura, String saveNascimento, String saveTelefone, String saveEndereco, String saveCEP, String saveRG, String saveGenero)
    {
        //Construtor das váriaveis Nome, Altura e Data de Nascimento
        this.saveNome = saveNome;
        this.saveAltura = saveAltura;
        this.saveNascimento = saveNascimento;
        this.saveTelefone = saveTelefone;
        this.saveEndereco = saveEndereco;
        this.saveCEP = saveCEP;
        this.saveRG = saveRG;
        this.saveGenero = saveGenero;
    }

    public Personagem()
    {
        //Construtor vazio para não dar erro ao deixar os campos vazios
    }

    //Getters e Setters
    @NonNull
    @Override
    public String toString()
    {
        return saveNome;
    }

    public String getSaveNome()
    {
        return saveNome;
    }

    public void setSaveNome(String saveNome)
    {
        this.saveNome = saveNome;
    }

    public String getSaveAltura()
    {
        return saveAltura;
    }

    public void setSaveAltura(String saveAltura)
    {
        this.saveAltura = saveAltura;
    }

    public String getSaveNascimento()
    {
        return saveNascimento;
    }

    public void setSaveNascimento(String saveNascimento)
    {
        this.saveNascimento = saveNascimento;
    }

    public String getSaveTelefone()
    {
        return saveTelefone;
    }

    public void setSaveTelefone(String saveTelefone)
    {
        this.saveTelefone = saveTelefone;
    }

    public String getSaveEndereco()
    {
        return saveEndereco;
    }

    public void setSaveEndereco(String saveEndereco)
    {
        this.saveEndereco = saveEndereco;
    }

    public String getSaveCEP()
    {
        return saveCEP;
    }

    public void setSaveCEP(String saveCEP)
    {
        this.saveCEP = saveCEP;
    }

    public String getSaveRG()
    {
        return saveRG;
    }

    public void setSaveRG(String saveRG)
    {
        this.saveRG = saveRG;
    }

    public String getSaveGenero()
    {
        return saveGenero;
    }

    public void setSaveGenero(String saveGenero)
    {
        this.saveGenero = saveGenero;
    }

    public void setSaveID(int saveID)
    {
        this.saveID = saveID;
    }

    public int getSaveID()
    {
        return saveID;
    }

    //Retorna "true" caso o ID for maior que 0, o que significa que há algum dado salvo
    public boolean ValidaID()
    {
        return saveID > 0;
    }
}
