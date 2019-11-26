

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.DBUtil;
import com.utils.Paging;

/**
 * Servlet implementation class Showall
 */
@WebServlet("/Showall")
public class Showall extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   // private final static String URL = "jdbc:sqlserver://123.123.96.76:2433;DatabaseName=hexing";
    private final static String URL = "jdbc:sqlserver://192.168.5.142:1433;DatabaseName=hexing";
    private static final String USER="sa";
    private static final String PASSWORD="Xuc2008@126.com";
    
    private static Connection conn;

	public static Statement st;
	   int currentPage=1;
    //静态代码块（将加载驱动、连接数据库放入静态块中）
    static{
        try {
            //1.加载驱动程序
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //2.获得数据库的连接
            conn=(Connection)DriverManager.getConnection(URL,USER,PASSWORD);
            st=conn.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //
	
	
	protected void service(HttpServletRequest request, HttpServletResponse response) //
			throws ServletException, IOException {
		List<Map> list =new ArrayList<Map>();//创建list集合用于存入map的键值对集合
		List<Map> totallist =new ArrayList<Map>();//创建list集合用于存入map的键值对集合
/*		String idcard_w = request.getParameter("idcard_w");//接收到前台传来的数据
		System.out.println(idcard_w);*/
		int page=0;
        //得到传过来的当前页
        String str_page=request.getParameter("page");
        /**
         * 创建分页的关于一些内容的工具bean
         * 
         * */
        Paging paging = null;
        int totalnum=0;
		try {
			//String sql ="SELECT top 100 [orderid],[thirdSn],[storeId],[fromType],[name],[phone],[takeNo],[tableNum],[userNote],[peopleNum],[tableNo],[deviceNo],[staffId],[staffNo],[staffBane],[memberId],[point],[pointExpiryDate],[orderTime],[orderDate],[orderStatus],[orderType],[posOrderType],[isPayed],[payType],[isInvoice],[invoiceDesc],[taxNo],[price],[deliveryFee],[mealFee],[productPrice],[discountPrice],[merchantBearPrice],[thirdPlatformBearPrice],[merchantPrice],[commission],[extra],[paymentDetails],[status],[products],[discounts],[createtime]  FROM [dbo].[order];";

			String totalsql="select count(1) as num from [orders]";
			 //String limitsql="select * from("+sql+")pp limit "+start+","+pageSize;
			 
			 ResultSet totalset = st.executeQuery(totalsql);
			
			 while(totalset.next()){
				 totalnum=totalset.getInt("num");
			 }
			   paging=new Paging();
		       // paging.setList(list);
		       // paging.setCount();//数据总数
		        paging.setCount(totalnum);
		        System.out.println("一共"+list.size());
		        paging.setPagesize(20);//一个页面的数据多少条
		        paging.setPagenumber();//总的页面数
		        paging.setEndpage();//最后一页
		        paging.setIndexpage(1);//第一页
		        if (str_page!=null) {
		            //将页转换整型判断其大小
		            int pag=Integer.parseInt(str_page);
		            //当大于零，将传过来的pag值赋给当前页page
		            if (pag>=0) {
		                page=pag;
		                //如果小于最大值时则，将其传过来的值减1在赋值给当前页，让其一直在最后一页
		                if (pag>(paging.getPagenumber()-1)) {
		                    page=pag-1;
		                }
		            }
		        }
		        paging.setPage(page);//最终确认当前页
		/*        List<Object> list_page =new ArrayList<>();
		        //将当前页的值传给新的list_page集合中，list集合是全部数据综合，用i调用其中的几条数据给list_page
		        for (int i = paging.getPage()*paging.getPagesize(); i <(paging.getPage()+1)*paging.getPagesize()&&i<list.size(); i++) {
		            list_page.add(list.get(i));
		        }
		       */
			 
		    String limitsql="exec paging_procedure_order @pageIndex="+str_page+",@pageSize="+paging.getPagesize();
			 System.out.println(limitsql);
			ResultSet rs = st.executeQuery(limitsql);
			//从数据库读取的内容，返回一个结果集。
			System.out.println("获取数据");
			while (rs.next()) {
				String orderid = rs.getString("orderid");
				String thirdSn = rs.getString("thirdSn");
				String storeId = rs.getString("storeId");
				String fromType = rs.getString("fromType");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String takeNo = rs.getString("takeNo");
				String tableNum = rs.getString("tableNum");
				String userNote = rs.getString("userNote");
				String peopleNum = rs.getString("peopleNum");
				String tableNo = rs.getString("tableNo");
				String deviceNo = rs.getString("deviceNo");
				String staffId = rs.getString("staffId");
				String staffNo = rs.getString("staffNo");
				//String contact = rs.getString("contact");
				String staffBane = rs.getString("staffBane");
				String memberId = rs.getString("memberId");
				String point = rs.getString("point");
				String pointExpiryDate = rs.getString("pointExpiryDate");
				String orderTime = rs.getString("orderTime");
				String orderDate = rs.getString("orderDate");
				String orderStatus = rs.getString("orderStatus");
				String orderType = rs.getString("orderType");
				String posOrderType = rs.getString("posOrderType");
				String isPayed = rs.getString("isPayed");
				String payType = rs.getString("payType");
				String isInvoice = rs.getString("isInvoice");
				String invoiceDesc = rs.getString("invoiceDesc");
				String taxNo = rs.getString("taxNo");
				String price = rs.getString("price");
				String deliveryFee = rs.getString("deliveryFee");
				String mealFee = rs.getString("mealFee");
				String productPrice = rs.getString("productPrice");
				String discountPrice = rs.getString("discountPrice");
				String merchantBearPrice = rs.getString("merchantBearPrice");
				String thirdPlatformBearPrice = rs.getString("thirdPlatformBearPrice");
				String merchantPrice = rs.getString("merchantPrice");
				String commission = rs.getString("commission");
				String extra = rs.getString("extra");
				String paymentDetails = rs.getString("paymentDetails");
				String products = rs.getString("products");
				String discounts = rs.getString("discounts");
				String createtime = rs.getString("createtime");
	
				//获取用循环接收数据库的表格信息
				
				Map map = new HashMap(); 
				map.put("orderid", orderid);			
				map.put("thirdSn", thirdSn);		
				map.put("storeId", storeId);
				map.put("fromType", fromType);
				map.put("name", name);
				map.put("phone", phone);
				map.put("takeNo", takeNo);
				map.put("tableNum", tableNum);
				map.put("userNote", userNote);
				map.put("peopleNum", peopleNum);
				map.put("tableNo", tableNo);
				map.put("deviceNo", deviceNo);
				map.put("staffId", staffId);
				map.put("staffNo", staffNo);
				//map.put("contact", contact);
				map.put("staffBane", staffBane);
				map.put("memberId", memberId);
				map.put("point", point);
				map.put("pointExpiryDate", pointExpiryDate);
				map.put("orderTime", orderTime);
				map.put("orderDate", orderDate);
				map.put("orderStatus", orderStatus);
				map.put("orderType", orderType);
				map.put("posOrderType", posOrderType);
				map.put("isPayed", isPayed);
				map.put("payType", payType);
				map.put("isInvoice", isInvoice);
				map.put("invoiceDesc", invoiceDesc);
				map.put("taxNo", taxNo);
				map.put("price", price);
				map.put("deliveryFee", deliveryFee);
				map.put("mealFee", mealFee);
				map.put("productPrice", productPrice);
				map.put("discountPrice", discountPrice);
				map.put("merchantBearPrice", merchantBearPrice);
				map.put("thirdPlatformBearPrice", thirdPlatformBearPrice);
				map.put("merchantPrice", merchantPrice);
				map.put("commission", commission);
				map.put("extra", extra);
				map.put("paymentDetails",paymentDetails);
				map.put("products", products);
				map.put("discounts", discounts);
				map.put("createtime", createtime);
	
				//用键值对存入到map集合中
				System.out.println(map);
				list.add(map);//在将map集合对象存入list集合
				System.out.println("放入集合");
				for (Map map_1 :list) {
					System.out.println(map_1);
				}//在打印台遍历出数据查看是否有错误
				
			}//遍历结果集
		} catch (Exception e) {
			e.printStackTrace();
		}
	
 
		System.out.println("跳转");
		
      
        
        
		  request.setAttribute("paging",paging);
		    paging.setList(list);//从数据库得到数据存入的list集合
		request.setAttribute("key_list",list);//将list集合数据放入到request中共享
		request.setAttribute("totalnum", totalnum);
		request.getRequestDispatcher("/all.jsp").forward(request, response);
		//跳转到index.jsp页面
	}


    /**
     * @see HttpServlet#HttpServlet()
     */
    public Showall() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
