package ar.com.ada.api.creditos.models.request;

import java.math.BigDecimal;
import java.util.Date;

public class InfoCancelacionNueva {

    public int prestamoId;
    public BigDecimal importeCuota;
    public int numeroCuota;
    public Date fechaCancelacion;
}
