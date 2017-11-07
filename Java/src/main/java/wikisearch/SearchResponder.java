package wikisearch;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class SearchResponder implements Responder {

    private Request request;
    private RequestContext context;

    @Override
    public Response makeResponse(Request request, RequestContext context) {
        this.request = request;
        this.context = context;

        List<WikiPage> matchingPages = new DepthFirstTraverser(context.rootPage)
                .traverse()
                .stream()
                .filter(this::traverse)
                .collect(toList());
        String text = "found term in pages:<ul>";
        for (WikiPage resultPage : matchingPages) {
            text += "<li>" + resultPage.getTitle() + "</li>";
        }
        text += "</ul>";

        return new Response(new WikiPage(title(), text));
    }

    String title() {
        return "Search Results";
    }

    boolean traverse(WikiPage page) {
        String searchText = this.request.data.get("search_text");
        return page.getText().contains(searchText);
    }
}
