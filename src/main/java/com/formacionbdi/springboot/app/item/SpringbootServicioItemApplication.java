package com.formacionbdi.springboot.app.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * The clas Principal de la aplicación. La etiquest @EnableFeignClientes sirve
 * para abilitar los clientes Feing que esten habilitados en el proyecto y
 * permite inyectar estos clientes en los controladores u otros componentes de
 * Spring como los Service. RibbonClient sirve para el valanceo de carga,
 * permite habilitar varios puertos para el uso del service.
 * 
 */
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class SpringbootServicioItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioItemApplication.class, args);
	}

}
