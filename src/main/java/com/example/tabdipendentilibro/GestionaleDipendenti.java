package com.example.tabdipendentilibro;

import com.example.tabdipendentilibro.ModificaDipendenteController;
import com.example.tabdipendentilibro.Dipendente;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.Random;
import java.util.Date;



public class GestionaleDipendenti extends Application {

    /* Lista dei dipendenti*/
    private static final ObservableList<Dipendente> dipendenti = FXCollections.observableArrayList();

    /* Popup stage (e relativo controller) per modifica dipendente */
    private static Stage popupStage;
    private static ModificaDipendenteController popupController;

    /* Costruttore */
    public GestionaleDipendenti(){
        // dati per testing
        Random random = new Random(new Date().getTime());
        dipendenti.add(new Dipendente(random.nextInt(), "Donald", "Duck", "DNDCK123456789"));
        dipendenti.add(new Dipendente(random.nextInt(), "Mickey", "Mouse", "MKMS123456789"));
        dipendenti.add(new Dipendente(random.nextInt(), "Uncle", "Scrooge", "NCLSC123456789"));
        dipendenti.add(new Dipendente(random.nextInt(), "Gyro", "Gearloose", "GRGRS123456789"));
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Preparazione stage principale
        Parent root = FXMLLoader.load(getClass().getResource("tabellaDipendenti.fxml"));
        primaryStage.setTitle("Gestionale dipendenti");
        primaryStage.setScene(new Scene(root));

        // Preparazione stage popup per modifica dipendente
        popupStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modificaDipendente.fxml"));
        Parent popupRoot = fxmlLoader.load();
        popupController = fxmlLoader.getController(); // salva riferimento a controller del popup
        popupStage.setScene(new Scene(popupRoot));
        popupStage.setTitle("Modifica dipendente");
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initOwner(primaryStage);

        primaryStage.show(); // mostra stage principale
    }

    public static void mostraPopupModifica(Dipendente dipendente) {
        popupController.setDipendente(dipendente);
        popupStage.showAndWait();
    }

    public static ObservableList<Dipendente> getDipendenti() {
        return dipendenti;
    }

    public static void aggiungiDipendente(Dipendente dip) {
        dipendenti.add(dip);
    }

    public static void rimuoviDipendente(Dipendente dipendente) {
        dipendenti.remove(dipendente);
    }

    public static void modificaDipendente(Dipendente dipendenteAggiornato) {
        for (Dipendente dipendente : dipendenti) {
            if (dipendente.getId() == dipendenteAggiornato.getId()) {
                dipendente = dipendenteAggiornato;
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
