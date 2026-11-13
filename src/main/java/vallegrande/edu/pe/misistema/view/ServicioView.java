
package vallegrande.edu.pe.misistema.view;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.util.Duration;
import vallegrande.edu.pe.misistema.model.Servicio;

public class ServicioView {

    private final VBox root = new VBox(16);
    private final TableView<Servicio> tabla = new TableView<>();

    private final TextField txtId = new TextField();
    private final TextField txtNombre = new TextField();
    private final TextField txtDescripcion = new TextField();
    private final TextField txtDuracion = new TextField();
    private final TextField txtPrecio = new TextField();
    private final TextField txtEspecialista = new TextField();

    private final ComboBox<String> cmbEstado =
            new ComboBox<>(FXCollections.observableArrayList("Activo", "Inactivo"));

    private final TextField txtBuscar = new TextField();

    private final Label lblTotal = new Label("0");
    private final Label lblActivos = new Label("0");
    private final Label lblPromedio = new Label("S/ 0.00");

    private final Button btnRegistrar = new Button("✚ Registrar");
    private final Button btnActualizar = new Button("✎ Actualizar");
    private final Button btnEliminar = new Button("✖ Eliminar");
    private final Button btnLimpiar = new Button("↻ Limpiar");

    public ServicioView() {

        root.getStyleClass().add("root-pane");
        root.setPadding(new Insets(0));

        // Contenido principal con desplazamiento
        VBox contenido = new VBox(18);
        contenido.getStyleClass().add("content-pane");

        contenido.getChildren().addAll(
                crearEncabezado(),
                crearEstadisticas(),
                crearServiciosDestacados(),
                crearFormulario(),
                crearTabla()
        );

        ScrollPane scroll = new ScrollPane(contenido);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scroll.setPannable(true);

        root.getChildren().add(scroll);
        VBox.setVgrow(scroll, Priority.ALWAYS);

        setSeleccionActiva(false);

        // Animaciones de entrada
        animarEntrada(contenido.getChildren().get(0), 0);
        animarEntrada(contenido.getChildren().get(1), 120);
        animarEntrada(contenido.getChildren().get(2), 240);
        animarEntrada(contenido.getChildren().get(3), 360);
        animarEntrada(contenido.getChildren().get(4), 480);
    }

    // =========================================
    // ENCABEZADO
    // =========================================

    private HBox crearEncabezado() {

        Label titulo = new Label("Salón de Belleza Glamour");
        titulo.getStyleClass().add("titulo");

        Label subtitulo = new Label(
                "✨ Tu belleza, nuestro arte y pasión"
        );
        subtitulo.getStyleClass().add("subtitulo");

        VBox textos = new VBox(5, titulo, subtitulo);
        textos.getStyleClass().add("header");

        Label insignia = new Label("💗 BELLEZA & ESTILO");
        insignia.getStyleClass().add("header-badge");

        Region espacio = new Region();
        HBox.setHgrow(espacio, Priority.ALWAYS);

        HBox encabezado = new HBox(
                10, textos, espacio, insignia
        );

        encabezado.setAlignment(Pos.CENTER_LEFT);

        return encabezado;
    }

    // =========================================
    // ESTADISTICAS
    // =========================================

    private HBox crearEstadisticas() {

        HBox caja = new HBox(15,
                tarjetaEstadistica("💇 Servicios registrados", lblTotal),
                tarjetaEstadistica("💖 Servicios activos", lblActivos),
                tarjetaEstadistica("💰 Precio promedio", lblPromedio)
        );

        for (Node nodo : caja.getChildren()) {
            HBox.setHgrow(nodo, Priority.ALWAYS);
        }

        return caja;
    }

    private VBox tarjetaEstadistica(String texto, Label valor) {

        valor.getStyleClass().add("stat-valor");

        Label titulo = new Label(texto);
        titulo.getStyleClass().add("stat-texto");

        VBox tarjeta = new VBox(7, valor, titulo);

        tarjeta.getStyleClass().add("stat-card");
        tarjeta.setMaxWidth(Double.MAX_VALUE);

        return tarjeta;
    }

    // =========================================
    // TARJETAS DE BELLEZA
    // =========================================

    private VBox crearServiciosDestacados() {

        Label titulo = new Label("✨ Nuestros servicios destacados");
        titulo.getStyleClass().add("section-title");

        Label descripcion = new Label(
                "Descubre los tratamientos favoritos de nuestras clientas"
        );
        descripcion.getStyleClass().add("section-subtitle");

        VBox encabezado = new VBox(4, titulo, descripcion);

        HBox tarjetas = new HBox(14);

        tarjetas.getChildren().addAll(

                crearTarjetaBelleza(
                        "💇",
                        "Corte y peinado",
                        "Estilo moderno y personalizado",
                        "S/ 35.00",
                        "POPULAR"
                ),

                crearTarjetaBelleza(
                        "🎨",
                        "Tinte profesional",
                        "Color y brillo para tu cabello",
                        "S/ 90.00",
                        "FAVORITO"
                ),

                crearTarjetaBelleza(
                        "💅",
                        "Manicure spa",
                        "Cuidado y belleza para tus uñas",
                        "S/ 40.00",
                        "NUEVO"
                ),

                crearTarjetaBelleza(
                        "🧖",
                        "Tratamiento facial",
                        "Limpieza y cuidado de la piel",
                        "S/ 65.00",
                        "ESPECIAL"
                )
        );

        for (Node tarjeta : tarjetas.getChildren()) {
            HBox.setHgrow(tarjeta, Priority.ALWAYS);
        }

        return new VBox(12, encabezado, tarjetas);
    }

    private VBox crearTarjetaBelleza(
            String icono,
            String nombre,
            String descripcion,
            String precio,
            String etiqueta
    ) {

        Label lblIcono = new Label(icono);
        lblIcono.getStyleClass().add("beauty-icon");

        Label lblNombre = new Label(nombre);
        lblNombre.getStyleClass().add("beauty-title");

        lblNombre.setWrapText(true);

        Label lblDescripcion = new Label(descripcion);
        lblDescripcion.getStyleClass().add("beauty-desc");

        lblDescripcion.setWrapText(true);

        Label lblPrecio = new Label(precio);
        lblPrecio.getStyleClass().add("beauty-price");

        Label lblEtiqueta = new Label("✦ " + etiqueta);
        lblEtiqueta.getStyleClass().add("beauty-tag");

        VBox tarjeta = new VBox(
                9,
                lblIcono,
                lblNombre,
                lblDescripcion,
                lblPrecio,
                lblEtiqueta
        );

        tarjeta.getStyleClass().add("beauty-card");
        tarjeta.setAlignment(Pos.CENTER_LEFT);
        tarjeta.setMaxWidth(Double.MAX_VALUE);
        tarjeta.setMinHeight(170);

        // Animación al pasar el mouse
        tarjeta.setOnMouseEntered(e -> {

            ScaleTransition animacion = new ScaleTransition(
                    Duration.millis(180), tarjeta
            );

            animacion.setToX(1.035);
            animacion.setToY(1.035);
            animacion.play();
        });

        tarjeta.setOnMouseExited(e -> {

            ScaleTransition animacion = new ScaleTransition(
                    Duration.millis(180), tarjeta
            );

            animacion.setToX(1.0);
            animacion.setToY(1.0);
            animacion.play();
        });

        return tarjeta;
    }

    // =========================================
    // FORMULARIO CRUD
    // =========================================

    private VBox crearFormulario() {

        Label titulo = new Label("💗 Gestión de servicios");
        titulo.getStyleClass().add("section-title");

        txtId.setPromptText("Automático");
        txtId.setDisable(true);

        txtNombre.setPromptText("Ej: Corte y peinado");
        txtDescripcion.setPromptText("Describe el servicio");
        txtDuracion.setPromptText("Minutos");
        txtPrecio.setPromptText("0.00");
        txtEspecialista.setPromptText("Nombre del especialista");

        cmbEstado.setValue("Activo");
        cmbEstado.setMaxWidth(Double.MAX_VALUE);

        GridPane form = new GridPane();

        form.setHgap(14);
        form.setVgap(12);

        ColumnConstraints c0 = new ColumnConstraints();
        c0.setMinWidth(95);

        ColumnConstraints c1 = new ColumnConstraints();
        c1.setHgrow(Priority.ALWAYS);

        ColumnConstraints c2 = new ColumnConstraints();
        c2.setMinWidth(105);

        ColumnConstraints c3 = new ColumnConstraints();
        c3.setHgrow(Priority.ALWAYS);

        form.getColumnConstraints().addAll(c0, c1, c2, c3);

        txtId.setMaxWidth(Double.MAX_VALUE);
        txtNombre.setMaxWidth(Double.MAX_VALUE);
        txtDescripcion.setMaxWidth(Double.MAX_VALUE);
        txtDuracion.setMaxWidth(Double.MAX_VALUE);
        txtPrecio.setMaxWidth(Double.MAX_VALUE);
        txtEspecialista.setMaxWidth(Double.MAX_VALUE);

        form.addRow(
                0,
                new Label("ID:"),
                txtId,
                new Label("Nombre:"),
                txtNombre
        );

        form.addRow(
                1,
                new Label("Descripción:"),
                txtDescripcion,
                new Label("Duración (min):"),
                txtDuracion
        );

        form.addRow(
                2,
                new Label("Precio (S/):"),
                txtPrecio,
                new Label("Especialista:"),
                txtEspecialista
        );

        form.addRow(
                3,
                new Label("Estado:"),
                cmbEstado
        );

        btnRegistrar.getStyleClass().add("btn-principal");
        btnActualizar.getStyleClass().add("btn-secundario");
        btnEliminar.getStyleClass().add("btn-peligro");
        btnLimpiar.getStyleClass().add("btn-neutro");

        HBox botones = new HBox(
                10,
                btnRegistrar,
                btnActualizar,
                btnEliminar,
                btnLimpiar
        );

        botones.setAlignment(Pos.CENTER_LEFT);

        VBox card = new VBox(
                15,
                titulo,
                form,
                botones
        );

        card.getStyleClass().add("card");

        return card;
    }

    // =========================================
    // TABLA DE SERVICIOS
    // =========================================

    private VBox crearTabla() {

        Label titulo = new Label("📋 Lista de servicios");
        titulo.getStyleClass().add("section-title");

        txtBuscar.setPromptText(
                "🔍 Buscar por servicio o especialista..."
        );

        txtBuscar.getStyleClass().add("buscador");
        txtBuscar.setMaxWidth(Double.MAX_VALUE);

        // ID
        TableColumn<Servicio, Integer> colId =
                new TableColumn<>("ID");

        colId.setCellValueFactory(
                new PropertyValueFactory<>("idServicio")
        );

        colId.setPrefWidth(55);

        // SERVICIO
        TableColumn<Servicio, String> colNombre =
                new TableColumn<>("Servicio");

        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombreServicio")
        );

        colNombre.setPrefWidth(160);

        // DESCRIPCION
        TableColumn<Servicio, String> colDesc =
                new TableColumn<>("Descripción");

        colDesc.setCellValueFactory(
                new PropertyValueFactory<>("descripcion")
        );

        colDesc.setPrefWidth(220);

        // DURACION
        TableColumn<Servicio, Integer> colDur =
                new TableColumn<>("Duración");

        colDur.setCellValueFactory(
                new PropertyValueFactory<>("duracionMinutos")
        );

        colDur.setPrefWidth(90);

        colDur.setCellFactory(c -> new TableCell<>() {

            @Override
            protected void updateItem(Integer item, boolean empty) {

                super.updateItem(item, empty);

                setText(
                        empty || item == null
                                ? null
                                : item + " min"
                );
            }
        });

        // PRECIO
        TableColumn<Servicio, Double> colPrecio =
                new TableColumn<>("Precio");

        colPrecio.setCellValueFactory(
                new PropertyValueFactory<>("precio")
        );

        colPrecio.setPrefWidth(95);

        colPrecio.setCellFactory(c -> new TableCell<>() {

            @Override
            protected void updateItem(Double item, boolean empty) {

                super.updateItem(item, empty);

                setText(
                        empty || item == null
                                ? null
                                : String.format("S/ %.2f", item)
                );
            }
        });

        // ESPECIALISTA
        TableColumn<Servicio, String> colEsp =
                new TableColumn<>("Especialista");

        colEsp.setCellValueFactory(
                new PropertyValueFactory<>("especialista")
        );

        colEsp.setPrefWidth(130);

        // ESTADO
        TableColumn<Servicio, String> colEstado =
                new TableColumn<>("Estado");

        colEstado.setCellValueFactory(
                new PropertyValueFactory<>("estado")
        );

        colEstado.setPrefWidth(90);

        colEstado.setCellFactory(c -> new TableCell<>() {

            @Override
            protected void updateItem(String item, boolean empty) {

                super.updateItem(item, empty);

                getStyleClass().removeAll(
                        "estado-activo",
                        "estado-inactivo"
                );

                if (empty || item == null) {

                    setText(null);

                } else {

                    setText(item);

                    if ("Activo".equalsIgnoreCase(item)) {
                        getStyleClass().add("estado-activo");
                    } else {
                        getStyleClass().add("estado-inactivo");
                    }
                }
            }
        });

        tabla.getColumns().clear();

        tabla.getColumns().addAll(
                colId,
                colNombre,
                colDesc,
                colDur,
                colPrecio,
                colEsp,
                colEstado
        );

        tabla.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN
        );

        tabla.setPlaceholder(
                new Label("💗 Todavía no hay servicios registrados")
        );

        tabla.setPrefHeight(280);
        tabla.setMinHeight(220);

        VBox caja = new VBox(
                12,
                titulo,
                txtBuscar,
                tabla
        );

        VBox.setVgrow(tabla, Priority.ALWAYS);
        VBox.setVgrow(caja, Priority.ALWAYS);

        return caja;
    }

    // =========================================
    // ANIMACIONES DE ENTRADA
    // =========================================

    private void animarEntrada(Node nodo, int retraso) {

        nodo.setOpacity(0);
        nodo.setTranslateY(18);

        PauseTransition pausa = new PauseTransition(
                Duration.millis(retraso)
        );

        pausa.setOnFinished(e -> {

            FadeTransition aparecer = new FadeTransition(
                    Duration.millis(650), nodo
            );

            aparecer.setFromValue(0);
            aparecer.setToValue(1);

            TranslateTransition deslizar = new TranslateTransition(
                    Duration.millis(650), nodo
            );

            deslizar.setFromY(18);
            deslizar.setToY(0);

            aparecer.play();
            deslizar.play();
        });

        pausa.play();
    }

    // =========================================
    // MENSAJES
    // =========================================

    public void mostrarError(String msg) {

        Alert alerta = new Alert(Alert.AlertType.ERROR, msg);
        alerta.setTitle("Error");
        alerta.setHeaderText("Ha ocurrido un problema");
        alerta.showAndWait();
    }

    public void mostrarInfo(String msg) {

        Alert alerta = new Alert(Alert.AlertType.INFORMATION, msg);
        alerta.setTitle("Información");
        alerta.setHeaderText("Salón de Belleza Glamour");
        alerta.showAndWait();
    }

    public boolean confirmar(String msg) {

        Alert conf = new Alert(
                Alert.AlertType.CONFIRMATION,
                msg,
                ButtonType.YES,
                ButtonType.NO
        );

        conf.setTitle("Confirmar operación");
        conf.setHeaderText("¿Estás segura?");

        return conf.showAndWait().orElse(ButtonType.NO)
                == ButtonType.YES;
    }

    // =========================================
    // FORMULARIO
    // =========================================

    public void limpiarFormulario() {

        txtId.clear();
        txtNombre.clear();
        txtDescripcion.clear();
        txtDuracion.clear();
        txtPrecio.clear();
        txtEspecialista.clear();

        cmbEstado.setValue("Activo");

        tabla.getSelectionModel().clearSelection();

        setSeleccionActiva(false);
    }

    public void mostrarServicio(Servicio s) {

        txtId.setText(String.valueOf(s.getIdServicio()));
        txtNombre.setText(s.getNombreServicio());
        txtDescripcion.setText(s.getDescripcion());
        txtDuracion.setText(String.valueOf(s.getDuracionMinutos()));
        txtPrecio.setText(String.valueOf(s.getPrecio()));
        txtEspecialista.setText(s.getEspecialista());

        cmbEstado.setValue(s.getEstado());

        setSeleccionActiva(true);
    }

    public void setSeleccionActiva(boolean activa) {

        btnActualizar.setDisable(!activa);
        btnEliminar.setDisable(!activa);
        btnRegistrar.setDisable(activa);
    }

    // =========================================
    // ESTADISTICAS
    // =========================================

    public void actualizarEstadisticas(
            int total,
            long activos,
            double promedio
    ) {

        lblTotal.setText(String.valueOf(total));
        lblActivos.setText(String.valueOf(activos));

        lblPromedio.setText(
                String.format("S/ %.2f", promedio)
        );
    }

    // =========================================
    // GETTERS PARA EL CONTROLLER
    // =========================================

    public VBox getRoot() {
        return root;
    }

    public TableView<Servicio> getTabla() {
        return tabla;
    }

    public TextField getTxtId() {
        return txtId;
    }

    public TextField getTxtNombre() {
        return txtNombre;
    }

    public TextField getTxtDescripcion() {
        return txtDescripcion;
    }

    public TextField getTxtDuracion() {
        return txtDuracion;
    }

    public TextField getTxtPrecio() {
        return txtPrecio;
    }

    public TextField getTxtEspecialista() {
        return txtEspecialista;
    }

    public ComboBox<String> getCmbEstado() {
        return cmbEstado;
    }

    public TextField getTxtBuscar() {
        return txtBuscar;
    }

    public Button getBtnRegistrar() {
        return btnRegistrar;
    }

    public Button getBtnActualizar() {
        return btnActualizar;
    }

    public Button getBtnEliminar() {
        return btnEliminar;
    }

    public Button getBtnLimpiar() {
        return btnLimpiar;
    }
}
