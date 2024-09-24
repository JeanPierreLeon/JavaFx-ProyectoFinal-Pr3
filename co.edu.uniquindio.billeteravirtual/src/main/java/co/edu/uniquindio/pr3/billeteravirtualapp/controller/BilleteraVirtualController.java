package co.edu.uniquindio.pr3.billeteravirtualapp.controller;

public class BilleteraVirtualController {
    ModelFactoryController modelFactoryController;

    public BilleteraVirtualController() {
        System.out.println("Llamando al singleton desde BilleteraVirtualServiceController");
        modelFactoryController = ModelFactoryController.getInstance();

    }
}
