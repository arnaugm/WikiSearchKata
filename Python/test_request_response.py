import pytest

from wikiapp import *
from wiki_page import WikiPage
from request_response import Request


def test_request_response_cycle():
    root_page = WikiPage(title="FrontPage", uri="/")
    myapp = WikiApp(root_page)
    request = Request(request_type="GET", uri="/")
    response = myapp.handle_request(request)
    assert response.page.title == "FrontPage"


def test_request_a_page():
    root_page = WikiPage(title="FrontPage", uri="/")
    child_page = WikiPage(title="Child1", text="a child page", tags=["foo"])
    root_page.add_child(child_page)
    myapp = WikiApp(root_page)
    request = Request(request_type="GET", uri="/Child1")
    response = myapp.handle_request(request)
    assert response.page.title == "Child1"


def test_request_a_search():
    root_page = WikiPage(title="FrontPage", uri="/")
    child_page = WikiPage(title="Child1", text="a child page", tags=["foo"])
    root_page.add_child(child_page)
    myapp = WikiApp(root_page)
    request = Request(request_type="POST", uri="/",
                      data={"search_text": "child"})
    response = myapp.handle_request(request)
    assert response.page.title == "Search Results"
    assert "Child1" in response.page.text


def test_unsupported_http_method():
    root_page = WikiPage(title="FrontPage", uri="/")
    myapp = WikiApp(root_page)
    request = Request(request_type="UPDATE", uri="/")
    with pytest.raises(Exception):
        myapp.handle_request(request)
