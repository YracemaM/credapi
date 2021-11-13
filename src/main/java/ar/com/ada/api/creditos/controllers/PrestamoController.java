package ar.com.ada.api.creditos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.creditos.entities.Prestamo;
import ar.com.ada.api.creditos.models.request.InfoEstadoPrestamo;
import ar.com.ada.api.creditos.models.request.InfoNuevoPrestamo;
import ar.com.ada.api.creditos.models.response.GenericResponse;
import ar.com.ada.api.creditos.services.ClienteService;
import ar.com.ada.api.creditos.services.PrestamoService;

@RestController
public class PrestamoController {

    @Autowired
    PrestamoService service;

    @Autowired
    ClienteService clienteService;

    @PostMapping("/prestamos")
    public ResponseEntity<GenericResponse> crearPrestamo(@RequestBody InfoNuevoPrestamo infoPrestamo) {

        GenericResponse respuesta = new GenericResponse();

        Prestamo prestamoNuevo = service.crearPrestamo(infoPrestamo.clienteId, infoPrestamo.fecha, infoPrestamo.importe, 
                                            infoPrestamo.cuotas, infoPrestamo.fechaAlta, infoPrestamo.estado);

        respuesta.isOk = true;
        respuesta.id = prestamoNuevo.getPrestamoId();
        respuesta.message = "El prestamo fue creado con exito";

        return ResponseEntity.ok(respuesta);   
    }

    @GetMapping("/prestamos/clientes/{idCliente}")
    public ResponseEntity<List<Prestamo>> traerPrestamosPorClienteId(@PathVariable int idCliente) {

        return ResponseEntity.ok(service.traerPrestamos(idCliente));
    }

    @PutMapping("/prestamos/{id}")
    public ResponseEntity<GenericResponse> actualizarPrestamo(@PathVariable int id, 
                                                                @RequestBody InfoEstadoPrestamo estadoPrestamo) {

        GenericResponse respuesta = new GenericResponse();

        Prestamo prestamo = service.traerPrestamoPorId(id);
        prestamo.setEstadoId(estadoPrestamo.estadoPrestamo);

        service.actualizarPrestamo(prestamo);

        respuesta.id = prestamo.getPrestamoId();
        respuesta.isOk = true;
        respuesta.message = "El estado del prestamo ha sido actualizado exitosamente";

        return ResponseEntity.ok(respuesta);
    }
}
