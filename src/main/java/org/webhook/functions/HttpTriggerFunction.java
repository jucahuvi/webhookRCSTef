package org.webhook.functions;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.io.*;

/**
 * Azure Functions with HTTP Trigger.
 */
public class HttpTriggerFunction {
    /**
     * This function listens at endpoint "/api/HttpExample". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpExample
     * 2. curl "{your host}/api/HttpExample?name=HTTP%20Query"
     */
    @FunctionName("webhook")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.GET, HttpMethod.POST},
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<String> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        // Parse query parameter


        context.getLogger().info("method: " + request.getHttpMethod().toString());
        // String storageConnectionString ="DefaultEndpointsProtocol=https;AccountName=webhookrcstelf;AccountKey=j44Tigz746rR4XxMdNmXljYuLBuZhFSdGE7HFriZA9YehqbGGBKv2cCaQgKXWeHgJvQRuQ/OS681sH2/IMMtqQ==;EndpointSuffix=core.windows.net";
        StringBuilder sb = new StringBuilder();
        sb.append(request.getBody()).append("<p>&nbsp;</p>");
        File file= new File("c:\\home\\site\\webhook.html");
        try {
            Writer output;
            output = new BufferedWriter(new FileWriter(file,true));  //clears file every time

            //html

            String body= "<h3>" + sb.toString() + "</h3>";

            output.append(body);
            output.append("<p>&nbsp;</p>");
            output.close();
            context.getLogger().info("Successfully wrote to the file.");
        } catch (IOException e) {
            context.getLogger().info("An error occurred.");
            context.getLogger().info (e.toString());
        }
        if (request.getHttpMethod().toString().equals("POST")){
            return request.createResponseBuilder(HttpStatus.OK).body("hola").build();
        }else {
            return request.createResponseBuilder(HttpStatus.OK).body("").build();
        }
    }
}
