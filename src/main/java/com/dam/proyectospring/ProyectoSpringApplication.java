package com.dam.proyectospring;

import com.dam.proyectospring.modelos.Piloto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ProyectoSpringApplication {
    private static WebClient client = WebClient.create("http://localhost:8080");
    public static void main(String[] args) {
        SpringApplication.run(ProyectoSpringApplication.class, args);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean quit = false;
        while (!quit){
            System.out.println("\n");
            try{
                System.out.println("""
                        ****************************************
                        Seleccione una opción:
                        1.- Mostrar todos los pilotos
                        2.- Mostrar un piloto dado un id
                        3.- Crear un piloto con nuevos datos
                        4.- Actualizar un piloto dado un id
                        5.- Borrar un piloto dado un id
                        6.- Salir
                        ****************************************""");
                int option = Integer.parseInt(reader.readLine());
                switch (option){
                    case 1:
                        // Mostrar todos los pilotos
                        List<Piloto> pilotoFlux = client.get()
                                .uri("pilotos")
                                .retrieve()
                                .bodyToFlux(Piloto.class)
                                .collectList()
                                .block();
                        if (pilotoFlux != null){
                            pilotoFlux.forEach(System.out::println);
                        }else{
                            System.err.println("No pilots found");
                        }
                        break;
                    case 2:
                        // Mostrar un piloto dado un id
                        System.out.print("Introduzca id del piloto: ");
                        String showOneId = reader.readLine();
                        Piloto pilotoMono = client.get()
                                .uri("piloto/{id}", showOneId)
                                .retrieve()
                                .bodyToMono(Piloto.class)
                                .block();
                        if (pilotoMono != null){
                            System.out.println(pilotoMono);
                        }else{
                            System.out.println("No se ha encontrado el piloto con id: " + showOneId);
                        }
                        break;
                    case 3:
                        // Crear un piloto con nuevos datos
                        System.out.println("Introduzca los datos del piloto nuevo");
                        System.out.println("================================");
                        System.out.println("Id: ");
                        String newPilotoId = reader.readLine();
                        System.out.println("Nombre: ");
                        String newPilotoNombre = reader.readLine();
                        System.out.println("Abreviatura: ");
                        String newPilotoAbreviatura = reader.readLine();
                        System.out.println("Número: ");
                        int newPilotoNumero = Integer.parseInt(reader.readLine());
                        System.out.println("Equipo: ");
                        String newPilotoEquipo = reader.readLine();
                        System.out.println("País: ");
                        String newPilotoPais = reader.readLine();
                        System.out.println("Fecha de nacimiento (YYYY-MM-DD): ");
                        LocalDate newPilotoFechaNacimiento = LocalDate.parse(reader.readLine());
                        Piloto newPiloto = new Piloto(newPilotoId, newPilotoNombre, newPilotoAbreviatura,
                                newPilotoNumero, newPilotoEquipo, newPilotoPais, newPilotoFechaNacimiento);

                        Piloto pilotoMono2 = client.post()
                                .uri("pilotos")
                                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                .body(BodyInserters.fromValue(newPiloto))
                                .retrieve()
                                .bodyToMono(Piloto.class)
                                .block();
                        if (pilotoMono2 != null){
                            System.out.println("Piloto añadido: " + pilotoMono2.getDriver());
                        }else{
                            System.out.println("No se ha podido añadir el piloto");
                        }
                        break;
                    case 4:
                        // Actualizar un piloto dado un id
                        System.out.print("Introduzca id del piloto: ");
                        String searchId = reader.readLine();
                        Piloto searchUpdatePiloto = client.get().uri("piloto/{id}", searchId).
                                retrieve().
                                bodyToMono(Piloto.class).
                                block();
                        if (searchUpdatePiloto != null) {
                            System.out.println("Introduzca los datos del piloto");
                            System.out.println("================================");
                            System.out.println("Id: ");
                            String updatePilotoId = reader.readLine();
                            System.out.println("Nombre: ");
                            String updatePilotoNombre = reader.readLine();
                            System.out.println("Abreviatura: ");
                            String updatePilotoAbreviatura = reader.readLine();
                            System.out.println("Número: ");
                            int updatePilotoNumero = Integer.parseInt(reader.readLine());
                            System.out.println("Equipo: ");
                            String updatePilotoEquipo = reader.readLine();
                            System.out.println("País: ");
                            String updatePilotoPais = reader.readLine();
                            System.out.println("Fecha de nacimiento (YYYY-MM-DD): ");
                            LocalDate updatePilotoFechaNacimiento = LocalDate.parse(reader.readLine());
                            Piloto updatePiloto = new Piloto(updatePilotoId, updatePilotoNombre, updatePilotoAbreviatura,
                                    updatePilotoNumero, updatePilotoEquipo, updatePilotoPais, updatePilotoFechaNacimiento);
                            Piloto pilotoMono3 = client.put()
                                    .uri("pilotos")
                                    .body(BodyInserters.fromValue(updatePiloto))
                                    .retrieve()
                                    .bodyToMono(Piloto.class)
                                    .block();
                            if (pilotoMono3 != null){
                                System.out.println("Piloto actualizado: " + pilotoMono3.getDriver());
                            }else{
                                System.out.println("No se ha podido actualizar el piloto");
                            }
                        }else{
                            System.out.println("No se ha encontrado el piloto con id: " + searchId);
                        }
                        break;
                    case 5:
                        // Borrar un piloto dado un id
                        System.out.print("Introduzca id del piloto: ");
                        String deleteId = reader.readLine();
                        Piloto pilotoDelete = client.get().uri("piloto/", deleteId).retrieve().bodyToMono(Piloto.class).block();
                        if (pilotoDelete != null){
                            client.delete()
                                    .uri("pilotos", BodyInserters.fromValue(pilotoDelete))
                                    .retrieve();
                            System.out.println("Piloto eliminado");
                        }else{
                            System.out.println("No se ha encontrado el piloto con id: " + deleteId);
                        }
                        break;
                    case 6:
                        // Salir
                        quit = true;
                        break;
                    default:
                        System.out.println("Opción no válida");
                }
            }catch (IOException IOEx){
                System.err.println("Error de entrada/salida");

            }catch (NumberFormatException nfEx){
                System.err.println("No se ha introducido un número");
            }
        }
    }
}