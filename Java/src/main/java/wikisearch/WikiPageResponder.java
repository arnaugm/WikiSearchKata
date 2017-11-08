package wikisearch;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class WikiPageResponder implements Responder {

    public Response makeResponse(Request request, RequestContext context) {
        Map<String, WikiPage> allPages = new DepthFirstTraverser(context.rootPage)
                .traverse()
                .stream()
                .collect(toMap(WikiPage::getUri, p -> p));

        WikiPage page = allPages.get(request.uri);
        if (page != null) {
            return new Response("200", page);
        } else {
            return new Response("404", new WikiPage("404"));
        }
    }
}
