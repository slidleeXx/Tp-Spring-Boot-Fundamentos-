package com.utn.tareas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class Tarea {
    private Long id;
    private String descripcion;
    private boolean completada;
    private Prioridad prioridad ;

    @Override
    public String toString() {
        return "Tareas{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", completada=" + completada +
                ", prioridad=" + prioridad +
                '}';
    }


}
