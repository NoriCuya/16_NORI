
package vallegrande.edu.pe.misistema;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import vallegrande.edu.pe.misistema.controller.ServicioController;
import vallegrande.edu.pe.misistema.model.ServicioDAO;
import vallegrande.edu.pe.misistema.view.ServicioView;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        // Crear interfaz
        ServicioView view = new ServicioView();

        // Conectar controlador y base de datos
        ServicioDAO dao = new ServicioDAO();

        new ServicioController(view, dao);

        // Crear escena
        Scene scene = new Scene(
                view.getRoot(),
                1150,
                850
        );

        // Cargar estilos CSS
        URL css = Main.class.getResource(
                "/vallegrande/edu/pe/misistema/estilos.css"
        );

        if (css != null) {

            scene.getStylesheets().add(
                    css.toExternalForm()
            );

        } else {

            System.out.println(
                    "ERROR: No se encontró estilos.css"
            );
        }

        // Configuración de ventana
        stage.setTitle(
                "Salón de Belleza Glamour | Sistema de Gestión"
        );

        stage.setMinWidth(950);
        stage.setMinHeight(650);

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
