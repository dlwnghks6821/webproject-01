package web08;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class selectServlet
 */
@WebServlet("/RoomAllSelect")
public class RoomAllSelect extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
   
   String url;
   String userid;
   String passwd;
   
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomAllSelect() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
       url = "jdbc:oracle:thin:@localhost:1521:orcl";
       userid ="system";
       passwd = "human123";
       Connection conn = null;
       Statement stmt = null;
       ResultSet rs = null;
       PrintWriter out = response.getWriter();
       try {
         String sql = "select room_id,room_name,room_type,howmuch,howmany1 from ROOM ";
         
         Class.forName("oracle.jdbc.driver.OracleDriver");
         conn = DriverManager.getConnection(url,userid,passwd);
         stmt = conn.createStatement();
         rs=stmt.executeQuery(sql);
         String str = "";
         //rs.next();
         //str=rs.getString("ROOM_ID");
         //System.out.println(str);
         while(rs.next()) {
            if(!str.equals("")) str+=",";
            
            str+=rs.getString("room_id");
            str+="</td><td>";
            str+=rs.getString("room_name");
            str+="</td><td>";
            str+=rs.getString("room_type");
            str+="</td><td>";
            str+=rs.getString("howmuch");
            str+="</td><td>";
            str+=rs.getString("howmany1");
            System.out.println(str);
         }
         
         
         out.println(str);
         out.close();
         //rs.close();
         System.out.println("console.10");
      }catch(Exception e) {
         System.out.println(e);
      }finally {
         try {
            rs.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
         try {
            stmt.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
         try {
            conn.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}