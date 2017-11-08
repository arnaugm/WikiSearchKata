package wikisearch;

import java.util.HashMap;
import java.util.Map;

public class Request {

    public final String type;
    public final String uri;
    public final Map<String, String> data;

    public Request(String type, String uri) {
        this(type, uri, new HashMap<>());
    }

    public Request(String type, String uri, Map<String, String> data) {
        this.type = type;
        this.uri = uri;
        this.data = data;
    }

}
