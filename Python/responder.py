from request_response import Response
from wiki_page import WikiPage
from traverse import DepthFirstTraverser


class WikiPageResponder:
    def make_response(self, request, context):
        # brute force approach where better approaches exist
        all_pages = {page.uri: page for page in
                     DepthFirstTraverser(context.root_page).traverse()}
        page = all_pages.get(request.uri)
        if page:
            return Response(http_code="200", page=page)
        else:
            return Response(http_code="404", page=WikiPage("404"))


class SearchResponder:
    def make_response(self, request, context):
        self.request = request
        self.context = context
        results_page = WikiPage(self.title())
        matching_pages = (page for page in
                          DepthFirstTraverser(context.root_page).traverse()
                          if self.traverse(page))
        results_page.text = "found term in pages:<ul>"
        for result_page in matching_pages:
            results_page.text += '<li>' + result_page.title + '</li>'
        results_page.text += "</ul>"
        return Response(page=results_page)

    def title(self):
        return "Search Results"

    def traverse(self, page):
        search_term = self.request.data["search_text"]
        return search_term in page.text
