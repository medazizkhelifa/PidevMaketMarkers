package tn.esprit.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//@EnableAspectJAutoProxy
//@EnableScheduling
@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
//	@Bean
//	public RestTemplate getRestTemplate() {
	//	return new RestTemplate();
//	}
/*try {
		JobDetail job1 = JobBuilder.newJob(Job1.class)
				.withIdentity("job1", "group1").build();

		Trigger trigger1 = TriggerBuilder.newTrigger()
				.withIdentity("cronTrigger1", "group1")
				.withSchedule(CronScheduleBuilder.cronSchedule("0/30 * * * * ?"))
				.build();

		Scheduler scheduler1 = new StdSchedulerFactory().getScheduler();
		scheduler1.start();
		scheduler1.scheduleJob(job1, trigger1);



		Thread.sleep(100000);

		scheduler1.shutdown();
		//	scheduler2.shutdown();

	} catch (Exception e) {
		e.printStackTrace();
	}
}

*/

}

