package ar.com.ada.api.creditos.models.request;

import java.math.BigDecimal;
import java.util.Date;

import ar.com.ada.api.creditos.entities.Prestamo.EstadoPrestamoEnum;

public class InfoNuevoPrestamo {

    public int clienteId;
    public Date fecha;
    public BigDecimal importe;
    public int cuotas;
    public Date fechaAlta;
    public EstadoPrestamoEnum estado;
}
