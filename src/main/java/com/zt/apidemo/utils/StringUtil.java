package com.zt.apidemo.utils;

import java.util.ArrayList;
import java.util.List;





public class StringUtil{

	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}
	public static boolean isEmpty(Object obj) {
		return obj == null || objToString(obj).length() == 0;
	}
	/**
	 * 长度不足补（左）
	 * @param orgStr
	 * @param chr
	 * @param len
	 * @return
	 */
	public static String addLeftChar(String orgStr,String chr,int len){
		String ret=orgStr;
		if(orgStr.length()<len){
			for(int i=0;i<(len-orgStr.length());i++){
				ret=chr+ret;
			}
			
		}
		return ret;
	}
	/**
	 * 长度不足补（右边）
	 * @param orgStr
	 * @param chr
	 * @param len
	 * @return
	 */
	public static String addrightChar(String orgStr,String chr,int len){
		String ret=orgStr;
		if(orgStr.length()<len){
			for(int i=0;i<(len-orgStr.length());i++){
				ret=ret+chr;
			}
			
		}
		return ret;
	}
	
	/**
	 * 移除特定字符（右边）
	 * @param orgStr 
	 * @param chr
	 * @return
	 */
	public static String removeRightChar(String orgStr,String chr){
		if(orgStr.endsWith(chr)){
			orgStr=orgStr.substring(0, orgStr.length()-1);
			orgStr=removeRightChar(orgStr,chr);
		};
		return orgStr;
	}
	
	/**
	 * 移除特定字符（右边）
	 * @param orgStr 源字符串
	 * @param str 去除字符串
	 * @return 字符串
	 */
	public static String removeRightStr(String orgStr,String str){
		if(orgStr.endsWith(str)){
			orgStr=orgStr.substring(0, orgStr.length()-str.length());
			orgStr=removeRightStr(orgStr,str);
		};
		return orgStr;
	}
	
	/*public static void main(String[] args) {
		System.out.println(removeRightStr("sadyyutyut","yut"));
	}*/
	

	public static List<String> strToList(String[] str){
			List<String> list=new ArrayList<>();
		  for (int i = 0; i < str.length; i++){
			  list.add(str[i]);
		  }
		  return list;
	}
	

	/*
	 * 数组去重 （ 从listbase 去除 removelist 已存在的元素，返回一个新数组）
	 * 
	 * @param listbase 对比数组
	 * 
	 * @param removelist 比较数组(需要删除的集合)
	 */
	public static List<String> removeAllList(List<String> listbase, List<String> removelist) {
		List<String> retList = new ArrayList<>();
		for (String itemBase : listbase) {
			if (!removelist.contains(itemBase)) {
				retList.add(itemBase);
			}
		}

		return retList;

	}


	/**
	 * 获取文件名后缀
	 * @param fileName
	 * @return
	 */
	public static String getExtension(String fileName) {
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
		return ext;
	}
	
	/**
	 * 清除HTML标签及转义字符
	 * @param html
	 * @return
	 */
	public static String removeHTMLTag(String html) {
		if(html == null) {
			return "";
		}
		
		String s = html;
		/** 清除普通标签 **/
		s = s.replaceAll("<(S*?)[^>]*>.*?|<.*? />", "");
		/** 清除转义字符 **/
		s = s.replaceAll("&.{2,6}?;", "");
		return s;
	}

	public static String objToString(Object obj) {
	
		return obj == null || "".equals(obj.toString())?"":obj.toString();
	}
	
	public static String emptyStrReplace(String str,String replaceStr) {
		return isEmpty(str)?replaceStr:str;
	}
	
	/**
	 * 大于0
	* @author zting 
	* @date 2020年1月10日 下午4:22:34
	* @Description: TODO 
	* @param value
	* @return    
	* @return boolean   
	*
	 */
	public static boolean objGTZero(Integer value) {
		return value==null||value<1?false:true;
	}
	/**
	 * 是否未空
	* @author zting 
	* @date 2020年1月10日 下午4:30:26
	* @Description: TODO 
	* @param value
	* @return    
	* @return boolean   
	*
	 */
	public static boolean isSpanceOrEmpty(String value) {
		
		return value == null || value.trim().length() == 0;
		
	}
	
	public static Integer isZoreReplace(Integer value,Integer repVlaue) {
		
		return  value==null||value<1?repVlaue:value;
		
	}
	
	public static String isEmptyReplace(String value,String repValue) {
		return isEmpty(value)?repValue:value;
		
	}
}