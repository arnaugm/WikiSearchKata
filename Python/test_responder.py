from request_response import Request, RequestContext
from responder import WikiPageResponder, SearchResponder
from wiki import WikiPage


def test_wiki_page_responder_ok():
    responder = WikiPageResponder()
    request = Request(request_type="GET", uri="/")
    root_page = WikiPage(title="FrontPage", uri="/")
    context = RequestContext(root_page)

    response = responder.make_response(request, context)

    assert response.http_code == "200"


def test_wiki_page_responder_ko():
    responder = WikiPageResponder()
    request = Request(request_type="GET", uri="/not-found")
    root_page = WikiPage(title="FrontPage", uri="/")
    context = RequestContext(root_page)

    response = responder.make_response(request, context)

    assert response.http_code == "404"
    assert response.page.title == "404"


def test_search_responder_no_results():
    responder = SearchResponder()
    request = Request(request_type="POST", uri="/", data={"search_text": "term"})
    root_page = WikiPage(title="FrontPage", uri="/")
    context = RequestContext(root_page)

    response = responder.make_response(request, context)

    assert response.page.text == "found term in pages:<ul></ul>"


def test_search_responder_with_results():
    responder = SearchResponder()
    request = Request(request_type="POST", uri="/", data={"search_text": "spam"})
    root_page = WikiPage(title="SpamPage", uri="/spam", text="spam")
    context = RequestContext(root_page)

    response = responder.make_response(request, context)

    assert response.page.text == "found term in pages:<ul><li>SpamPage</li></ul>"
