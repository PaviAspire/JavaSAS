
package com.application.apachecamel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "app.enable.route",havingValue ="filemv")
public class FileMoverRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:C://Users//pavithra.srinivasan//IdeaProjects//ApacheCamel//source").log("${body}").
               to("file:C://Users//pavithra.srinivasan//IdeaProjects//ApacheCamel//destination");
    }
}

