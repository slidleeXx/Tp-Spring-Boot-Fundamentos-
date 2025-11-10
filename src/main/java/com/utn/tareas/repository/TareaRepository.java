package com.utn.tareas.repository;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TareaRepository {
    //repositorio es un recurso compartido usamos -> colección thread-safe
    private final List<Tarea> tareas = new CopyOnWriteArrayList<>();
    // valores to Id -> autoincrementable AtomicLong
    private final AtomicLong secuenciaId = new AtomicLong(1);

    public TareaRepository() {
        // Inicialización con datos de ejemplo (3–5 tareas)
        tareas.add(new Tarea(secuenciaId.getAndIncrement(), "Configurar proyecto", true, Prioridad.MEDIA));
        tareas.add(new Tarea(secuenciaId.getAndIncrement(), "Definir modelo y repo", false, Prioridad.ALTA));
        tareas.add(new Tarea(secuenciaId.getAndIncrement(), "Escribir README inicial", false, Prioridad.BAJA));
        tareas.add(new Tarea(secuenciaId.getAndIncrement(), "Probar arranque", true, Prioridad.MEDIA));
        tareas.add(new Tarea(secuenciaId.getAndIncrement(), "Planificar servicio", false, Prioridad.ALTA));
    }

    // Guardar una tarea id aut-> null
    public Tarea save(Tarea tarea) {
        if (tarea.getId() == null) {
            tarea.setId(secuenciaId.getAndIncrement());
            tareas.add(tarea);
        } else {
            // actualización simpl: reemplazar si existe
            deleteById(tarea.getId());
            tareas.add(tarea);
        }
        return tarea;
    }

    // Obtener todas las tareas
    public List<Tarea> findAll() {
        return Collections.unmodifiableList(tareas);
    }

    // Buscar por ID ( retornamos un objeto Optional)
    public Optional<Tarea> findById(Long id) {
        return tareas.stream()
                .filter(t -> Objects.equals(t.getId(), id))
                .findFirst();
    }

    // Eliminar por ID
    public void deleteById(Long id) {
        tareas.removeIf(t -> Objects.equals(t.getId(), id));
    }
}