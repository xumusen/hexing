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
import com.entity.PaymentDetails;
import com.entity.Status;
import com.entity.product.Combos;
import com.entity.product.DisProducts;
import com.entity.product.Discounts;
import com.entity.product.Products;
import com.entity.product.Propertys;
import com.entity.product.Skus;

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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String order = Test.readFileContent("/Users/jason/OneDrive - cd/项目资料/吉野家/order/1029/test3.txt").trim();

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

		JSONObject jsonobject = JSONObject.fromObject(jsonobj.getString("data"));// 将字符串转化成json对象

		System.out.println(jsonobj.getString("data"));
		String orderId = jsonobject.getString("orderId");

		// String thirdSn=jsonobject.getString("thirdSn");

		if (jsonobject == null || jsonobject.isEmpty() || jsonobject.isNullObject() || "null".equals(jsonobject)) {
			System.out.println("订单数据有问题");
		}

		try {
			String thirdSn = jsonobject.getString("thirdSn");
			System.out.println("thirdSn =" + thirdSn);
		} catch (Exception e) {
			// TODO: handle exception
			String thirdSn = "";
		}
		try {
			String name = jsonobject.getString("name");
			System.out.println("name =" + name);
		} catch (Exception e) {
			// TODO: handle exception
			String name = "";
		}
		try {
			String phone = jsonobject.getString("phone");
			System.out.println("phone =" + phone);
		} catch (Exception e) {
			// TODO: handle exception
			String phone = "";
		}

		try {
			String takeNo = jsonobject.getString("takeNo");
			System.out.println("takeNo =" + takeNo);
		} catch (Exception e) {
			// TODO: handle exception
			String takeNo = "";
		}
		try {
			String tableNum = jsonobject.getString("tableNum");
			System.out.println("tableNum =" + tableNum);
		} catch (Exception e) {
			// TODO: handle exception
			String tableNum = "";
		}
		try {
			int peopleNum = jsonobject.getInt("peopleNum");
			System.out.println("peopleNum =" + peopleNum);
		} catch (Exception e) {
			// TODO: handle exception
			String peopleNum = "";
		}

		try {
			String deviceNo = jsonobject.getString("deviceNo");
			System.out.println("deviceNo =" + deviceNo);
		} catch (Exception e) {
			// TODO: handle exception
			String deviceNo = "";
		}
		try {
			String staffId = jsonobject.getString("staffId");
			System.out.println("staffId =" + staffId);
		} catch (Exception e) {
			// TODO: handle exception
			String staffId = "";
		}

		try {
			String staffNo = jsonobject.getString("staffNo");
			System.out.println("staffNo =" + staffNo);
		} catch (Exception e) {
			// TODO: handle exception
			String staffNo = "";
		}

		try {
			String staffBane = jsonobject.getString("staffBane");
			System.out.println("staffBane =" + staffBane);
		} catch (Exception e) {
			// TODO: handle exception
			String staffBane = "";
		}

		try {
			String memberId = jsonobject.getString("memberId");
			System.out.println("memberId =" + memberId);
		} catch (Exception e) {
			// TODO: handle exception
			String memberId = "";
		}

		try {
			String point = jsonobject.getString("point");
			System.out.println("point =" + point);
		} catch (Exception e) {
			// TODO: handle exception
			String point = "";
		}
		try {
			String pointExpiryDate = jsonobject.getString("pointExpiryDate");
			System.out.println("pointExpiryDate =" + pointExpiryDate);
		} catch (Exception e) {
			// TODO: handle exception
			String pointExpiryDate = "";
		}
		try {
			String posOrderType = jsonobject.getString("posOrderType");
			System.out.println("posOrderType =" + posOrderType);
		} catch (Exception e) {
			// TODO: handle exception
			String posOrderType = "";
		}

		try {
			String userNote = jsonobject.getString("userNote");
			System.out.println("userNote =" + userNote);
		} catch (Exception e) {
			// TODO: handle exception
			String userNote = "";
		}

		try {
			String payType = jsonobject.getString("payType");
			System.out.println("payType =" + payType);
		} catch (Exception e) {
			// TODO: handle exception
			String payType = "";
		}

		String storeId = jsonobject.getString("storeId");
		String fromType = jsonobject.getString("fromType");

		String orderTime = jsonobject.getString("orderTime");
		String orderDate = jsonobject.getString("orderDate");
		int orderStatus = jsonobject.getInt("orderStatus");
		int orderType = jsonobject.getInt("orderType");
		boolean isPayed = jsonobject.getBoolean("isPayed");

		JSONObject paymentDetails = jsonobject.getJSONObject(jsonobject.getString("paymentDetails"));// 获取数组
		System.out.println(jsonobject.getString("paymentDetails"));
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
				details.insertPaymentDetails(orderId, details);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("payType   " + details.getPayType());
			System.out.println("type   " + details.getType());
			System.out.println("money   " + details.getMoney());
			System.out.println("typeName   " + details.getTypeName());

		}

		try {
			boolean isInvoice = jsonobject.getBoolean("isInvoice");
			System.out.println("isInvoice =" + isInvoice);
		} catch (Exception e) {
			// TODO: handle exception
			String isInvoice = "";
		}
		try {
			String invoiceDesc = jsonobject.getString("invoiceDesc");
			System.out.println("invoiceDesc =" + invoiceDesc);
		} catch (Exception e) {
			// TODO: handle exception
			String invoiceDesc = "";
		}
		try {
			String taxNo = jsonobject.getString("taxNo");
			System.out.println("taxNo =" + taxNo);
		} catch (Exception e) {
			// TODO: handle exception
			String taxNo = "";
		}
		try {
			float deliveryFee = jsonobject.getLong("deliveryFee");
			System.out.println("deliveryFee =" + deliveryFee);
		} catch (Exception e) {
			// TODO: handle exception
			String deliveryFee = "";
		}
		try {
			float productPrice = jsonobject.getLong("productPrice");
			System.out.println("productPrice =" + productPrice);
		} catch (Exception e) {
			// TODO: handle exception
			String productPrice = "";
		}
		try {
			float thirdPlatformBearPrice = jsonobject.getLong("thirdPlatformBearPrice");
			System.out.println("thirdPlatformBearPrice =" + thirdPlatformBearPrice);
		} catch (Exception e) {
			// TODO: handle exception
			String thirdPlatformBearPrice = "";
		}
		try {
			float commission = jsonobject.getLong("commission");
			System.out.println("commission =" + commission);
		} catch (Exception e) {
			// TODO: handle exception
			String commission = "";
		}
		try {
			String extra = jsonobject.getString("extra");
			System.out.println("extra =" + extra);
		} catch (Exception e) {
			// TODO: handle exception
			String extra = "";
		}
		float price = jsonobject.getLong("price");
		float mealFee = jsonobject.getLong("mealFee");
		float discountPrice = jsonobject.getLong("discountPrice");
		float merchantBearPrice = jsonobject.getLong("merchantBearPrice");
		float merchantPrice = jsonobject.getLong("merchantPrice");

		try {
			JSONArray status = JSONArray.fromObject(jsonobject.getString("status")); // 位于data下面
			System.out.println(jsonobject.getString("status"));
			for (int i = 0; i < status.size(); i++) {
				JSONObject statuses = status.getJSONObject(i);// 获取数组
				Status statuse1 = (Status) JSONObject.toBean(statuses, Status.class);
				Status.updateStatus(orderId, statuse1);
				System.out.println(statuse1.getTitle());
				System.out.println(statuse1.getValue());
				System.out.println(statuse1.getDateTime());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			JSONArray discountses = JSONArray.fromObject(jsonobject.getString("discounts")); // 位于data下面
			//System.out.println("discount is  " + jsonobject.getString("discounts"));
			for (int i = 0; i < discountses.size(); i++) {
				//System.out.println("i is " + i);
				JSONObject discount = discountses.getJSONObject(i);// 获取数组

				Map disproductses = new HashMap();
				disproductses.put("disproducts", DisProducts.class);
				Discounts discount1 = (Discounts) JSONObject.toBean(discount, Discounts.class, disproductses);
				Discounts.insertDiscount(orderId, discount1);

				if (discount1.getDisProducts() != null) {
				//	System.out.println("获取的 优惠不为空"+discount1.getDisProducts().size());
					try {
						for (int j = 0; j < discount1.getDisProducts().size(); j++) {
							// Combos.insertCombos("",
							// product1.getCombos().get(j));
					//		System.out.println("j is  "+j);
							DisProducts.insertDisProducts(orderId, discount1.getDisProducts().get(j));
						//	System.out.println("222222222");
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
		System.out.println(jsonobject.getString("products"));
		for (int i = 0; i < products.size(); i++) {
			JSONObject product = products.getJSONObject(i);// 获取数组
			// Products product1 = (Products) JSONObject.toBean(product,
			// Products.class);
			Map comboses = new HashMap();
			comboses.put("combos", Combos.class);
			comboses.put("skus", Skus.class);
			comboses.put("properties", Propertys.class);
			Products product1 = (Products) JSONObject.toBean(product, Products.class, comboses);
			System.out.println(product1.getPrice());
			try {
				product1.insertProducts(orderId, product1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (product1.getSkus() != null) {
				try {
					for (int j = 0; j < product1.getSkus().size(); j++) {
						Skus.insertSkus(orderId, product1.getSkus().get(j));
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (product1.getPropertys() != null) {
				try {
					for (int j = 0; j < product1.getPropertys().size(); j++) {
						Propertys.insertPropertys(orderId, product1.getPropertys().get(j));
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (product1.getCombos() != null) {
				try {
					for (int j = 0; j < product1.getCombos().size(); j++) {
						Combos.insertCombos(orderId, product1.getCombos().get(j),product1.getPid());
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(product1.getCombos().get(i).getName());
				System.out.println(product1.getCombos().get(i).getPid());
				System.out.println(product1.getCombos().get(i).getNum());
				System.out.println(product1.getCombos().get(i).getAddPrice());
				System.out.println(product1.getCombos().get(i).getPrice());
				if (product1.getCombos().get(i).getSkus() != null) {
					try {
						for (int j = 0; j < product1.getCombos().get(i).getSkus().size(); j++) {
							Skus.insertSkus(orderId, product1.getCombos().get(i).getSkus().get(j));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					/*
					 * System.out.println("哈哈哈");
					 * System.out.println(product1.getCombos().get(i).getSkus().
					 * get(i).getTitle());
					 * 
					 */
				}

			}

		}

		try {
			Map orders = new HashMap();
			orders.put("paymentDetails", PaymentDetails.class);
			orders.put("status", Status.class);
			Order order1 = (Order) JSONObject.toBean(jsonobject, Order.class, orders);
			Order.insertOrder(orderId, order1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
