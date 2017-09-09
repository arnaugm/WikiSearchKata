from request_response import Request, RequestContext
from responder import WikiPageResponder
from wiki import WikiPage


def test_wiki_page_responder_ok():
    responder = WikiPageResponder()
    request = Request(request_type="GET", uri="/")
    root_page = WikiPage(title="FrontPage", uri="/")
    context = RequestContext(root_page)

    response = responder.make_response(request, context)

    assert response.http_code == "200"
