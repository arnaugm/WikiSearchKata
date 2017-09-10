from responder import WikiPageResponder, SearchResponder
from request_response import RequestContext


class WikiApp:
    def __init__(self, root_page):
        self.root_page = root_page

    def handle_request(self, request):
        if request.request_type == "GET":
            responder = WikiPageResponder()
        elif request.request_type == "POST":
            responder = SearchResponder()
        else:
            raise Exception('Method not supported')
        return responder.make_response(request, RequestContext(self.root_page))
