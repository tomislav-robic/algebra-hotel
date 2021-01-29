package robic.tomislav.algebrahotel.algebrahotel.jobs;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    @Bean
    public JobDetail roomTypePrintJobDetail() {
        return JobBuilder
                .newJob(RoomTypePrintJob.class)
                .withIdentity("roomTypePrintJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger studentPrintTrigger() {

        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5).repeatForever();

        return TriggerBuilder
                .newTrigger()
                .forJob(roomTypePrintJobDetail())
                .withIdentity("roomTypePrintTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }

}
