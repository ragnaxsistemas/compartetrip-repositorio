package com.ragnax.compartetriprepositorio.configuration;

import java.util.Arrays;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
@EnableCaching
@ComponentScan("com.ragnax.compartetriprepositorio.servicio")
public class CachingConfig {

	@Bean
    @Primary
    public CacheManager cacheArrastrametrip() {
        final SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
        		new ConcurrentMapCache("buscarTipoViajeRecomendado"),
        		new ConcurrentMapCache("listarTodoTipoViajeRecomendado") ,
        		new ConcurrentMapCache("buscarTipoVehiculoViaje") ,
        		new ConcurrentMapCache("listarTodoTipoVehiculoViaje"),
        		new ConcurrentMapCache("buscarTipoViaje") ,
        		new ConcurrentMapCache("listarTodoTipoViaje") ,
        		new ConcurrentMapCache("buscarTipoStatusViaje"),
        		new ConcurrentMapCache("listarTodoTipoStatusViaje"),
        		new ConcurrentMapCache("buscarClasificacionPasajero"),
        		new ConcurrentMapCache("listarTodoClasificacionPasajero"),
        		new ConcurrentMapCache("buscarRolPasajero") ,
        		new ConcurrentMapCache("listarTodoRolPasajero") ,
        		new ConcurrentMapCache("buscarDescuentoxCodigoDescuento"),
        		new ConcurrentMapCache("buscarPasajeroArrastramexIdUsuario")));
        return cacheManager;
    }

}
