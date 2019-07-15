package com.revature.effective.nintotwelve.multithreading;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
public class TaskScheduler implements SchedulingConfigurer, CommandLineRunner {

	List<CronTask> cronTasks;
	
	public static void main(String... args) {
		SpringApplication.run(TaskScheduler.class, args);
	}
	
	
	@Override
	@Valid
	public void run(String... args) throws Exception {
		CronTask task = this.createCronTask(new Runnable() {
			@Override
			public void run() {
				System.out.println(LocalDateTime.now());
			}
		}, "1/10 * * * * *");
		
		ScheduledTaskRegistrar taskRegistrar = new ScheduledTaskRegistrar();
		taskRegistrar.addCronTask(task);
		configureTasks(taskRegistrar);
		Thread.sleep(21);
		
		taskRegistrar.destroy();
		taskRegistrar = null;
		
		ScheduledTaskRegistrar taskRegistrar2 = new ScheduledTaskRegistrar();
		taskRegistrar2.addCronTask(task);
		configureTasks(taskRegistrar2);
		
	}
	
	@Validated()

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.afterPropertiesSet();
	}
	
	public CronTask createCronTask(Runnable action, String expression) {
		return new CronTask(action, new CronTrigger(expression));
	}
}
