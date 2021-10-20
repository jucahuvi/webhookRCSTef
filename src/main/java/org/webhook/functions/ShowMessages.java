package org.webhook.functions;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Azure Functions with HTTP Trigger.
 */
public class ShowMessages {
    /**
     * This function listens at endpoint "/api/showMessages". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/showMessages
     * 2. curl {your host}/api/showMessages?name=HTTP%20Query
     */
    @FunctionName("showMessages")
    public HttpResponseMessage run(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.GET, HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS)
                    HttpRequestMessage<String> request,
                final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        // Parse query parameter




        StringBuilder sb = new StringBuilder();
        String body;
        try {
            BufferedReader br = new BufferedReader(new FileReader("c:\\home\\site\\webhook.html"));
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {
                sb.append(strCurrentLine);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        body = sb.toString();
        context.getLogger().info(body);
        HttpResponseMessage.Builder builder = request.createResponseBuilder(HttpStatus.OK).header("Content-Type", "text/html");
        if (request.getHttpMethod().toString().equals("POST")){
            return builder.status(HttpStatus.OK).body(body).build();
        }else {
            return builder.status(HttpStatus.OK).body(body).build();
        }


    }
}
