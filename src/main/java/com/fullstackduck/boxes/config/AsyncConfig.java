package com.fullstackduck.boxes.config;

import java.lang.reflect.Method;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer{
	
	@Bean(name = "asyncTaskExecutor")
    public ThreadPoolTaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // Defina o tamanho do pool de threads
        executor.setMaxPoolSize(10); // Defina o tamanho máximo do pool de threads
        executor.setThreadNamePrefix("AsyncTask-"); // Prefixo do nome das threads
        executor.initialize();
        return executor;
    }
	
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new AsyncUncaughtExceptionHandler() {
			
			@Override
			public void handleUncaughtException(Throwable ex, Method method, Object... params) {
				System.out.println("Erro: " + ex.getMessage());
				System.out.println("Método: " + method.getName());
			}
		};
	}
}
