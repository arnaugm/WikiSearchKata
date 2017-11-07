package wikisearch;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestWikiApp {

    @Test
    public void testRequestResponseCycle() {
        WikiPage rootPage = new WikiPage("FrontPage", "", new HashSet<>(), "/");
        WikiApp myapp = new WikiApp(rootPage);
        Request request = new Request("GET", "/");

        Response response = myapp.handleRequest(request);

        assertThat(response.page.getTitle(), is("FrontPage"));
    }

    @Test
    public void testRequestAPage() {
        WikiPage rootPage = new WikiPage("FrontPage", "", new HashSet<>(), "/");
        WikiPage childPage = new WikiPage("Child1", "a child page", new HashSet<String>() {{
            add("foo");
        }});
        rootPage.addChild(childPage);
        WikiApp myapp = new WikiApp(rootPage);
        Request request = new Request("GET", "/Child1");

        Response response = myapp.handleRequest(request);

        assertThat(response.page.getTitle(), is("Child1"));
    }

    @Test
    public void testRequestASearch() {
        WikiPage rootPage = new WikiPage("FrontPage", "", new HashSet<>(), "/");
        WikiPage childPage = new WikiPage("Child1", "a child page", new HashSet<String>() {{
            add("foo");
        }});
        rootPage.addChild(childPage);
        WikiApp myapp = new WikiApp(rootPage);
        Request request = new Request("POST", "/", new HashMap<String, String>(){{
            put("search_text", "child");
        }});

        Response response = myapp.handleRequest(request);

        assertThat(response.page.getTitle(), is("Search Results"));
        assertThat(response.page.getText(), containsString("Child1"));
    }

    @Test(expected = RuntimeException.class)
    public void testUnsupportedHttpMethod() {
        WikiPage rootPage = new WikiPage("FrontPage", "", new HashSet<>(), "/");
        WikiApp myapp = new WikiApp(rootPage);
        Request request = new Request("UPDATE", "/");

        myapp.handleRequest(request);
    }

}
