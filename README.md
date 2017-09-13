WikiSearchKata
==============

We are developing a wiki application.
With this application you can read wiki pages and find pages containing a search term, but we want more!

In our "reponder.py" there are several classes which provide different responses depending on what we are asking for:
- WikiPageResponder: returns the page with the given uri.
- SearchResponder: returns a search results page with a list of pages containing the given term in its text.

Our next task will be to provide two new responder classes with the following functionality:
- WhereUsedResponder: adding a "where_used" field in the data paremeter of the request, we will ask for a search results page with a list of pages referencing the given page in its text by its title.
- PropertySearchResponder: