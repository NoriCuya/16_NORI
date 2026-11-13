package vallegrande.edu.pe.misistema.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import vallegrande.edu.pe.misistema.model.Servicio;
import vallegrande.edu.pe.misistema.model.ServicioDAO;
import vallegrande.edu.pe.misistema.view.ServicioView;

public class ServicioController {

    private final ServicioView view;
    private final ServicioDAO dao;
    private final ObservableList<Servicio> datos = FXCollections.observableArrayList();
    private final FilteredList<Servicio> filtrados = new FilteredList<>(datos, s -> true);

    public ServicioController(ServicioView view, ServicioDAO dao) {
        this.view = view;
        this.dao = dao;

        SortedList<Servicio> ordenados = new SortedList<>(filtrados);
        ordenados.comparatorProperty().bind(view.getTabla().comparatorProperty());
        view.getTabla().setItems(ordenados);

        view.getTabla().getSelectionModel().selectedItemProperty().addListener((obs, old, s) -> {
            if (s != null) view.mostrarServicio(s);
            else view.setSeleccionActiva(false);
        });

        view.getTxtBuscar().textProperty().addListener((obs, old, texto) -> filtrar(texto));

        view.getBtnRegistrar().setOnAction(e -> registrar());
        view.getBtnActualizar().setOnAction(e -> actualizar());
        view.getBtnEliminar().setOnAction(e -> eliminar());
        view.getBtnLimpiar().setOnAction(e -> view.limpiarFormulario());

        cargarDatos();
    }

    private void filtrar(String texto) {
        String q = texto == null ? "" : texto.trim().toLowerCase();
        filtrados.setPredicate(s -> q.isEmpty()
                || s.getNombreServicio().toLowerCase().contains(q)
                || s.getEspecialista().toLowerCase().contains(q));
    }

    private void cargarDatos() {
        try {
            datos.setAll(dao.listar());
            actualizarEstadisticas();
        } catch (Exception ex) {
            view.mostrarError("No se pudo consultar la base de datos:\n" + ex.getMessage());
        }
    }

    private void actualizarEstadisticas() {
        long activos = datos.stream().filter(s -> "Activo".equals(s.getEstado())).count();
        double promedio = datos.stream().mapToDouble(Servicio::getPrecio).average().orElse(0);
        view.actualizarEstadisticas(datos.size(), activos, promedio);
    }

    private Servicio leerFormulario(int id) {
        String nombre = view.getTxtNombre().getText().trim();
        String especialista = view.getTxtEspecialista().getText().trim();
        if (nombre.isEmpty() || especialista.isEmpty()) {
            throw new IllegalArgumentException("Nombre y especialista son obligatorios.");
        }
        int duracion = Integer.parseInt(view.getTxtDuracion().getText().trim());
        double precio = Double.parseDouble(view.getTxtPrecio().getText().trim().replace(',', '.'));
        if (duracion <= 0) throw new IllegalArgumentException("La duración debe ser mayor a 0 minutos.");
        if (precio < 0) throw new IllegalArgumentException("El precio no puede ser negativo.");
        return new Servicio(id, nombre, view.getTxtDescripcion().getText().trim(),
                duracion, precio, especialista, view.getCmbEstado().getValue());
    }

    private void registrar() {
        try {
            dao.insertar(leerFormulario(0));
            cargarDatos();
            view.limpiarFormulario();
            view.mostrarInfo("Servicio registrado correctamente.");
        } catch (NumberFormatException ex) {
            view.mostrarError("Duración y precio deben ser números válidos.");
        } catch (Exception ex) {
            view.mostrarError(ex.getMessage());
        }
    }

    private void actualizar() {
        if (view.getTxtId().getText().isBlank()) {
            view.mostrarError("Selecciona un servicio de la tabla.");
            return;
        }
        try {
            dao.actualizar(leerFormulario(Integer.parseInt(view.getTxtId().getText())));
            cargarDatos();
            view.limpiarFormulario();
            view.mostrarInfo("Servicio actualizado correctamente.");
        } catch (NumberFormatException ex) {
            view.mostrarError("Duración y precio deben ser números válidos.");
        } catch (Exception ex) {
            view.mostrarError(ex.getMessage());
        }
    }

    private void eliminar() {
        if (view.getTxtId().getText().isBlank()) {
            view.mostrarError("Selecciona un servicio de la tabla.");
            return;
        }
        if (view.confirmar("¿Eliminar este servicio?")) {
            try {
                dao.eliminar(Integer.parseInt(view.getTxtId().getText()));
                cargarDatos();
                view.limpiarFormulario();
                view.mostrarInfo("Servicio eliminado correctamente.");
            } catch (Exception ex) {
                view.mostrarError(ex.getMessage());
            }
        }
    }
}