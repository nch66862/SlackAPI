import com.slack.api.Slack;
import com.slack.api.methods.response.api.ApiTestResponse;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import io.github.cdimascio.dotenv.Dotenv;

import static com.slack.api.model.block.Blocks.*;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;
import static com.slack.api.model.block.composition.BlockCompositions.plainText;
import static com.slack.api.model.block.element.BlockElements.asElements;
import static com.slack.api.model.block.element.BlockElements.button;

public class Example {
    public static void main(String[] args) throws Exception {
        Slack slack = Slack.getInstance();
        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("SLACK_TOKEN");
        System.out.println(token);

        // Initialize an API Methods client with the given token
        MethodsClient methods = slack.methods(token);

        // Build a request object
        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel("#random") // Use a channel ID `C1234567` is preferrable
                .blocks(asBlocks(
                        section(section -> section.text(markdownText("*Please select a restaurant:*"))),
                        divider(),
                        actions(actions -> actions
                                .elements(asElements(
                                        button(b -> b.text(plainText(pt -> pt.emoji(true).text("Farmhouse"))).value("v1")),
                                        button(b -> b.text(plainText(pt -> pt.emoji(true).text("Kin Khao"))).value("v2"))
                                ))
                        )
                ))
                .build();

        // Get a response as a Java object
        ChatPostMessageResponse response = methods.chatPostMessage(request);
        System.out.println(response);
    }
}