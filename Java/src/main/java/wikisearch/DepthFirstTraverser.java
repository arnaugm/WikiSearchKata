package wikisearch;

import java.util.*;

public class DepthFirstTraverser {

    private WikiPage root;
    private Set<WikiPage> visited;
    private Deque<WikiPage> toVisit;

    public DepthFirstTraverser(WikiPage root) {
        this.root = root;
        this.visited = new HashSet<>();
        this.toVisit = new LinkedList<WikiPage>() {{
            push(root);
        }};
    }

    public List<WikiPage> traverse() {
        List<WikiPage> result = new ArrayList<>();
        while (!this.toVisit.isEmpty()) {
            WikiPage node = this.toVisit.pop();
            if (this.visited.contains(node)) {
                continue;
            }
            this.visited.add(node);
            node.getChildren().forEach(c -> this.toVisit.push(c));
            result.add(node);
        }
        return result;
    }
}
