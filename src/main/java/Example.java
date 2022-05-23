import com.slack.api.Slack;
import com.slack.api.methods.response.api.ApiTestResponse;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import io.github.cdimascio.dotenv.Dotenv;

public class Example {
    public static void main(String[] args) throws Exception {
        Slack slack = Slack.getInstance();
        //ApiTestResponse response = slack.methods().apiTest(r -> r.foo("bar"));

        // Load an env variable
        // If the token is a bot token, it starts with `xoxb-` while if it's a user token, it starts with `xoxp-`
        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("SLACK_TOKEN");
        //String token = System.getenv("SLACK_TOKEN");
        System.out.println(token);

        // Initialize an API Methods client with the given token
        MethodsClient methods = slack.methods(token);

        // Build a request object
        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel("#random") // Use a channel ID `C1234567` is preferrable
                .text("success with .env file!")
                .build();

        // Get a response as a Java object
        ChatPostMessageResponse response = methods.chatPostMessage(request);
        System.out.println(response);
    }
}