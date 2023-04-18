package com.example.tabdipendentilibro;

import com.example.tabdipendentilibro.GestionaleDipendenti;
import com.example.tabdipendentilibro.Dipendente;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import java.util.Date;
import java.util.Random;

/* Controller della home */
public class TabellaDipendentiController {

    /* Proprietà */
    private StringProperty messaggioValidazione = new SimpleStringProperty();

    /* Riferimenti FXML tabella */
    @FXML private TableView<Dipendente> tabellaDipendenti;
    @FXML private TableColumn<Dipendente, String> colonnaNome;
    @FXML private TableColumn<Dipendente, String> colonnaCognome;
    @FXML private TableColumn<Dipendente, String> colonnaCodiceFiscale;
    @FXML private TableColumn<Dipendente, String> colonnaEtà;

    /* Riferimenti FXML form aggiunta dipendente */
    @FXML private TextField campoNome;
    @FXML private TextField campoCognome;
    @FXML private TextField campoCodiceFiscale;
    @FXML private TextField campoEtà;

    @FXML
    private void initialize() {
        // Impostazioni dati della tabella dei dipendenti
        tabellaDipendenti.setItems(GestionaleDipendenti.getDipendenti());
        colonnaNome.setCellValueFactory(new PropertyValueFactory<Dipendente, String>("nome"));
        colonnaCognome.setCellValueFactory(new PropertyValueFactory<Dipendente, String>("cognome"));
        colonnaCodiceFiscale.setCellValueFactory(new PropertyValueFactory<Dipendente, String>("codiceFiscale"));
        colonnaEtà.setCellValueFactory(new PropertyValueFactory<Dipendente, String>("età"));
    }

    /* Event handler aggiunta dipendente */
    public void handleNuovoDipendente(MouseEvent mouseEvent) {
        String nome = campoNome.textProperty().get();
        String cognome = campoCognome.textProperty().get();
        String codiceFiscale = campoCodiceFiscale.textProperty().get();
        String età = campoEtà.textProperty().get();
        if (validaForm(nome, cognome, codiceFiscale, età)) {
            Random random = new Random(new Date().getTime());
            int idNuovoDipendente = random.nextInt();
            Dipendente dip = new Dipendente(idNuovoDipendente, nome, cognome, codiceFiscale, età);
            GestionaleDipendenti.aggiungiDipendente(dip);
            setMessaggioValidazione("Nuovo dipendente aggiunto!");
            campoNome.clear();
            campoCognome.clear();
            campoCodiceFiscale.clear();
            campoEtà.clear();
        }
    }

    public void handleModificaDipendente(MouseEvent mouseEvent) {
        Dipendente dipendenteSelezionato = tabellaDipendenti.getSelectionModel().getSelectedItem();
        if (dipendenteSelezionato == null) return;
        GestionaleDipendenti.mostraPopupModifica(dipendenteSelezionato);
    }

    public void handleEliminaDipendente(MouseEvent mouseEvent) {
        Dipendente dipendenteSelezionato = tabellaDipendenti.getSelectionModel().getSelectedItem();
        if (dipendenteSelezionato == null) return;
        GestionaleDipendenti.rimuoviDipendente(dipendenteSelezionato);
    }

    private boolean validaForm(String nome, String cognome, String codiceFiscale, String età) {
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
        if (età == null || età.isEmpty()) {
            msg += "Inserisci età\n";
            valido = false;
        }
        setMessaggioValidazione(msg);
        return valido;
    }

    /* Getters e setters */
    public String getMessaggioValidazione() { return messaggioValidazione.get(); }
    public StringProperty messaggioValidazioneProperty() { return messaggioValidazione; }
    public void setMessaggioValidazione(String msg) { this.messaggioValidazione.set(msg); }
}
