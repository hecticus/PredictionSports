package scheduler;

import akka.actor.ActorSystem;
import scala.App;
import scala.concurrent.ExecutionContext;
import scala.concurrent.duration.Duration;
import services.appland.AppLandServicio;
import services.kraken_servicio.KrakenServicio;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

/**
 * @author Pedro Ocando on 11/27/18
 */
public class DisableCheckerTask {
    private final ActorSystem actorSystem;
    private final ExecutionContext executionContext;
    private KrakenServicio krakenServicio;
    private AppLandServicio applandServicio;


    @Inject
    public DisableCheckerTask(ActorSystem actorSystem, ExecutionContext executionContext, KrakenServicio krakenServicio, AppLandServicio applandServicio) {
        this.krakenServicio = krakenServicio;
        this.applandServicio = applandServicio;
        this.actorSystem = actorSystem;
        this.executionContext = executionContext;
        this.initialize();
    }

    private void initialize() {
        this.actorSystem.scheduler().scheduleOnce(
            Duration.create(0, TimeUnit.SECONDS),
            () -> Ignite(),
            this.executionContext
        );
    }

    public void Ignite() {
        while(true){
            try {
                applandServicio.makeUnsubscribeCall("HECTI_MOVIS_U_VE");
                applandServicio.makeUnsubscribeCall("HECTI_CIUDA_U_VE");
                Thread.sleep(86400000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
