<%@page import = "java.util.ArrayList"%>
<%@page import = "com.accio.SearchResult"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <table border = 2>
        <tr>
            <th>Title</th>
            <th>Link</th>
        </tr>
        <%
            ArrayList<SearchResult> results = (ArrayList<SearchResult>)request.getAttribute("results");
            for(SearchResult res : results){
        %>
        <tr>
            <td> <% out.println(res.getTitle()); %></td>
            <td> <% out.println(res.getLink());  %></td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>