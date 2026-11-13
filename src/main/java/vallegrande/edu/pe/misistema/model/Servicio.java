package vallegrande.edu.pe.misistema.model;

public class Servicio {
    private final int idServicio;
    private final String nombreServicio;
    private final String descripcion;
    private final int duracionMinutos;
    private final double precio;
    private final String especialista;
    private final String estado;

    public Servicio(int idServicio, String nombreServicio, String descripcion,
                    int duracionMinutos, double precio, String especialista, String estado) {
        this.idServicio = idServicio;
        this.nombreServicio = nombreServicio;
        this.descripcion = descripcion;
        this.duracionMinutos = duracionMinutos;
        this.precio = precio;
        this.especialista = especialista;
        this.estado = estado;
    }

    public int getIdServicio() { return idServicio; }
    public String getNombreServicio() { return nombreServicio; }
    public String getDescripcion() { return descripcion; }
    public int getDuracionMinutos() { return duracionMinutos; }
    public double getPrecio() { return precio; }
    public String getEspecialista() { return especialista; }
    public String getEstado() { return estado; }
}