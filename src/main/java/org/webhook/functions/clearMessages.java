package org.webhook.functions;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.io.*;

/**
 * Azure Functions with HTTP Trigger.
 */
public class clearMessages {
    /**
     * This function listens at endpoint "/api/showMessages". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/showMessages
     * 2. curl {your host}/api/showMessages?name=HTTP%20Query
     */
    @FunctionName("clearMessages")
    public HttpResponseMessage run(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.GET, HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS)
                    HttpRequestMessage<String> request,
                final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        // Parse query parameter

try{
        PrintWriter pw =new PrintWriter("c:\\home\\site\\webhook.html");


        pw.print("");
// other operations
        pw.close();
        } catch (IOException e) {
            context.getLogger().info("An error occurred.");
            context.getLogger().info(e.toString());
        }
        if (request.getHttpMethod().toString().equals("POST")){
            return request.createResponseBuilder(HttpStatus.OK).body("hola").build();
        }else {
            return request.createResponseBuilder(HttpStatus.OK).body("").build();
        }

    }
}
