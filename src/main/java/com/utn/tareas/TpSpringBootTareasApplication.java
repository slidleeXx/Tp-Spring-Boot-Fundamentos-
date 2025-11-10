package com.utn.tareas;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.service.MensajeService;
import com.utn.tareas.service.TareaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TpSpringBootTareasApplication  implements CommandLineRunner {

    private final TareaService tareaService;
    private final MensajeService mensajeService;

    public TpSpringBootTareasApplication(TareaService tareaService, MensajeService mensajeService) {
        this.tareaService = tareaService;
        this.mensajeService = mensajeService;
    }

	public static void main(String[] args) {
		SpringApplication.run(TpSpringBootTareasApplication.class, args);}

    @Override
    public void run(String... args) {

        // (1) Bienvenida del entorno
        mensajeService.mostrarBienvenida();

        // (2) Propiedades actuales
        tareaService.imprimirConfiguracion();

        // (3) Tareas iniciales
        System.out.println("\n--Tareas iniciales --");
        tareaService.listarTodas().forEach(System.out::println);

        // (4) Agregar una nueva tarea
        System.out.println("\nAgregando una nuevas tareas...");
        tareaService.agregarTarea("Generar Test ", Prioridad.MEDIA);
        tareaService.agregarTarea("Compartir Resultados Tests generados", Prioridad.BAJA);
        tareaService.agregarTarea("Realizar cambios al Proyecto", Prioridad.ALTA);
        tareaService.agregarTarea("Generar Documentacion de Cambios", Prioridad.ALTA);
        tareaService.agregarTarea("Actualizar proyecto en Nube", Prioridad.MEDIA);

        // si Profile = dev lanzamos Excepcion excedemos max Tareas .
//        tareaService.agregarTarea("Nueva tarea generada por el sistema PROF -> DEV ", Prioridad.MEDIA);

        // (5) Listar tareas pendientes
        System.out.println("\n--Tareas pendientes --");
        tareaService.listarPendientes().forEach(System.out::println);

        // (6) Marcar una tarea como completada
        System.out.println("\nMarcando la tarea con ID =1 como completada....");
        tareaService.completarTarea(1L);
        System.out.println("\nMarcando la tarea con ID =2 como completada....");
        tareaService.completarTarea(2L);
        System.out.println("\nMarcando la tarea con ID =3 como completada....");
        tareaService.completarTarea(3L);


        // (7) Mostrar estadisticas
        System.out.println("\n--Estadisticas --");
        System.out.println(tareaService.obtenerEstadisticas());

        // (8) Listar tareas completadas
        System.out.println("\n--Tareas completadas--");
        tareaService.listarCompletadas().forEach(System.out::println);

        // (9) Mensaje final del sistema
        mensajeService.mostrarDespedida();
    }

}
