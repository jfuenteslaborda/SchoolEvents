package com.schoolevents.schoolevents_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchooleventsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchooleventsApiApplication.class, args);
        System.out.println("""
               \s
                                                  Desarrollador: José Fuentes Laborda
                ███████╗ ██████╗██╗  ██╗ ██████╗  ██████╗ ██╗     ███████╗██╗   ██╗███████╗███╗   ██╗████████╗███████╗
                ██╔════╝██╔════╝██║  ██║██╔═══██╗██╔═══██╗██║     ██╔════╝██║   ██║██╔════╝████╗  ██║╚══██╔══╝██╔════╝
                ███████╗██║     ███████║██║   ██║██║   ██║██║     █████╗  ██║   ██║█████╗  ██╔██╗ ██║   ██║   ███████╗
                ╚════██║██║     ██╔══██║██║   ██║██║   ██║██║     ██╔══╝  ╚██╗ ██╔╝██╔══╝  ██║╚██╗██║   ██║   ╚════██║
                ███████║╚██████╗██║  ██║╚██████╔╝╚██████╔╝███████╗███████╗ ╚████╔╝ ███████╗██║ ╚████║   ██║   ███████║
                ╚══════╝ ╚═════╝╚═╝  ╚═╝ ╚═════╝  ╚═════╝ ╚══════╝╚══════╝  ╚═══╝  ╚══════╝╚═╝  ╚═══╝   ╚═╝   ╚══════╝
                                                        \s
                                                         \s
                            Swagger-ui: http://localhost:8080/swagger-ui.html
               \s
               \s""");
	}

}
