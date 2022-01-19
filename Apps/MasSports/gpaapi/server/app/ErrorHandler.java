import play.http.HttpErrorHandler;
import play.mvc.*;
import play.mvc.Http.*;
import services.mail_servicio.MailServicio;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ErrorHandler implements HttpErrorHandler {

    @Inject
    private MailServicio mailServicio;

    public CompletionStage<Result> onClientError(RequestHeader request, int statusCode, String message) {
        if(!message.contains("extapi/get-status/events/")) {
            mailServicio.sendMail("Error En CLIENT GPAAPI", message + statusCode + request.uri() + request.path());
        }
        return CompletableFuture.completedFuture(
                Results.status(statusCode, "A client error occurred: " + message)
        );
    }

    public CompletionStage<Result> onServerError(RequestHeader request, Throwable exception) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        mailServicio.sendMail("Error En SERVER GPAAPI", exception.getMessage() + " - " + sw);
        return CompletableFuture.completedFuture(
                Results.internalServerError("A server error occurred: " + exception.getMessage())
        );
    }
}