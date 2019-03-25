package controllers;

import java.io.File;
import com.google.inject.Inject;
import com.google.inject.Provider;

import play.Application;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

public class Static extends Controller {

    @Inject
    Provider<Application> app;

    public Result getFile(String path){
        File file = app.get().getFile(path);
        if(file.exists()){
            return ok(file);
        }else{
            return Results.notFound();
        }
    }
}