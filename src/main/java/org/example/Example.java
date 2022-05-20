package org.example;

import com.slack.api.Slack;
import com.slack.api.methods.response.api.ApiTestResponse;

public class Example {
    public static void main(String[] args) throws Exception {
        Slack slack = Slack.getInstance();
        ApiTestResponse response = slack.methods().apiTest(r -> r.foo("bar"));
        System.out.println(response);
    }
}