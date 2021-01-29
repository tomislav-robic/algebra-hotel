package robic.tomislav.algebrahotel.algebrahotel.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import robic.tomislav.algebrahotel.algebrahotel.domain.RoomType;
import robic.tomislav.algebrahotel.algebrahotel.repository.JpaRoomTypeRepository;


public class RoomTypePrintJob extends QuartzJobBean {

    private Logger log = LoggerFactory.getLogger(RoomTypePrintJob.class);

    private final JpaRoomTypeRepository jpaRoomTypeRepository;

    public RoomTypePrintJob(JpaRoomTypeRepository jpaRoomTypeRepository) {
        this.jpaRoomTypeRepository = jpaRoomTypeRepository;
    }


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Iterable<RoomType> roomTypes = jpaRoomTypeRepository.findAll();

        if(roomTypes.iterator().hasNext()) {
            log.info("These are the room types currently entered in the app");
            roomTypes.forEach(
                    it -> log.info(it.getName())
            );
        } else {
            log.info("There are not room types in the app");
        }
    }
}
