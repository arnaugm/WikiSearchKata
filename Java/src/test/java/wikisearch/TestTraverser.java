package wikisearch;

import org.junit.Test;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

public class TestTraverser {

    @Test
    public void testTraversePages() {
        WikiPage root = new WikiPage("Root");
        WikiPage child1 = new WikiPage("Child1");
        WikiPage child2 = new WikiPage("Child2");
        WikiPage child3 = new WikiPage("Child3");
        root.addChild(child2);
        root.addChild(child1);
        child1.addChild(child3);

        DepthFirstTraverser traverser = new DepthFirstTraverser(root);
        List<WikiPage> pagesVisitedInOrder = traverser.traverse();
        List<String> pageTitlesVisitedInOrder = pagesVisitedInOrder.stream()
                .map(WikiPage::getTitle)
                .collect(toList());

        assertThat(pageTitlesVisitedInOrder.indexOf("Child3"), is(lessThan(pageTitlesVisitedInOrder.indexOf("Child2"))));
    }

    @Test
    public void testTraverseWithLoops() {
        WikiPage root = new WikiPage("Root");
        WikiPage child1 = new WikiPage("Child1");
        WikiPage child2 = new WikiPage("Child2");
        WikiPage child3 = new WikiPage("Child3");
        root.addChild(child2);
        root.addChild(child1);
        child1.addChild(child3);
        child3.addChild(root);

        DepthFirstTraverser traverser = new DepthFirstTraverser(root);
        List<WikiPage> pagesVisitedInOrder = traverser.traverse();

        assertThat(pagesVisitedInOrder.size(), is(4));
    }
}


