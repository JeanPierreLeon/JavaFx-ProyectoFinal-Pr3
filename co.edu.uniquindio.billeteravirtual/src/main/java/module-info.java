module co.edu.uniquindio.pr3.billeteravirtualapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.mapstruct;
    requires javafx.graphics;



    opens co.edu.uniquindio.pr3.billeteravirtualapp to javafx.fxml;
    exports co.edu.uniquindio.pr3.billeteravirtualapp;

}