package com.example.tabdipendentilibro;

import com.example.tabdipendentilibro.GestionaleDipendenti;
import com.example.tabdipendentilibro.Dipendente;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ModificaDipendenteController {

    /* Proprietà */
    private Dipendente dipendente;
    private StringProperty messaggioValidazione =
            new SimpleStringProperty();

    /* Riferimenti FXML */
    @FXML private TextField campoNome;
    @FXML private TextField campoCognome;
    @FXML private TextField campoCodiceFiscale;

    @FXML
    public void start() {

    }

    /* Event handler salvataggio modifiche */
    public void handleSalvaModifiche(MouseEvent mouseEvent) {
        String nome = campoNome.textProperty().get();
        String cognome = campoCognome.textProperty().get();
        String codiceFiscale = campoCodiceFiscale.textProperty().get();
        if (validaForm(nome, cognome, codiceFiscale)) {
            dipendente.setNome(nome);
            dipendente.setCognome(cognome);
            dipendente.setCodiceFiscale(codiceFiscale);
            GestionaleDipendenti.modificaDipendente(dipendente);
            setMessaggioValidazione("Modifiche salvate con successo");
        }
    }


    private boolean validaForm(String nome, String cognome, String codiceFiscale) {
        boolean valido = true;
        String msg = "";
        if (nome == null || nome.isEmpty()) {
            msg += "Inserisci nome\n";
            valido = false;
        }
        if (cognome == null || cognome.isEmpty()) {
            msg += "Inserisci cognome\n";
            valido = false;
        }
        if (codiceFiscale == null || codiceFiscale.isEmpty()) {
            msg += "Inserisci codice fiscale\n";
            valido = false;
        }
        setMessaggioValidazione(msg);
        return valido;
    }

    /* Getters e setters */
    public void setDipendente(Dipendente dipendente) {
        this.dipendente = dipendente;
        // riempimento campi con le info del dipendente
        campoNome.textProperty().setValue(dipendente.getNome());
        campoCognome.textProperty().set(dipendente.getCognome());
        campoCodiceFiscale.textProperty().set(dipendente.getCodiceFiscale());
    }

    public String getMessaggioValidazione() {
        return messaggioValidazione.get();
    }
    public StringProperty messaggioValidazioneProperty() {
        return messaggioValidazione;
    }
    public void setMessaggioValidazione(String messaggioValidazione) {
        this.messaggioValidazione.set(messaggioValidazione);
    }
}
