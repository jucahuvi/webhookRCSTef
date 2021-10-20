package org.webhook.functions;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.specialized.BlockBlobClient;
import com.azure.storage.common.StorageSharedKeyCredential;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * Azure Functions with HTTP Trigger.
 */
public class SaveFile {
    /**
     * This function listens at endpoint "/api/HttpExample". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpExample
     * 2. curl "{your host}/api/HttpExample?name=HTTP%20Query"
     */
    @FunctionName("saveFile")
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
        String storageConnectionString ="DefaultEndpointsProtocol=https;AccountName=webhookrcstelf;AccountKey=j44Tigz746rR4XxMdNmXljYuLBuZhFSdGE7HFriZA9YehqbGGBKv2cCaQgKXWeHgJvQRuQ/OS681sH2/IMMtqQ==;EndpointSuffix=core.windows.net";
        try
        {
            /*
             * From the Azure portal, get your Storage account's name and account key.
             */
            String accountName = "webhookrcstelf";
            String accountKey = "j44Tigz746rR4XxMdNmXljYuLBuZhFSdGE7HFriZA9YehqbGGBKv2cCaQgKXWeHgJvQRuQ/OS681sH2/IMMtqQ==";

            /*
             * Use your Storage account's name and key to create a credential object; this is used to access your account.
             */
            StorageSharedKeyCredential credential = new StorageSharedKeyCredential(accountName, accountKey);

            /*
             * From the Azure portal, get your Storage account blob service URL endpoint.
             * The URL typically looks like this:
             */
            String endpoint = String.format(Locale.ROOT, "https://webhookrcstelf.blob.core.windows.net/", accountName);

            /*
             * Create a BlobServiceClient object that wraps the service endpoint, credential and a request pipeline.
             */
            BlobServiceClient storageClient = new BlobServiceClientBuilder().endpoint(endpoint).credential(credential).buildClient();

            BlobContainerClient blobContainerClient = storageClient.getBlobContainerClient("webhookcontainer" + System.currentTimeMillis());

            /*
             * Create a container in Storage blob account.
             */
            blobContainerClient.create();


            BlockBlobClient blobClient = blobContainerClient.getBlobClient("webhook_notifications.txt").getBlockBlobClient();

            String data = "Hello world!";
            InputStream dataStream = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));

            /*
             * Create the blob with string (plain text) content.
             */
            blobClient.upload(dataStream, data.length());

            dataStream.close();


        }
        catch (Exception e)
        {
            // Output the stack trace.
            e.printStackTrace();
        }
        if (request.getHttpMethod().toString().equals("POST")){
            return request.createResponseBuilder(HttpStatus.OK).body("hola").build();
        }else {
            return request.createResponseBuilder(HttpStatus.OK).body("").build();
        }
    }
}
