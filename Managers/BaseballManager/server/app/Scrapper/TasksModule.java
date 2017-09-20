package Scrapper;


import com.google.inject.AbstractModule;

public class TasksModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MyActorTask.class).asEagerSingleton();
    }
}