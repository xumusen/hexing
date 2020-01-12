package com.ddl;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.entity.Order;
import com.entity.Order2;
import com.entity.PaymentDetails;
import com.entity.Status;
import com.entity.Title;
import com.entity.goods.Combos;
import com.entity.goods.DisProducts;
import com.entity.goods.Discounts;
import com.entity.goods.Products;
import com.entity.goods.Products_goods;
import com.entity.goods.Propertys;
import com.entity.goods.Skus;
import com.utils.ExcelData;
import com.utils.TimeUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;

public class Test2 {

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

	public static void retailOrder(String order) {

		// String order = Test2.readFileContent("/Users/jason/OneDrive -
		// cd/项目资料/吉野家/order/1031/shitong1").trim();
		// String order = Test.readFileContent("/Users/jason/OneDrive -
		// cd/项目资料/吉野家/order/1028-jian24/test3.txt").trim();
		
		// String order = Test2.readFileContent("H:\\OneDrive -
		// cd\\项目资料\\吉野家\\order\\1028-jian24\\test1.txt").trim();
		//String order = Test.readFileContent("/Users/jason/OneDrive - cd/项目资料/吉野家/order/1031/j6.txt").trim();
		// System.out.println(order);
		JSONObject jsonobj = JSONObject.fromObject(order);// 将字符串转化成json对象

		String accessKey=jsonobj.getString("accessKey");
		String actionName=jsonobj.getString("actionName");
		long timestamp=jsonobj.getLong("timestamp");
		String ticket=jsonobj.getString("ticket");
		String sign=jsonobj.getString("sign");
		String serviceType=jsonobj.getString("serviceType");
		String vendor=jsonobj.getString("vendor");
		
		String storeId2="";
		try {
			 storeId2=jsonobj.getString("storeId");
		//	System.out.println("storeId2 =" + storeId2);
		} catch (Exception e) {
			// TODO: handle exception
			 storeId2 = "";
		}
		Title title= new Title();
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

	//	System.out.println(jsonobj.getString("data"));
		String orderId = jsonobject.getString("orderId");
		/*
		 * String thirdSn=jsonobject.getString("sn");
		 * System.out.println(jsonobj.getString("sn"));
		 */
		// String thirdSn=jsonobject.getString("thirdSn");

		if (jsonobject == null || jsonobject.isEmpty() || jsonobject.isNullObject() || "null".equals(jsonobject)) {
	//		System.out.println("订单数据有问题");
		}

		/*
		 * try { String thirdSn = jsonobject.getString("thirdSn");
		 * System.out.println("thirdSn =" + thirdSn); } catch (Exception e) { //
		 * TODO: handle exception String thirdSn = ""; }
		 */

		try {
			String sn = jsonobject.getString("sn");
	//		System.out.println("sn =" + sn);
		} catch (Exception e) {
			// TODO: handle exception
			String sn = "";
		}
		try {
			String subStoreId = jsonobject.getString("subStoreId");
	//		System.out.println("subStoreId =" + subStoreId);
		} catch (Exception e) {
			// TODO: handle exception
			String subStoreId = "";
		}
		try {
			String storeName = jsonobject.getString("storeName");
	//		System.out.println("storeName =" + storeName);
		} catch (Exception e) {
			// TODO: handle exception
			String storeName = "";
		}
		try {
			String brandId = jsonobject.getString("brandId");
	//		System.out.println("brandId =" + brandId);
		} catch (Exception e) {
			// TODO: handle exception
			String brandId = "";
		}

		try {
			String brandName = jsonobject.getString("brandName");
	//		System.out.println("brandName =" + brandName);
		} catch (Exception e) {
			// TODO: handle exception
			String brandName = "";
		}

		try {
			String counts = jsonobject.getString("counts");
	//		System.out.println("counts =" + counts);
		} catch (Exception e) {
			// TODO: handle exception
			String counts = "";
		}

		try {
			String name = jsonobject.getString("name");
	//		System.out.println("name =" + name);
		} catch (Exception e) {
			// TODO: handle exception
			String name = "";
		}
		try {
			String openId = jsonobject.getString("openId");
	//		System.out.println("openId =" + openId);
		} catch (Exception e) {
			// TODO: handle exception
			String openId = "";
		}

		try {
			String unionId = jsonobject.getString("unionId");
//			System.out.println("unionId =" + unionId);
		} catch (Exception e) {
			// TODO: handle exception
			String unionId = "";
		}

		try {
			String phone = jsonobject.getString("phone");
//			System.out.println("phone =" + phone);
		} catch (Exception e) {
			// TODO: handle exception
			String phone = "";
		}

		try {
			String deviceNo = jsonobject.getString("deviceNo");
	//		System.out.println("deviceNo =" + deviceNo);
		} catch (Exception e) {
			// TODO: handle exception
			String deviceNo = "";
		}

		try {
			String takeNo = jsonobject.getString("takeNo");
	//		System.out.println("takeNo =" + takeNo);
		} catch (Exception e) {
			// TODO: handle exception
			String takeNo = "";
		}
		try {
			String tableNum = jsonobject.getString("tableNum");
	//		System.out.println("tableNum =" + tableNum);
		} catch (Exception e) {
			// TODO: handle exception
			String tableNum = "";
		}
		try {
			int peopleNum = jsonobject.getInt("peopleNum");
	//		System.out.println("peopleNum =" + peopleNum);
		} catch (Exception e) {
			// TODO: handle exception
			String peopleNum = "";
		}

		try {
			String deviceNo = jsonobject.getString("deviceNo");
	//		System.out.println("deviceNo =" + deviceNo);
		} catch (Exception e) {
			// TODO: handle exception
			String deviceNo = "";
		}
		try {
			String staffId = jsonobject.getString("staffId");
	//		System.out.println("staffId =" + staffId);
		} catch (Exception e) {
			// TODO: handle exception
			String staffId = "";
		}

		try {
			String staffNo = jsonobject.getString("staffNo");
	//		System.out.println("staffNo =" + staffNo);
		} catch (Exception e) {
			// TODO: handle exception
			String staffNo = "";
		}

		try {
			String staffBane = jsonobject.getString("staffBane");
	//		System.out.println("staffBane =" + staffBane);
		} catch (Exception e) {
			// TODO: handle exception
			String staffBane = "";
		}

		try {
			String memberId = jsonobject.getString("memberId");
//			System.out.println("memberId =" + memberId);
		} catch (Exception e) {
			// TODO: handle exception
			String memberId = "";
		}

		try {
			String point = jsonobject.getString("point");
	//		System.out.println("point =" + point);
		} catch (Exception e) {
			// TODO: handle exception
			String point = "";
		}
		try {
			String pointExpiryDate = jsonobject.getString("pointExpiryDate");
	//		System.out.println("pointExpiryDate =" + pointExpiryDate);
		} catch (Exception e) {
			// TODO: handle exception
			String pointExpiryDate = "";
		}
		try {
			String posOrderType = jsonobject.getString("posOrderType");
	//		System.out.println("posOrderType =" + posOrderType);
		} catch (Exception e) {
			// TODO: handle exception
			String posOrderType = "";
		}

		try {
			String userNote = jsonobject.getString("userNote");
	//		System.out.println("userNote =" + userNote);
		} catch (Exception e) {
			// TODO: handle exception
			String userNote = "";
		}

		try {
			String payType = jsonobject.getString("payType");
	//		System.out.println("payType =" + payType);
		} catch (Exception e) {
			// TODO: handle exception
			String payType = "";
		}
		try {
			String isPayed = jsonobject.getString("isPayed");
	//		System.out.println("isPayed =" + isPayed);
		} catch (Exception e) {
			// TODO: handle exception
			String isPayed = "";
		}
		try {
			String paytime = jsonobject.getString("paytime");
	//		System.out.println("paytime =" + paytime);
		} catch (Exception e) {
			// TODO: handle exception
			String paytime = "";
		}
		try {
			String currency = jsonobject.getString("currency");
	//		System.out.println("currency =" + currency);
		} catch (Exception e) {
			// TODO: handle exception
			String currency = "";
		}
		try {
			String registerPhone = jsonobject.getString("registerPhone");
	//		System.out.println("registerPhone =" + registerPhone);
		} catch (Exception e) {
			// TODO: handle exception
			String registerPhone = "";
		}

		try {
			String isStoreFirstOrder = jsonobject.getString("isStoreFirstOrder");
	//		System.out.println("isStoreFirstOrder =" + isStoreFirstOrder);
		} catch (Exception e) {
			// TODO: handle exception
			String isStoreFirstOrder = "";
		}
		try {
			String isBrandFirstOrder = jsonobject.getString("isBrandFirstOrder");
	//		System.out.println("isBrandFirstOrder =" + isBrandFirstOrder);
		} catch (Exception e) {
			// TODO: handle exception
			String isBrandFirstOrder = "";
		}

		String storeId = jsonobject.getString("storeId");
		String orderNo = jsonobject.getString("orderNo");
		String originOrderId="";
		try {
			 originOrderId = jsonobject.getString("originOrderId");
	//		System.out.println("originOrderId =" + originOrderId);
		} catch (Exception e) {
			// TODO: handle exception
			 originOrderId ="" ;
		}

		String type = jsonobject.getString("type");

		String fromType = jsonobject.getString("fromType");

		String orderTime = jsonobject.getString("orderTime");
		String orderDate = jsonobject.getString("orderDate");
		int orderStatus = jsonobject.getInt("orderStatus");
		int orderType = jsonobject.getInt("orderType");
		boolean isPayed = jsonobject.getBoolean("isPayed");

		JSONObject paymentDetails = jsonobject.getJSONObject(jsonobject.getString("paymentDetails"));// 获取数组
//		System.out.println(jsonobject.getString("paymentDetails"));
		/*
		 * PaymentDetails
		 * details=(PaymentDetails)JSONObject.toBean(paymentDetails,
		 * PaymentDetails.class); System.out.println(details.toString());
		 */
		JSONArray jsonArray = JSONArray.fromObject(jsonobject.getString("paymentDetails")); // 位于data下面

		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject paymentDetail = jsonArray.getJSONObject(i);// 获取数组
			PaymentDetails details = (PaymentDetails) JSONObject.toBean(paymentDetail, PaymentDetails.class);

			try {
				details.insertPaymentDetails(orderId, details,orderDate,orderTime,originOrderId,storeId);
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

		try {
			boolean isInvoice = jsonobject.getBoolean("isInvoice");
//			System.out.println("isInvoice =" + isInvoice);
		} catch (Exception e) {
			// TODO: handle exception
			String isInvoice = "";
		}
		try {
			String invoiceDesc = jsonobject.getString("invoiceDesc");
	//		System.out.println("invoiceDesc =" + invoiceDesc);
		} catch (Exception e) {
			// TODO: handle exception
			String invoiceDesc = "";
		}
		try {
			String taxNo = jsonobject.getString("taxNo");
	//		System.out.println("taxNo =" + taxNo);
		} catch (Exception e) {
			// TODO: handle exception
			String taxNo = "";
		}
		try {
			float deliveryFee = jsonobject.getLong("deliveryFee");
	//		System.out.println("deliveryFee =" + deliveryFee);
		} catch (Exception e) {
			// TODO: handle exception
			String deliveryFee = "";
		}
		try {
			float productPrice = jsonobject.getLong("productPrice");
	//		System.out.println("productPrice =" + productPrice);
		} catch (Exception e) {
			// TODO: handle exception
			String productPrice = "";
		}
		try {
			float realTimeProductPrice = jsonobject.getLong("realTimeProductPrice");
	//		System.out.println("realTimeProductPrice =" + realTimeProductPrice);
		} catch (Exception e) {
			// TODO: handle exception
			String realTimeProductPrice = "";
		}

		try {
			float thirdPlatformBearPrice = jsonobject.getLong("thirdPlatformBearPrice");
	//		System.out.println("thirdPlatformBearPrice =" + thirdPlatformBearPrice);
		} catch (Exception e) {
			// TODO: handle exception
			String thirdPlatformBearPrice = "";
		}
		try {
			float commission = jsonobject.getLong("commission");
	//		System.out.println("commission =" + commission);
		} catch (Exception e) {
			// TODO: handle exception
			String commission = "";
		}
		try {
			String extra = jsonobject.getString("extra");
	//		System.out.println("extra =" + extra);
		} catch (Exception e) {
			// TODO: handle exception
			String extra = "";
		}
		try {
			String userId = jsonobject.getString("userId");
	//		System.out.println("userId =" + userId);
		} catch (Exception e) {
			// TODO: handle exception
			String userId = "";
		}
		try {
			String thirdUserId = jsonobject.getString("thirdUserId");
	//		System.out.println("thirdUserId =" + thirdUserId);
		} catch (Exception e) {
			// TODO: handle exception
			String thirdUserId = "";
		}

		try {
			String mealFee = jsonobject.getString("mealFee");
	//		System.out.println("mealFee =" + mealFee);
		} catch (Exception e) {
			// TODO: handle exception
			String mealFee = "";
		}

		String price = jsonobject.getString("price");

		String discountPrice = jsonobject.getString("discountPrice");
		String merchantBearPrice = jsonobject.getString("merchantBearPrice");
		String merchantPrice = jsonobject.getString("merchantPrice");

		try {
			JSONArray status = JSONArray.fromObject(jsonobject.getString("status")); // 位于data下面
//			System.out.println(jsonobject.getString("status"));
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
			// System.out.println("discount is " +
			// jsonobject.getString("discounts"));
			for (int i = 0; i < discountses.size(); i++) {
				// System.out.println("i is " + i);
				JSONObject discount = discountses.getJSONObject(i);// 获取数组

				Map disproductses = new HashMap();
				disproductses.put("disproducts", DisProducts.class);
				Discounts discount1 = (Discounts) JSONObject.toBean(discount, Discounts.class, disproductses);
				Discounts.insertDiscount(orderId, discount1,orderDate,orderTime);

				if (discount1.getDisProducts() != null) {
					// System.out.println("获取的
					// 优惠不为空"+discount1.getDisProducts().size());
					try {
						for (int j = 0; j < discount1.getDisProducts().size(); j++) {
							// Combos.insertCombos("",
							// product1.getCombos().get(j));
							// System.out.println("j is "+j);
							DisProducts.insertDisProducts(orderId, discount1.getDisProducts().get(j));
							// System.out.println("222222222");
						}

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		JSONArray products = JSONArray.fromObject(jsonobject.getString("products"));
	//	System.out.println(jsonobject.getString("products"));
		for (int i = 0; i < products.size(); i++) {
			JSONObject product = products.getJSONObject(i);// 获取数组
			// Products product1 = (Products) JSONObject.toBean(product,
			// Products.class);
			Map comboses = new HashMap();
			comboses.put("combos", Combos.class);
			comboses.put("skus", Skus.class);
			comboses.put("properties", Propertys.class);
			Products_goods product1 = (Products_goods) JSONObject.toBean(product, Products_goods.class, comboses);
	//		System.out.println(product1.getPrice());
			try {
				product1.insertProducts(orderId, product1,originOrderId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (product1.getSkus() != null) {
				try {
					for (int j = 0; j < product1.getSkus().size(); j++) {
						JSONArray pskus = JSONArray.fromObject(product1.getSkus()); // 位于discounts下面
						
						Skus skus = (Skus) JSONObject.toBean(pskus.getJSONObject(j),
								Skus.class);
						
						Skus.insertSkus(orderId, skus,product1.getPid());
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (product1.getPropertys() != null) {
				try {
					for (int j = 0; j < product1.getPropertys().size(); j++) {
						JSONArray properties = JSONArray.fromObject(product1.getPropertys()); // 位于discounts下面
		//				System.out.println("j is  " + j);
						Propertys propertys = (Propertys) JSONObject.toBean(properties.getJSONObject(j),
								Propertys.class);
						Propertys.insertPropertys(orderId, propertys,product1.getPid());
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (product1.getCombos() != null) {
				try {
					for (int j = 0; j < product1.getCombos().size(); j++) {
						JSONArray comboses1 = JSONArray.fromObject(product1.getCombos()); // 位于discounts下面
		//				System.out.println("j is  " + j);
						Combos combos = (Combos) JSONObject.toBean(comboses1.getJSONObject(j),
								Combos.class);
						Combos.insertCombos(orderId, combos,product1.getPid(),product1.getNum());
						//Combos.insertCombos(orderId, product1.getCombos().get(j), product1.getPid());
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (product1.getCombos().get(i).getSkus() != null) {
					try {
						for (int j = 0; j < product1.getCombos().get(i).getSkus().size(); j++) {
							JSONArray skuses = JSONArray.fromObject(product1.getCombos().get(j).getSkus()); // 位于discounts下面
							
							Skus skus = (Skus) JSONObject.toBean(skuses.getJSONObject(j),
									Skus.class);
						
							Skus.insertSkus(orderId, skus,product1.getPid());
								
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (product1.getCombos().get(i).getPropertys() != null) {
					try {
						for (int j = 0; j < product1.getCombos().get(i).getPropertys().size(); j++) {
							JSONArray compropertyes = JSONArray.fromObject(product1.getCombos().get(j).getPropertys()); // 位于discounts下面
							
							Propertys comproPropertys = (Propertys) JSONObject.toBean(compropertyes.getJSONObject(j),
									Propertys.class);
							Propertys.insertComPropertys(orderId, comproPropertys,product1.getPid(),product1.getCombos().get(j).getPid());
							
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		}

		try {
			Map orders = new HashMap();
			orders.put("paymentDetails", PaymentDetails.class);
			orders.put("status", Status.class);
			Order2 order1 = (Order2) JSONObject.toBean(jsonobject, Order2.class, orders);
			Order2.insertOrder(orderId, order1,title);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String order = Test2.readFileContent("/Users/jason/OneDrive - cd/项目资料/吉野家/order/1031/y1.txt").trim();
		//String order = Test.readFileContent("/Users/jason/OneDrive - cd/项目资料/吉野家/order/1028-jian24/test3.txt").trim();
//		System.out.println(order);
/*	     Statement stmt = DBUtil.getConnection().createStatement();
	        //ResultSet executeQuery(String sqlString)：执行查询数据库的SQL语句   ，返回一个结果集（ResultSet）对象。
	        ResultSet rs = stmt.executeQuery("select * from oldorder2 where 接口名称='1-16同步堂食订单' ");
	        while(rs.next()){//如果对象中有数据，就会循环打印出来
	            System.out.println(rs.getString("请求内容"));
	            Test.postDineorder(rs.getString("请求内容"));
	        }
		*/
		
		
		Test2.retailOrder("");
		
		
		
		//获取第二行第4列
	/*	String cell2 = sheet1.getExcelDateByIndex(1, 3);
		//根据第3列值为“customer23”的这一行，来获取该行第2列的值
		String cell3 = sheet1.getCellByCaseName("customer23", 2,1);
		System.out.println(cell2);
		System.out.println(cell3);
*/		
	}
}
