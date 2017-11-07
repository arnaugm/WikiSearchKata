package wikisearch;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestResponder {

    @Test
    public void testWikiPageResponderOk() {
        Responder responder = new WikiPageResponder();
        Request request = new Request("GET", "/");
        WikiPage rootPage = new WikiPage("FrontPage", "", new HashSet<>(), "/");
        RequestContext context = new RequestContext(rootPage);

        Response response = responder.makeResponse(request, context);

        assertThat(response.httpCode, is("200"));
    }

    @Test
    public void testWikiPageResponderKo() {
        Responder responder = new WikiPageResponder();
        Request request = new Request("GET", "/not-found");
        WikiPage rootPage = new WikiPage("FrontPage", "", new HashSet<>(), "/");
        RequestContext context = new RequestContext(rootPage);

        Response response = responder.makeResponse(request, context);

        assertThat(response.httpCode, is("404"));
        assertThat(response.page.getTitle(), is("404"));
    }

    @Test
    public void testSearchResponderNoResults() {
        Responder responder = new SearchResponder();
        Request request = new Request("POST", "/", new HashMap<String, String>() {{
            put("search_text", "term");
        }});
        WikiPage rootPage = new WikiPage("FrontPage", "", new HashSet<>(), "/");
        RequestContext context = new RequestContext(rootPage);

        Response response = responder.makeResponse(request, context);

        assertThat(response.page.getText(), is("found term in pages:<ul></ul>"));
    }

    @Test
    public void testSearchResponderWithResults() {
        Responder responder = new SearchResponder();
        Request request = new Request("POST", "/", new HashMap<String, String>() {{
            put("search_text", "spam");
        }});
        WikiPage rootPage = new WikiPage("SpamPage", "spam", new HashSet<>(), "/spam");
        RequestContext context = new RequestContext(rootPage);

        Response response = responder.makeResponse(request, context);

        assertThat(response.page.getText(), is("found term in pages:<ul><li>SpamPage</li></ul>"));
    }
}
