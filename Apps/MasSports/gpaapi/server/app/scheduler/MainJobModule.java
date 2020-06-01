package scheduler;
import com.google.inject.AbstractModule;

public class MainJobModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DisableCheckerTask.class).asEagerSingleton();
    }
}