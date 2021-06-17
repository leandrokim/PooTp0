package main.java.main;

import main.java.controllers.OrdenesYDocumentosController;
import main.java.models.Documento;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {

        OrdenesYDocumentosController magia = new OrdenesYDocumentosController();
        magia.ordenesDePagoEmitidas();
    }

}

