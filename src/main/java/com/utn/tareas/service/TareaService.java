package com.utn.tareas.service;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TareaService {
    private final TareaRepository tareaRepository;
    //properties
    @Value("${app.nombre}")
    private String nombreAplicacion;

    @Value("${app.max-tareas}")
    private int maxTareas;

    @Value("${app.mostrar-estadisticas}")
    private boolean mostrarEstadisticas;

    //Injeccion por COnstructor
    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }


    // Agregar nueva tarea (validacion del limite maximo)

    public Tarea agregarTarea(String descripcion, Prioridad prioridad) {

        if (tareaRepository.findAll().size() >= maxTareas) {
            throw new IllegalStateException("No se pueden agregar mas tareas. Limite = " + maxTareas);
        }

        Tarea nueva = new Tarea(null, descripcion, false, prioridad);
        return tareaRepository.save(nueva);
    }

    // Listar todas las tareas
    public List<Tarea> listarTodas() {
        return tareaRepository.findAll();
    }

    // Tareas pendientes
    public List<Tarea> listarPendientes() {
        return tareaRepository.findAll().stream()
                .filter(t -> !t.isCompletada())
                .collect(Collectors.toList());
    }

    // Tareas completadas
    public List<Tarea> listarCompletadas() {
        return tareaRepository.findAll().stream()
                .filter(Tarea::isCompletada)
                .collect(Collectors.toList());
    }

    // Marcar tarea como completada
    public boolean completarTarea(Long id) {
        return tareaRepository.findById(id)
                .map(tarea -> {
                    tarea.setCompletada(true);
                    tareaRepository.save(tarea);
                    return true;
                })
                .orElse(false);
    }

    // Estadísticas segun config
    public String obtenerEstadisticas() {

        if (mostrarEstadisticas) {
            List<Tarea> todas = tareaRepository.findAll();
            long total = todas.size();
            long completadas = todas.stream().filter(Tarea::isCompletada).count();
            long pendientes =total -completadas;

            return """
                Estadisticas - Tareas :
                Aplicacion: %s
                Total: %d
                Completadas: %d
                Pendientes: %d
                """.formatted(nombreAplicacion, total, completadas, pendientes);
        }
        return "Las estadisticas están deshabilitadas por configuracion.";
    }

    // Mostrar configuraciones actuales
    public void imprimirConfiguracion() {
        System.out.println("""
            Configuracion de la App =
            Nombre: %s
            Máximo-Tareas: %d
            Mostrar Estadisticas: %b
            """.formatted(nombreAplicacion, maxTareas, mostrarEstadisticas));
    }


}