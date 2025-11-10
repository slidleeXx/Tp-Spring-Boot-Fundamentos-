package com.utn.tareas.service;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class MensajeDevService implements MensajeService{

    @Override
    public void mostrarBienvenida() {
        System.out.println("[Profile = DEV] Bienvenido al entorno de desarrollo. Logs detallados habilitados.");
    }

    @Override
    public void mostrarDespedida() {
        System.out.println("[Profile =DEV] Finalizando ejecucion. ¡Buen trabajo en el entorno de desarrollo nos vemos luego!");
    }

}
