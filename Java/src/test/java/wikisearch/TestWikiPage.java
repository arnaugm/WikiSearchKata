package wikisearch;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestWikiPage {

    @Test
    public void testCreatePages() {
        WikiPage rootPage =
                new WikiPage("FrontPage", "some text on the root page", new HashSet<>(Arrays.asList("foo", "bar")), "uri");
        WikiPage childPage = new WikiPage("Child1", "a child page", new HashSet<>(Arrays.asList("foo")), "uri");
        rootPage.addChild(childPage);

        assertThat(rootPage.getTitle(), is("FrontPage"));
        assertThat(rootPage.getChildren().stream().map(WikiPage::getTitle).collect(toList()), hasItem("Child1"));
        assertThat(childPage.getParents().stream().map(WikiPage::getTitle).collect(toList()), hasItem("FrontPage"));
    }

    @Test
    public void testUri() {
        WikiPage rootPage =
                new WikiPage("FrontPage", "some text on the root page", new HashSet<>(Arrays.asList("foo", "bar")), "/");
        WikiPage childPage = new WikiPage("Child1", "a child page", new HashSet<>(Arrays.asList("foo")), "Child1");
        rootPage.addChild(childPage);
        WikiPage grandchild_page = new WikiPage("Child2", "a child page", new HashSet<>(Arrays.asList("foo")), "Child2");
        childPage.addChild(grandchild_page);

        assertThat(rootPage.getUri(), is("/"));
        assertThat(childPage.getUri(), is("/Child1"));
        assertThat(grandchild_page.getUri(), is("/Child1/Child2"));
    }
}
