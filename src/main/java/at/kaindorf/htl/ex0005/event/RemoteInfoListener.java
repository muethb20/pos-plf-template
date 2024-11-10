package at.kaindorf.htl.ex0005.event;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Slf4j
@Component
public class RemoteInfoListener {
    @EventListener
    public void handleDeleteStudent(PayloadApplicationEvent<RemoteInfo> payloadApplicationEvent) {
        log.info("--> Student is being deleted!");
        log.info(payloadApplicationEvent.getPayload().remoteHost());
        log.info(payloadApplicationEvent.getPayload().userAgent());
        log.info("-----------------------------");

        Path root = Path.of(System.getProperty("user.home"));
        log.info("Free Disk Space: " + root.toFile().getFreeSpace());
    }
}
