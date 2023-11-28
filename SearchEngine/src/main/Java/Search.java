import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

// Query Parser

@WebServlet("/Search")
public class Search extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String keyword = request.getParameter("keyword");

        Connection connection = DataBaseConnection.makeConnection();

        try{
            ResultSet resultset = connection.createStatement().executeQuery("SELECT title, link, ((length(lower(pageText)) - length(replace(lower(pageText),'"+keyword.toLowerCase()+"',''))) / length('"+keyword.toLowerCase()+"')) as Occurence FROM mytable ORDER BY Occurence DESC LIMIT 30;");

            ArrayList<SearchResult> results = new ArrayList<SearchResult>();

            while(resultset.next()){
                String title = resultset.getString("title");
                String link = resultset.getString("link");
                SearchResult searchResult = new SearchResult();
                searchResult.setLink(link);
                searchResult.setTitle(title);
                results.add(searchResult);
            }

            for(SearchResult s : results){
                System.out.println(s.title+"---"+s.link);
            }

            request.setAttribute("results", results);
            response.setContentType("text/html");
            PrintWriter printWriter = response.getWriter();
            request.getRequestDispatcher("search.jsp").forward(request,response);



        } catch(SQLException | ServletException e){
            e.printStackTrace();
        }


    }
}