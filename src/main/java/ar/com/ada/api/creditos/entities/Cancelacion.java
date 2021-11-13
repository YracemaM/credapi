package ar.com.ada.api.creditos.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "cancelacion")
public class Cancelacion {

    public Cancelacion() {

    }

    public Cancelacion(BigDecimal importe, int cuota, Date fechaCancelacion) {

        this.importe = importe;
        this.cuota = cuota;
        this.fechaCancelacion = fechaCancelacion;    
    }

    @Id
    @Column(name = "cancelacion_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "prestamo_id", referencedColumnName = "prestamo_id")
    private Prestamo prestamo;

    @Column(name = "fecha_cancelacion")
    private Date fechaCancelacion;

    private BigDecimal importe;

    private int cuota;
 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
        this.prestamo.agregarCancelacion(this);
    }

    public Date getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(Date fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public int getCuota() {
        return cuota;
    }

    public void setCuota(int cuota) {
        this.cuota = cuota;
    }
    
}
