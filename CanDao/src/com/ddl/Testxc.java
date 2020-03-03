package com.ddl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.entity.product.Category;
import com.entity.product.Combos;
import com.entity.product.Dept;
import com.entity.product.ProductCategory;
import com.entity.product.ProductContent;
import com.entity.product.Products;
import com.entity.product.Propertys;
import com.entity.product.Skus;
import com.entity.product.SubDept;
import com.entity.product.Types;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Testxc {

	public static void postDineorder(String orderId,String storeId,String productscollect) throws SQLException{
		
		//JSONObject jsonobj = JSONObject.fromObject(productscollect);

	JSONArray products = JSONArray.fromObject(productscollect);
	//System.out.println("products is " + jsonobject.getString("products"));
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
		comboses.put("procuctContent", ProductContent.class);
		comboses.put("procuctCategory", ProductCategory.class);
		Products product1 = (Products) JSONObject.toBean(product, Products.class, comboses);
	//	System.out.println(product1.getPrice());
		try {
			product1.insertProducts(orderId, product1,storeId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONObject deptobject = JSONObject.fromObject(product1.getDept());// 将字符串转化成json对象
		JSONObject typesobject = JSONObject.fromObject(product1.getTypes());// 将字符串转化成json对象
		JSONObject categoryobject = JSONObject.fromObject(product1.getCategory());// 将字符串转化成json对象
		JSONObject procuctContentobject = JSONObject.fromObject(product1.getProductContent());// 将字符串转化成json对象
		JSONObject procuctCategoryobject = JSONObject.fromObject(product1.getProductCategory());// 将字符串转化成json对象

		if (deptobject == null || deptobject.isEmpty() || deptobject.isNullObject()
				|| "null".equals(deptobject)) {
		//	System.out.println("deptobject is 空");
		}else{
			Map demap = new HashMap();
			demap.put("subDept", SubDept.class);
			Dept dept = (Dept) JSONObject.toBean(deptobject, Dept.class, demap);
			Dept.insertDept(orderId, dept, product1.getPid());
			if (dept.getSubDept() != null) {
				JSONObject subDeptobject = JSONObject.fromObject(dept.getSubDept());
				SubDept subDept = (SubDept) JSONObject.toBean(subDeptobject, SubDept.class);
				SubDept.insertSubDept(orderId, subDept, dept.getId(), product1.getPid());
			}

		}

		if (typesobject == null || typesobject.isEmpty() || typesobject.isNullObject()
				|| "null".equals(typesobject)) {
		//	System.out.println("typesobject is 空");
		} else {
			//System.out.println("types start ");
			Types types = (Types) JSONObject.toBean(typesobject, Types.class);
			Types.insertTypes(orderId, types, product1.getPid());
			//System.out.println("types end ");
		}
		if (categoryobject == null || categoryobject.isEmpty() || categoryobject.isNullObject()
				|| "null".equals(categoryobject)) {
		//	System.out.println("categoryobject is 空");
		} else {
		//	System.out.println("category start ");
			Category category = (Category) JSONObject.toBean(categoryobject, Category.class);
			Category.insertCategory(orderId, category, product1.getPid());
			//System.out.println("category end ");
		}
		if (procuctContentobject == null || procuctContentobject.isEmpty() || procuctContentobject.isNullObject()
				|| "null".equals(procuctContentobject)) {
			//System.out.println("procuctContentobject is 空");
		} else {
			//System.out.println("procuctContent start ");
			ProductContent procuctContent = (ProductContent) JSONObject.toBean(procuctContentobject,
					ProductContent.class);
			ProductContent.insertProductContent(orderId, procuctContent, product1.getPid());
			//System.out.println("procuctContent end ");
		}
		if (procuctCategoryobject == null || procuctCategoryobject.isEmpty() || procuctCategoryobject.isNullObject()
				|| "null".equals(procuctCategoryobject)) {
			//System.out.println("procuctCategoryobject is 空");
		} else {
		//	System.out.println("procuctCategory start ");
			ProductCategory procuctCategory = (ProductCategory) JSONObject.toBean(procuctCategoryobject,
					ProductCategory.class);
			ProductCategory.insertProductCategory(orderId, procuctCategory, product1.getPid());
		//	System.out.println("procuctCategory end ");
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
			//	System.out.println(" properties is    "+product1.getPropertys().size());
				for (int j = 0; j < product1.getPropertys().size(); j++) {
					JSONArray properties = JSONArray.fromObject(product1.getPropertys()); // 位于discounts下面
				//	System.out.println("j is  " + j);
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
				//	System.out.println("j is  " + j);
					Combos combos = (Combos) JSONObject.toBean(comboses1.getJSONObject(j),
							Combos.class);
					Combos.insertCombos(orderId, combos,product1.getPid(),product1.getNum(),storeId);

					if (product1.getCombos().get(j).getSkus() != null) {
						try {
							for (int k = 0; k < product1.getCombos().get(j).getSkus().size(); k++) {
								
								JSONArray skuses = JSONArray.fromObject(product1.getCombos().get(j).getSkus()); // 位于discounts下面
							
								Skus skus = (Skus) JSONObject.toBean(skuses.getJSONObject(k),
										Skus.class);
								//Combos.insertCombos(orderId, combos,product1.getPid());
								Skus.insertSkus(orderId, skus,product1.getPid());
									
						
							
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					
					if (product1.getCombos().get(j).getPropertys() != null) {
						try {
							for (int k = 0; k < product1.getCombos().get(j).getPropertys().size(); k++) {
								
								JSONArray compropertyes = JSONArray.fromObject(product1.getCombos().get(j).getPropertys()); // 位于discounts下面
							
								Propertys comproPropertys = (Propertys) JSONObject.toBean(compropertyes.getJSONObject(k),
										Propertys.class);
								Propertys.insertComPropertys(orderId, comproPropertys,product1.getPid(),product1.getCombos().get(j).getPid());
					
									
						
							
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

	}
	}
}
