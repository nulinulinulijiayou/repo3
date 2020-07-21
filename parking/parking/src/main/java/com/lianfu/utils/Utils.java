package com.lianfu.utils;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class Utils {
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
	public static <T> T toBean(Map map, Class<T> c) {
		T bean = null;
		try {
			bean = c.newInstance();
			ConvertUtils.register(new Converter() {
				public Object convert(Class type, Object value) {
					if (type != Date.class) {
						return null;
					}
					String val = (String) value;
					if(val == null || val.trim().isEmpty()){
						return null;
					}
					SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
					try {
						return sdf.parse(val);
					} catch (ParseException e) {
						throw new RuntimeException(e);
					}
				}
			}, Date.class);
			BeanUtils.populate(bean, map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

	public static <T> Object[] toObjects(T t) throws Exception {
		Class c = t.getClass();
		Field[] fs = c.getDeclaredFields();
		Object[] obs = new Object[fs.length];
		for (int i = 0; i < fs.length; i++) {
			fs[i].setAccessible(true);
			obs[i] = fs[i].get(t);
		}
		return obs;
	}
/*
	public static Map getParameterMap(ServletContext application,
                                      HttpServletRequest request, String fName) {
		Map<String, String> map = new HashMap<String, String>();
		// 无情三连 得到好多好多个FileItem
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setHeaderEncoding("utf-8");
		try {
			List<FileItem> items = sfu.parseRequest(request);
			// 把浏览器上传过来的图片保存到pictures文件夹下
			for (FileItem f : items) {
				// 普通表单项如何处理
				if (f.isFormField()) {
					// 把属性名，属性的值给它保存到map集合中
					String str = map.get(f.getFieldName());
					if (str == null) {
						map.put(f.getFieldName(), f.getString("utf-8"));
					} else {
						map.put(f.getFieldName(),
								str + "," + f.getString("utf-8"));
					}

				}
				// 文件表单项如何处理
				else {
					if(f.getName()!=null && !f.getName().trim().isEmpty()){
						String[] strs = f.getName().split("\\.");
						String string = "." + strs[strs.length - 1];
						String fileName = com.lianfu.yuehuoli.util.Utils.uuid() + string;
						// savePath
						// D:\apache-tomcat-7.0.72-windows-x64\apache-tomcat-7.0.72\webapps\day12_pic\pictures
						String savePath = application.getRealPath(fName);
						File file = new File(savePath, fileName);
						f.write(file);
						String contextPath = application.getContextPath();

						// 保存将来的存储路径（/项目名/文件夹名/图片名）
						map.put(f.getFieldName(), contextPath + "/" + fName + "/"
								+ fileName);
					}
					
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}*/

}
