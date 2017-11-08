package wikisearch;

import java.util.*;

public class WikiPage {

    private String title;
    private String text;
    private Set<String> tags ;
    private String uri;
    private List<WikiPage> parents;
    private List<WikiPage> children;

    public WikiPage(String title) {
        this(title, "");
    }

    public WikiPage(String title, String text) {
        this(title, text, new HashSet<>());
    }

    public WikiPage(String title, String text, Set<String> tags) {
        this(title, text, tags, title);
    }

    public WikiPage(String title, String text, Set<String> tags, String uri) {
        this.title = title;
        this.uri = uri;
        this.text = text;
        this.tags = tags;
        this.parents = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    public void addChild(WikiPage childPage) {
        this.children.add(childPage);
        childPage.addParent(this);
    }

    public void addParent(WikiPage parentPage) {
        this.parents.add(parentPage);
        if ("/".equals(parentPage.uri)) {
            this.uri = "/" + this.uri;
        } else {
            this.uri = parentPage.uri + "/" + this.uri;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Set<String> getTags() {
        return tags;
    }

    public String getUri() {
        return uri;
    }

    public List<WikiPage> getParents() {
        return parents;
    }

    public List<WikiPage> getChildren() {
        return children;
    }


    @Override
    public String toString() {
        return "WikiPage{" +
                "title='" + title + '\'' +
                '}';
    }
}
