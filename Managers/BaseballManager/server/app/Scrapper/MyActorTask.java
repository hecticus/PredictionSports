package Scrapper;

import akka.actor.ActorSystem;
import scala.concurrent.ExecutionContext;
import scala.concurrent.duration.Duration;

import javax.inject.Inject;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MyActorTask {

    private final ActorSystem actorSystem;
    private final ExecutionContext executionContext;
    private Scrapper scrapper;

    @Inject
    public MyActorTask(ActorSystem actorSystem, ExecutionContext executionContext) {
        this.actorSystem = actorSystem;
        this.executionContext = executionContext;

        this.initialize();
    }

    private void initialize() {
        scrapper = new Scrapper();
        this.actorSystem.scheduler().schedule(
                Duration.create(0, TimeUnit.SECONDS), // initialDelay
                Duration.create(15, TimeUnit.MINUTES),
                //
                () -> {
                    try {
                        scrapper.ScrapperDays();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                this.executionContext
        );
    }
}