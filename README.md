Wiki Search Kata
================

We are developing a wiki application.
With this application you can read wiki pages and find pages containing a search term, but we want more!

Our *"responder.py"* provides different responses depending on what we are asking for:
- **WikiPageResponder**: returns the page with the given uri.

  *Request*
  ```
  Request(request_type="GET", uri="/")
  ```
  *Response*
  ```
  WikiPage
  - children: [Child1]
  - parents: []
  - tags: []
  - text: "FrontPage text"
  - title: "FrontPage"
  - uri: "/"
  ```

- **SearchResponder**: returns a results page with a list of pages containing the given term in its text.

  *Request*
  ```
  Request(request_type="POST", uri="/", data={"search_text": "child"}))
  ```
  *Response*
  ```
  WikiPage
  - children: []
  - parents: []
  - tags: []
  - text: "found term in pages:<ul><li>Child1</li></ul>"
  - title: "Search Results"
  - uri: "/search-results"
  ```
  
Our next task will be to extend the search functionality of the wiki with the following features:
- **Where Used**: returns a results page with a list of pages referencing the given page.

  *Request*
  ```
  Request(request_type="POST", uri="/", data={"where_used": "FrontPage"}))
  ```
  *Response*
  ```
  WikiPage
  - children: []
  - parents: []
  - tags: []
  - text: "found references in pages:<ul><li>Child1</li></ul>"
  - title: "Where used: FrontPage"
  - uri: "/where-used"
  ```
  
- **Property Search**: returns a results page with a list of pages containing the given tags.

  *Request*
  ```
  Request(request_type="POST", uri="/", data={"tags": {"bar"}}))
  ```
  *Response*
  ```
  WikiPage
  - children: []
  - parents: []
  - tags: []
  - text: "found tags in pages:<ul><li>Child1</li></ul>"
  - title: "Property Search: 'bar'"
  - uri: "/property-search"
  ```
  
Think about a clean and extensible solution for the requested functionality.