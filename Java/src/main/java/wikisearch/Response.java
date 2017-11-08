package wikisearch;

public class Response {
    public final String httpCode;
    public final WikiPage page;

    public Response() {
        this(null);
    }

    public Response(WikiPage page) {
        this("200", page);
    }

    public Response(String httpCode, WikiPage page) {
        this.httpCode = httpCode;
        this.page = page;
    }
}
