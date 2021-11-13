package ar.com.ada.api.creditos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.creditos.entities.Cancelacion;
import ar.com.ada.api.creditos.entities.Prestamo;
import ar.com.ada.api.creditos.repos.CancelacionRepository;

@Service
public class CancelacionService {

    @Autowired
    CancelacionRepository repo;

    @Autowired
    PrestamoService prestamoService;

    public void crear(Cancelacion cancelacion) {

        Prestamo prestamo = new Prestamo();

        repo.save(cancelacion);

        prestamo.agregarCancelacion(cancelacion);
    }

    public List<Cancelacion> traerCancelacionesPorPrestamoId(int prestamoId) {
        
        Prestamo prestamo = prestamoService.traerPrestamoPorId(prestamoId);

        return prestamo.getCancelaciones();
    }
}    
