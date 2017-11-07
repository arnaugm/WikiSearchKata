package wikisearch;

public class WikiApp {

    private WikiPage rootPage;

    public WikiApp(WikiPage rootPage) {
        this.rootPage = rootPage;
    }

    public Response handleRequest(Request request) {
        Responder responder;
        if (request.type.equals("GET")) {
            responder = new WikiPageResponder();
        } else if (request.type.equals("POST")) {
            responder = new SearchResponder();
        } else {
            throw new RuntimeException("Method not supported");
        }
        return responder.makeResponse(request, new RequestContext(this.rootPage));
    }
}
