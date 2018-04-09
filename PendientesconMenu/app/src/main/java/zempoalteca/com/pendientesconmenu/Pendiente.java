package zempoalteca.com.pendientesconmenu;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Gabriel on 08/04/18.
 */

public class Pendiente implements Serializable {

    private int imagenPendiente;
    private String titulo;
    private String descripcion;
    private Date fecha;
    private String categoria;
    private int diasRestantes;

    public Pendiente(int imagenPendiente, String titulo, String descripcion, Date fecha, String categoria, int diasRestantes) {
        this.imagenPendiente = imagenPendiente;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.categoria = categoria;
        this.diasRestantes = diasRestantes;
    }

    public int getImagenPendiente() {
        return imagenPendiente;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getDiasRestantes() {
        return diasRestantes;
    }
}
