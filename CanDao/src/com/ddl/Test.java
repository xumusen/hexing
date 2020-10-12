package com.ddl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.entity.Order;
import com.entity.PaymentDetails;
import com.entity.Status;
import com.entity.Title;
import com.entity.product.Category;
import com.entity.product.Combos;
import com.entity.product.Dept;
import com.entity.product.DisProducts;
import com.entity.product.Discounts;
import com.entity.product.ProductCategory;
import com.entity.product.ProductContent;
import com.entity.product.Products;
import com.entity.product.Propertys;
import com.entity.product.Skus;
import com.entity.product.SubDept;
import com.entity.product.Types;
import com.utils.DBUtil;
import com.utils.TimeUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test {

	public static String readFileContent(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		StringBuffer sbf = new StringBuffer();
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempStr;
			while ((tempStr = reader.readLine()) != null) {
				sbf.append(tempStr);
			}
			reader.close();
			return sbf.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return sbf.toString();
	}

	public static void postDineorder(String order) throws SQLException {

		// String order = Test.readFileContent("/Users/jason/OneDrive -
		// cd/项目资料/吉野家/order/1029/test3.txt").trim();
		// String order = Test.readFileContent("D:\\order.txt").trim();
		// System.out.println(order);
		JSONObject jsonobj = JSONObject.fromObject(order);// 将字符串转化成json对象
		/*
		 * System.out.println(jsonobj.getString("data"));
		 * System.out.println(jsonobj.getString("accessKey"));
		 * System.out.println(jsonobj.getString("timestamp"));
		 * System.out.println(jsonobj.getString("sign"));
		 * System.out.println(jsonobj.getString("ticket"));
		 * System.out.println(jsonobj.getString("storeId"));
		 * System.out.println(jsonobj.getString("actionName"));
		 * System.out.println(jsonobj.getString("serviceType"));
		 * System.out.println(jsonobj.getString("vendor"));
		 */
		String accessKey = "";
		try {
			accessKey = jsonobj.getString("accessKey");
			// System.out.println("serviceType =" + serviceType);
		} catch (Exception e) {
			// TODO: handle exception
			accessKey = "";
		}

		// String accessKey=jsonobj.getString("accessKey");
		// String actionName=jsonobj.getString("actionName");
		String actionName = "";
		try {
			actionName = jsonobj.getString("actionName");
			// System.out.println("serviceType =" + serviceType);
		} catch (Exception e) {
			// TODO: handle exception
			actionName = "";
		}
		// long timestamp=jsonobj.getLong("timestamp");

		long timestamp = 0;
		try {
			timestamp = jsonobj.getLong("timestamp");
			// System.out.println("serviceType =" + serviceType);
		} catch (Exception e) {
			// TODO: handle exception
			timestamp = 0;
		}

		// String ticket=jsonobj.getString("ticket");

		String ticket = "";
		try {
			ticket = jsonobj.getString("ticket");
			// System.out.println("serviceType =" + serviceType);
		} catch (Exception e) {
			// TODO: handle exception
			ticket = "";
		}
		// String sign=jsonobj.getString("sign");
		String sign = "";
		try {
			sign = jsonobj.getString("sign");
			// System.out.println("serviceType =" + serviceType);
		} catch (Exception e) {
			// TODO: handle exception
			sign = "";
		}
		// String serviceType=jsonobj.getString("serviceType");
		String serviceType = "";
		try {
			serviceType = jsonobj.getString("serviceType");
			// System.out.println("serviceType =" + serviceType);
		} catch (Exception e) {
			// TODO: handle exception
			serviceType = "";
		}

		String vendor = "";
		try {
			vendor = jsonobj.getString("vendor");
			// System.out.println("serviceType =" + serviceType);
		} catch (Exception e) {
			// TODO: handle exception
			vendor = "";
		}

		String storeId2 = "";
		try {
			storeId2 = jsonobj.getString("storeId");
			// System.out.println("serviceType =" + serviceType);
		} catch (Exception e) {
			// TODO: handle exception
			storeId2 = "";
		}

		Title title = new Title();
		title.setAccessKey(accessKey);
		title.setActionName(actionName);
		title.setServiceType(serviceType);
		title.setSign(sign);
		title.setStoreId(storeId2);
		title.setTicket(ticket);
		title.setTimestamp(timestamp);
		title.setTimestamp2(TimeUtils.getTimeStamptoString(timestamp));
		title.setVendor(vendor);
		title.setJson(order);

		JSONObject jsonobject = JSONObject.fromObject(jsonobj.getString("data"));// 将字符串转化成json对象

		// System.out.println(jsonobj.getString("data"));
		String orderId = jsonobject.getString("orderId");

		// String thirdSn=jsonobject.getString("thirdSn");
		// System.out.println("discount is "+jsonobject.getString("discounts"));
		// System.out.println("products is "+jsonobj.getString("products"));
		if (jsonobject == null || jsonobject.isEmpty() || jsonobject.isNullObject() || "null".equals(jsonobject)) {
			System.out.println("error订单数据有问题");
		}

		try {
			String thirdSn = jsonobject.getString("thirdSn");
			// System.out.println("thirdSn =" + thirdSn);
		} catch (Exception e) {
			// TODO: handle exception
			String thirdSn = "";
		}
		try {
			String name = jsonobject.getString("name");
			// System.out.println("name =" + name);
		} catch (Exception e) {
			// TODO: handle exception
			String name = "";
		}
		try {
			String phone = jsonobject.getString("phone");
			// System.out.println("phone =" + phone);
		} catch (Exception e) {
			// TODO: handle exception
			String phone = "";
		}

		try {
			String takeNo = jsonobject.getString("takeNo");
			// System.out.println("takeNo =" + takeNo);
		} catch (Exception e) {
			// TODO: handle exception
			String takeNo = "";
		}
		try {
			String tableNum = jsonobject.getString("tableNum");
			// System.out.println("tableNum =" + tableNum);
		} catch (Exception e) {
			// TODO: handle exception
			String tableNum = "";
		}
		try {
			int peopleNum = jsonobject.getInt("peopleNum");
			// System.out.println("peopleNum =" + peopleNum);
		} catch (Exception e) {
			// TODO: handle exception
			String peopleNum = "";
		}

		try {
			String deviceNo = jsonobject.getString("deviceNo");
			// System.out.println("deviceNo =" + deviceNo);
		} catch (Exception e) {
			// TODO: handle exception
			String deviceNo = "";
		}
		try {
			String staffId = jsonobject.getString("staffId");
			// System.out.println("staffId =" + staffId);
		} catch (Exception e) {
			// TODO: handle exception
			String staffId = "";
		}

		try {
			String staffNo = jsonobject.getString("staffNo");
			// System.out.println("staffNo =" + staffNo);
		} catch (Exception e) {
			// TODO: handle exception
			String staffNo = "";
		}

		try {
			String staffBane = jsonobject.getString("staffBane");
			// System.out.println("staffBane =" + staffBane);
		} catch (Exception e) {
			// TODO: handle exception
			String staffBane = "";
		}

		try {
			String memberId = jsonobject.getString("memberId");
			// System.out.println("memberId =" + memberId);
		} catch (Exception e) {
			// TODO: handle exception
			String memberId = "";
		}

		try {
			String point = jsonobject.getString("point");
			// System.out.println("point =" + point);
		} catch (Exception e) {
			// TODO: handle exception
			String point = "";
		}
		try {
			String pointExpiryDate = jsonobject.getString("pointExpiryDate");
			// System.out.println("pointExpiryDate =" + pointExpiryDate);
		} catch (Exception e) {
			// TODO: handle exception
			String pointExpiryDate = "";
		}
		try {
			String posOrderType = jsonobject.getString("posOrderType");
			// System.out.println("posOrderType =" + posOrderType);
		} catch (Exception e) {
			// TODO: handle exception
			String posOrderType = "";
		}

		try {
			String userNote = jsonobject.getString("userNote");
			// System.out.println("userNote =" + userNote);
		} catch (Exception e) {
			// TODO: handle exception
			String userNote = "";
		}

		try {
			String payType = jsonobject.getString("payType");
			// System.out.println("payType =" + payType);
		} catch (Exception e) {
			// TODO: handle exception
			String payType = "";
		}

		String storeId = jsonobject.getString("storeId");
		String fromType = jsonobject.getString("fromType");

		String orderTime = jsonobject.getString("orderTime");
		String orderDate = jsonobject.getString("orderDate");
		int orderStatus = jsonobject.getInt("orderStatus");

		try {
			String orderType = jsonobject.getString("orderType");
			// System.out.println("orderType =" + orderType);
		} catch (Exception e) {
			// TODO: handle exception
			String orderType = "";
		}

		boolean isPayed = jsonobject.getBoolean("isPayed");

		JSONObject paymentDetails = null;
		try {
			paymentDetails = jsonobject.getJSONObject(jsonobject.getString("paymentDetails"));// 获取数组
			JSONArray jsonArray = JSONArray.fromObject(jsonobject.getString("paymentDetails")); // 位于data下面
			if (jsonArray.size() == 0 && Math.abs(jsonobject.getDouble("discountPrice")) == Math
					.abs((jsonobject.getDouble("merchantPrice"))))
				System.out.println(orderId + " paymentDetails是空的");
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject paymentDetail = jsonArray.getJSONObject(i);// 获取数组
				PaymentDetails details = (PaymentDetails) JSONObject.toBean(paymentDetail, PaymentDetails.class);

				try {
					details.insertPaymentDetails(orderId, details, orderDate, orderTime, "", storeId);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/*
				 * System.out.println("payType   " + details.getPayType());
				 * System.out.println("type   " + details.getType());
				 * System.out.println("money   " + details.getMoney());
				 * System.out.println("typeName   " + details.getTypeName());
				 */
			}
		} catch (Exception e) {
			// TODO: handle exception

		}

		try {
			boolean isInvoice = jsonobject.getBoolean("isInvoice");
			// System.out.println("isInvoice =" + isInvoice);
		} catch (Exception e) {
			// TODO: handle exception
			String isInvoice = "";
		}
		try {
			String invoiceDesc = jsonobject.getString("invoiceDesc");
			// System.out.println("invoiceDesc =" + invoiceDesc);
		} catch (Exception e) {
			// TODO: handle exception
			String invoiceDesc = "";
		}
		try {
			String taxNo = jsonobject.getString("taxNo");
			// System.out.println("taxNo =" + taxNo);
		} catch (Exception e) {
			// TODO: handle exception
			String taxNo = "";
		}
		try {
			float deliveryFee = jsonobject.getLong("deliveryFee");
			// System.out.println("deliveryFee =" + deliveryFee);
		} catch (Exception e) {
			// TODO: handle exception
			String deliveryFee = "";
		}
		try {
			float productPrice = jsonobject.getLong("productPrice");
			// System.out.println("productPrice =" + productPrice);
		} catch (Exception e) {
			// TODO: handle exception
			String productPrice = "";
		}
		try {
			float thirdPlatformBearPrice = jsonobject.getLong("thirdPlatformBearPrice");
			// System.out.println("thirdPlatformBearPrice =" + thirdPlatformBearPrice);
		} catch (Exception e) {
			// TODO: handle exception
			String thirdPlatformBearPrice = "";
		}
		try {
			float commission = jsonobject.getLong("commission");
			// System.out.println("commission =" + commission);
		} catch (Exception e) {
			// TODO: handle exception
			String commission = "";
		}
		try {
			String mealFee = jsonobject.getString("mealFee");
			// System.out.println("mealFee =" + mealFee);
		} catch (Exception e) {
			// TODO: handle exception
			String mealFee = "";
		}
		try {
			String originPrice = jsonobject.getString("originPrice");
			// System.out.println("originPrice =" + originPrice);
		} catch (Exception e) {
			// TODO: handle exception
			String originPrice = "";
		}
		String price = jsonobject.getString("price");

		try {
			String merchantBearPrice = jsonobject.getString("merchantBearPrice");
			// System.out.println("merchantBearPrice =" + merchantBearPrice);
		} catch (Exception e) {
			// TODO: handle exception
			String merchantBearPrice = "";
		}

		try {
			String discountPrice = jsonobject.getString("discountPrice");

			// System.out.println("discountPrice =" + discountPrice);
		} catch (Exception e) {
			// TODO: handle exception
			String discountPrice = "";
		}

		try {
			String merchantPrice = jsonobject.getString("merchantPrice");

			// System.out.println("merchantPrice =" + merchantPrice);
		} catch (Exception e) {
			// TODO: handle exception
			String merchantPrice = "";
		}

		try {

			JSONArray status = JSONArray.fromObject(jsonobject.getString("status")); // 位于data下面
			// System.out.println(jsonobject.getString("status"));
			for (int i = 0; i < status.size(); i++) {
				JSONObject statuses = status.getJSONObject(i);// 获取数组
				Status statuse1 = (Status) JSONObject.toBean(statuses, Status.class);
				Status.updateStatus(orderId, statuse1);
				/*
				 * System.out.println(statuse1.getTitle());
				 * System.out.println(statuse1.getValue());
				 * System.out.println(statuse1.getDateTime());
				 */
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			JSONArray discountses = JSONArray.fromObject(jsonobject.getString("discounts")); // 位于data下面
			// System.out.println(discountses.toString());
			for (int i = 0; i < discountses.size(); i++) {
				Map discountsmap = new HashMap();
				discountsmap.put("disproducts", DisProducts.class);
				Discounts discount1 = (Discounts) JSONObject.toBean(discountses.getJSONObject(i), Discounts.class,
						discountsmap);
				if (discount1.getDisProducts() != null) {
					for (int j = 0; j < discount1.getDisProducts().size(); j++) {
						JSONArray disproducts = JSONArray.fromObject(discount1.getDisProducts()); // 位于discounts下面
						Map disproductmap = new HashMap();
						disproductmap.put("dept", Dept.class);
						disproductmap.put("types", Types.class);
						DisProducts disProducts2 = (DisProducts) JSONObject.toBean(disproducts.getJSONObject(j),
								DisProducts.class, disproductmap);

						DisProducts.insertDisProducts(orderId, disProducts2, storeId, orderDate, orderTime);
					}
				}
				Discounts.insertDiscount(orderId, discount1, storeId, orderDate, orderTime);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {

			JSONArray products = JSONArray.fromObject(jsonobject.getString("products"));
			// System.out.println("products is " + jsonobject.getString("products"));
			for (int i = 0; i < products.size(); i++) {
				JSONObject product = products.getJSONObject(i);// 获取数组
				// Products product1 = (Products) JSONObject.toBean(product,
				// Products.class);
				Map comboses = new HashMap();
				comboses.put("combos", Combos.class);
				comboses.put("skus", Skus.class);
				comboses.put("properties", Propertys.class);
				comboses.put("types", Types.class);
				comboses.put("category", Category.class);
				comboses.put("dept", Dept.class);
				comboses.put("productContent", ProductContent.class);
				comboses.put("productCategory", ProductCategory.class);
				Products product1 = (Products) JSONObject.toBean(product, Products.class, comboses);
				// System.out.println(product1.getPrice());
				/*
				 * try { product1.insertProducts(orderId, product1, storeId); } catch
				 * (SQLException e) { // TODO Auto-generated catch block e.printStackTrace(); }
				 */

				JSONObject deptobject = JSONObject.fromObject(product1.getDept());// 将字符串转化成json对象
				JSONObject typesobject = JSONObject.fromObject(product1.getTypes());// 将字符串转化成json对象
				JSONObject categoryobject = JSONObject.fromObject(product1.getCategory());// 将字符串转化成json对象
				JSONObject productContentobject = JSONObject.fromObject(product1.getProductContent());// 将字符串转化成json对象
				JSONObject productCategoryobject = JSONObject.fromObject(product1.getProductCategory());// 将字符串转化成json对象

				if (deptobject == null || deptobject.isEmpty() || deptobject.isNullObject() || "null".equals(deptobject)) {
					// System.out.println("deptobject is 空");
				} else {
					// System.out.println("depart 不是空");
					Map demap = new HashMap();
					demap.put("subDept", SubDept.class);

					Dept dept = (Dept) JSONObject.toBean(deptobject, Dept.class, demap);

					if (dept.getSubDept() != null) {
						// System.out.println("subdept is not null");
						JSONObject subDeptobject = JSONObject.fromObject(dept.getSubDept());
						SubDept subDept = (SubDept) JSONObject.toBean(subDeptobject, SubDept.class);
						SubDept.insertSubDept(orderId, subDept, dept.getId(), product1.getPid());
					}

					Dept.insertDept(orderId, dept, product1.getPid());

				}

				if (typesobject == null || typesobject.isEmpty() || typesobject.isNullObject()
						|| "null".equals(typesobject)) {
					// System.out.println("typesobject is 空");
				} else {
					// System.out.println("types start ");
					Types types = (Types) JSONObject.toBean(typesobject, Types.class);
					Types.insertTypes(orderId, types, product1.getPid());
					// System.out.println("types end ");
				}
				if (categoryobject == null || categoryobject.isEmpty() || categoryobject.isNullObject()
						|| "null".equals(categoryobject)) {
					// System.out.println("categoryobject is 空");
				} else {
					// System.out.println("category start ");
					Category category = (Category) JSONObject.toBean(categoryobject, Category.class);
					Category.insertCategory(orderId, category, product1.getPid());
					// System.out.println("category end ");
				}
				if (productContentobject == null || productContentobject.isEmpty() || productContentobject.isNullObject()
						|| "null".equals(productContentobject)) {
					// System.out.println("productContentobject is 空");
				} else {
					// System.out.println("productContent start ");
					ProductContent productContent = (ProductContent) JSONObject.toBean(productContentobject,
							ProductContent.class);
					ProductContent.insertProductContent(orderId, productContent, product1.getPid());
					// System.out.println("productContent end ");
				}
				if (productCategoryobject == null || productCategoryobject.isEmpty() || productCategoryobject.isNullObject()
						|| "null".equals(productCategoryobject)) {
					// System.out.println("productCategoryobject is 空");
				} else {
					// System.out.println("productCategory start ");
					ProductCategory productCategory = (ProductCategory) JSONObject.toBean(productCategoryobject,
							ProductCategory.class);
					ProductCategory.insertProductCategory(orderId, productCategory, product1.getPid());
					// System.out.println("productCategory end ");
				}

				if (product1.getSkus() != null) {
					try {
						for (int j = 0; j < product1.getSkus().size(); j++) {

							JSONArray pskus = JSONArray.fromObject(product1.getSkus()); // 位于discounts下面

							Skus skus = (Skus) JSONObject.toBean(pskus.getJSONObject(j), Skus.class);

							Skus.insertSkus(orderId, skus, product1.getPid());
						}

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (product1.getPropertys() != null) {
					try {
						// System.out.println(" properties is "+product1.getPropertys().size());
						for (int j = 0; j < product1.getPropertys().size(); j++) {
							JSONArray properties = JSONArray.fromObject(product1.getPropertys()); // 位于discounts下面
							// System.out.println("j is " + j);
							Propertys propertys = (Propertys) JSONObject.toBean(properties.getJSONObject(j),
									Propertys.class);
							Propertys.insertPropertys(orderId, propertys, product1.getPid());
						}

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				boolean istaocan = false;

				if (product1.getCombos() != null) {
					istaocan = true;
					try {
						for (int j = 0; j < product1.getCombos().size(); j++) {

							JSONArray comboses1 = JSONArray.fromObject(product1.getCombos()); // 位于discounts下面
							// System.out.println("j is " + j);
							Combos combos = (Combos) JSONObject.toBean(comboses1.getJSONObject(j), Combos.class);
							Combos.insertCombos(orderId, combos, product1.getPid(), product1.getNum(), storeId, orderDate,
									"", "");

							if (product1.getCombos().get(j).getSkus() != null) {
								try {
									for (int k = 0; k < product1.getCombos().get(j).getSkus().size(); k++) {

										JSONArray skuses = JSONArray.fromObject(product1.getCombos().get(j).getSkus()); // 位于discounts下面

										Skus skus = (Skus) JSONObject.toBean(skuses.getJSONObject(k), Skus.class);
										// Combos.insertCombos(orderId, combos,product1.getPid());
										Skus.insertSkus(orderId, skus, product1.getPid());

									}
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}

							if (product1.getCombos().get(j).getPropertys() != null) {
								try {
									for (int k = 0; k < product1.getCombos().get(j).getPropertys().size(); k++) {

										JSONArray compropertyes = JSONArray
												.fromObject(product1.getCombos().get(j).getPropertys()); // 位于discounts下面

										Propertys comproPropertys = (Propertys) JSONObject
												.toBean(compropertyes.getJSONObject(k), Propertys.class);
										Propertys.insertComPropertys(orderId, comproPropertys, product1.getPid(),
												product1.getCombos().get(j).getPid());

									}
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}

						}

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				try {
					product1.insertProducts(orderId, product1, storeId, istaocan, orderDate);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
		} 
		catch (Exception e) {
			// TODO: handle exception
		}

		try {
			Map orders = new HashMap();
			orders.put("paymentDetails", PaymentDetails.class);
			orders.put("status", Status.class);
			Order order1 = (Order) JSONObject.toBean(jsonobject, Order.class, orders);
			Order.insertOrder(orderId, order1, title);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		// String order = Test.readFileContent("/Users/jason/OneDrive -
		// cd/项目资料/吉野家/order/1031/shitong1.txt").trim();
		// String order = Test.readFileContent("/Users/jason/OneDrive -
		// cd/项目资料/吉野家/order/1118/st1.txt").trim();
		String order = Test.readFileContent("E:\\seito.txt").trim();
		// String order = Test.readFileContent("H:\\OneDrive -
		// cd\\项目资料\\吉野家\\order\\1101\\st2error.txt").trim();
		// String order = Test.readFileContent("/Users/jason/OneDrive -
		// cd/项目资料/吉野家/order/1028-jian24/test3.txt").trim();
		// System.out.println(order);
		// Statement stmt = DBUtil.getConnection().createStatement();
		// ResultSet executeQuery(String sqlString)：执行查询数据库的SQL语句
		// ，返回一个结果集（ResultSet）对象。
		// ResultSet rs = stmt.executeQuery("SELECT * FROM error2531 WHERE
		// cast(接口名称 as NVARCHAR)='同步堂食订单' and cast(异常信息 as
		// NVARCHAR)='请求Seito无响应' ");
		// ResultSet rs = stmt.executeQuery("SELECT * FROM error1102 where 异常信息
		// ='请求Seito无响应' ");

		/*
		 * ResultSet rs = stmt.
		 * executeQuery("select * from  error1117 where \"接口名称\"='同步堂食订单' and \"异常信息\"='请求Seito无响应' "
		 * ); // stmt.
		 * executeQuery(" select * from error1114 AS e where e.异常信息='ActionName (candao.posEntryApi.entry) is illegal.' "
		 * );
		 * 
		 * while(rs.next()){//如果对象中有数据，就会循环打印出来
		 * System.out.println(rs.getString("请求内容"));
		 * Test.postDineorder(rs.getString("请求内容")); }
		 */

		ResourceBundle resource = ResourceBundle.getBundle("web");
		// System.out.println(resource.getString("URL"));
		// System.out.println(order);
		//Test.postDineorder(order);
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		   Calendar c = Calendar.getInstance();    
	        c.add(Calendar.MONTH, 0);
	        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
	        String first = format.format(c.getTime());
	        System.out.println("===============first:"+first);
	}

}
