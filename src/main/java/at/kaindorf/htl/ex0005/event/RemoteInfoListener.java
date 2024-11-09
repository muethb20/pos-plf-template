package at.kaindorf.htl.ex0005.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
@Slf4j
public class RemoteInfoListener {
    private final ApplicationContext applicationContext;
    private final Environment environment;
    public RemoteInfoListener(ApplicationContext applicationContext, Environment environment) {
        this.applicationContext = applicationContext;
        this.environment = environment;
    }

    @EventListener
    public void onEvent(PayloadApplicationEvent<RemoteInfo> event) {
        RemoteInfo ri = event.getPayload();
        log.info("Client IP Address = " + ri.remoteAddr());
        log.info("Remote User = " + ri.remoteUser());
        log.info("User Agent = " + ri.userAgent());

        log.info(""+environment.containsProperty("java.vm.version"));

        Path root = Path.of(System.getProperty("user.home"));
        log.info("Free Disk Space = "+root.toFile().getFreeSpace());
    }
}
