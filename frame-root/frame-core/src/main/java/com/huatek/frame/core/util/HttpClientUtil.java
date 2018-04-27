package com.huatek.frame.core.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

public class HttpClientUtil {
	/**
	 * 进行模拟GET请求方法
	 * @paramurl
	 * @return
	 */
	public static HttpClientResponseResult requestGet(String url){
		HttpClientResponseResult responseResult = new HttpClientResponseResult();
		//生成HttpClinet对象并设置参数
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//生成HttpGet对象并设置参数
		HttpGet httpGet = new HttpGet(url);
		//设置httpGet请求超时为 5 秒
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(5000).setConnectTimeout(5000).build();
		httpGet.setConfig(requestConfig);

		// 定义返回对象
		String jsonStr = "";
		//执行 HTTP GET 请求
		try {
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
			//获取响应编码
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			responseResult.setStatusCode(statusCode);
			//解析响应内容
			HttpEntity httpEntity = httpResponse.getEntity();
			jsonStr = EntityUtils.toString(httpEntity,"UTF-8");
			responseResult.setResponseContent(jsonStr);
		} catch (IOException e) {
			//发生网络异常
			
		} finally {
			//释放连接
			httpGet.releaseConnection();
			try {
				httpClient.close();
			} catch (IOException e) {
			
			}
		}
		return responseResult;
	}

	/**
	 * 进行模拟POST请求方法
	 * @paramurl
	 * @paramparams
	 * @return
	 */
	public static HttpClientResponseResult requestPost(String url, Map<String,String> paramMap){
	if(paramMap==null)
			return null;
		HttpClientResponseResult responseResult = new HttpClientResponseResult();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		String jsonStr = "";
		HttpEntity entity = null;  
		try {
			entity = new StringEntity(new Gson().toJson(paramMap),ContentType.APPLICATION_JSON);//new UrlEncodedFormEntity(params, "UTF-8");
			httpPost.setEntity(entity);
			CloseableHttpResponse httpResponse = null;
			httpResponse = httpClient.execute(httpPost); 
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			responseResult.setStatusCode(statusCode);
			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {  
				jsonStr = EntityUtils.toString(httpEntity,"UTF-8");
				responseResult.setResponseContent(jsonStr);
            } 
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			httpPost.releaseConnection();
			try {
				httpClient.close();
			} catch (IOException e) {
			}
		}
		return responseResult;
}

	/**
	 * 进行模拟POST form请求方法
	 * @paramurl
	 * @paramparams
	 * @return
	 */
	public static HttpClientResponseResult requestFormPost(String url, Map<String,String> paramMap){
	if(paramMap==null)
			return null;
		HttpClientResponseResult responseResult = new HttpClientResponseResult();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		String jsonStr = "";
		List<NameValuePair> formparams = new ArrayList<NameValuePair>(); 
		
		
		if(paramMap!=null&&!paramMap.isEmpty()){
			Set<Entry<String, String>> entrySet = paramMap.entrySet();
			Iterator<Entry<String, String>> entryIt = entrySet.iterator();
			while(entryIt.hasNext()){
				Entry<String, String> entry = entryIt.next();
				formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));  
			}
		}
		
        UrlEncodedFormEntity uefEntity=null; 
		try {
			httpPost.setHeader("Content-type","application/x-www-form-urlencoded");
			uefEntity = new UrlEncodedFormEntity(formparams, "ISO-8859-1");  
			httpPost.setEntity(uefEntity);
			CloseableHttpResponse httpResponse = null;
			httpResponse = httpClient.execute(httpPost); 
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			responseResult.setStatusCode(statusCode);
			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {  
				jsonStr = EntityUtils.toString(httpEntity,"UTF-8");
				responseResult.setResponseContent(jsonStr);
            } 
		} catch (IOException e) {
		}finally{
			httpPost.releaseConnection();
			try {
				httpClient.close();
			} catch (IOException e) {
			}
		}
		return responseResult;
}
	
	
	/**
	 * 进行模拟POST请求方法
	 * @paramurl
	 * @paramparams
	 * @return
	 */
	public static HttpClientResponseResult requestPost(String url,String timestamp,String method,String sign,String paramMap){
		HttpClientResponseResult responseResult = new HttpClientResponseResult();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		String jsonStr = "";
		HttpEntity entity = null;  
		try {
			entity = new StringEntity(paramMap,ContentType.APPLICATION_JSON);//new UrlEncodedFormEntity(params, "UTF-8");
			httpPost.setHeader("timestamp", timestamp);
			httpPost.setHeader("method", method);
			httpPost.setHeader("sign", sign);
			httpPost.setEntity(entity);
			CloseableHttpResponse httpResponse = null;
			httpResponse = httpClient.execute(httpPost); 
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			responseResult.setStatusCode(statusCode);
			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {  
				jsonStr = EntityUtils.toString(httpEntity,"UTF-8");
				responseResult.setResponseContent(jsonStr);
            } 
		} catch (IOException e) {
		}finally{
			httpPost.releaseConnection();
			try {
				httpClient.close();
			} catch (IOException e) {
			}
		}
		return responseResult;
}	
	
}
