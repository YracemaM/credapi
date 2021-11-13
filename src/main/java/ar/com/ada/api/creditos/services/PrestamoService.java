package ar.com.ada.api.creditos.services;

import java.math.BigDecimal;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.creditos.entities.Cliente;
import ar.com.ada.api.creditos.entities.Prestamo;
import ar.com.ada.api.creditos.entities.Prestamo.EstadoPrestamoEnum;
import ar.com.ada.api.creditos.repos.PrestamoRepository;

@Service
public class PrestamoService {

    @Autowired
    PrestamoRepository repo;

    @Autowired
    ClienteService clienteService;

    public Prestamo crearPrestamo(int clienteId, Date fecha, BigDecimal importe, int cuotas, Date fechaAlta, 
                                EstadoPrestamoEnum estado) {

        Prestamo prestamo = new Prestamo();

        Cliente cliente = clienteService.traerClientePorId(clienteId);
        prestamo.setCliente(cliente);
        prestamo.setFecha(fecha);
        prestamo.setImporte(importe);
        prestamo.setCuotas(cuotas);
        prestamo.setFechaAlta(new Date());
        prestamo.setEstadoId(EstadoPrestamoEnum.SOLICITADO);

        cliente.agregarPrestamo(prestamo);
        return repo.save(prestamo);
    }

    public List<Prestamo> traerPrestamos(int idCliente) {
                
        Cliente cliente = clienteService.traerClientePorId(idCliente);

        return cliente.getPrestamos();
    }

    public void actualizarPrestamo(Prestamo prestamo) {

        repo.save(prestamo);
    }

    public Prestamo traerPrestamoPorId(int id) {

        Optional<Prestamo> resultado = repo.findById(id);

        if(resultado.isPresent())
            return resultado.get();

        return null;
    }
    
}
