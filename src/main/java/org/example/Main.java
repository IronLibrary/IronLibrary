package org.example;
import org.example.controller.MenuController;
public class Main {
    public static void main(String[] args) {
        //cargado los datos de la base de datos

        MenuController menuController = new MenuController();
        menuController.start();

    }
}