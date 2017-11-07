package wikisearch;

public interface Responder {
    Response makeResponse(Request request, RequestContext context);
}
