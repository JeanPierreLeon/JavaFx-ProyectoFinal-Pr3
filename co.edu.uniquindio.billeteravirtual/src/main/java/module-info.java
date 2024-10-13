/**
 *
 */
module co.edu.uniquindio.pr3.billeteravirtualapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.mapstruct;
    requires javafx.graphics;
    requires java.logging;
    requires java.desktop;


    opens co.edu.uniquindio.pr3.billeteravirtualapp to javafx.fxml;
    exports co.edu.uniquindio.pr3.billeteravirtualapp;
    exports co.edu.uniquindio.pr3.billeteravirtualapp.viewController;
    opens co.edu.uniquindio.pr3.billeteravirtualapp.viewController to javafx.fxml;
    exports co.edu.uniquindio.pr3.billeteravirtualapp.controller;
    exports co.edu.uniquindio.pr3.billeteravirtualapp.mapping.dto;
    exports co.edu.uniquindio.pr3.billeteravirtualapp.mapping.mappers;
    exports co.edu.uniquindio.pr3.billeteravirtualapp.model;
    exports co.edu.uniquindio.pr3.billeteravirtualapp.exceptions;
    opens co.edu.uniquindio.pr3.billeteravirtualapp.controller to javafx.fxml;


}