package com.example.ad.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.ad.domain.Output;

@Controller
public class BreedController {
	
	@RequestMapping(value = "/checkBreed")
	public String add() 
	{
		return "checkBreed";
	}
	
	@RequestMapping(value = "/checkBreedsave")
	public String save(@RequestParam("fileImage") MultipartFile multipartFile, Model model) throws IllegalStateException, IOException 
	{
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
		multipartFile.transferTo(convFile);
		byte[] fileContent = FileUtils.readFileToByteArray(convFile);
		String encodedString = Base64.getEncoder().encodeToString(fileContent);
		
		List<String> result = mlMethod(fileContent);
		List<Output> links = convertLink(result);
		
		model.addAttribute("image", encodedString);
		model.addAttribute("links", links);
		return "resultBreed";
	}
	
	public List<String> mlMethod(byte[] fileContent) {
		List<String> output = new ArrayList<String>();
		String adding = "";
        try{
        	String attachmentName = "image";
        	String attachmentFileName = "androidFlask.jpg";
        	String crlf = "\r\n";
        	String twoHyphens = "--";
        	String boundary =  "*****";
        	
        	HttpURLConnection httpUrlConnection = null;
        	URL url = new URL("https://tut-api-37p4k2zaiq-uc.a.run.app/predict/");
        	httpUrlConnection = (HttpURLConnection) url.openConnection();
        	httpUrlConnection.setUseCaches(false);
        	httpUrlConnection.setDoOutput(true);

        	httpUrlConnection.setRequestMethod("POST");
        	httpUrlConnection.setRequestProperty("Connection", "Keep-Alive");
        	httpUrlConnection.setRequestProperty("Cache-Control", "no-cache");
        	httpUrlConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
        	
        	DataOutputStream request = new DataOutputStream(
        		    httpUrlConnection.getOutputStream());

        		request.writeBytes(twoHyphens + boundary + crlf);
        		request.writeBytes("Content-Disposition: form-data; name=\"" + attachmentName + "\";filename=\"" + attachmentFileName + "\"" + crlf);
        		request.writeBytes(crlf);
        		request.write(fileContent);
        		
        		request.writeBytes(crlf);
        		request.writeBytes(twoHyphens + boundary + twoHyphens + crlf);
        		
        		request.flush();
        		request.close();
        		
        	
        		InputStream responseStream = new 
        			    BufferedInputStream(httpUrlConnection.getInputStream());

        			BufferedReader responseStreamReader = 
        			    new BufferedReader(new InputStreamReader(responseStream));

        			String line = "";
        			StringBuilder stringBuilder = new StringBuilder();

        			while ((line = responseStreamReader.readLine()) != null) {
        			    stringBuilder.append(line).append("\n");
        			}
        			responseStreamReader.close();

        			String response = stringBuilder.toString();
        			System.out.println(response);
        			adding = response;
        			responseStream.close();
        			httpUrlConnection.disconnect();
        	
            
            } catch (MalformedURLException e) {
            	e.printStackTrace();
            }catch (IOException e){
            	e.printStackTrace();
            }finally
        		{
            
        }
        String str[] = adding.split("&");
        output = Arrays.asList(str);
        return output;
	}
	
	public List<Output> convertLink(List<String> result){
		
		List<Output> output = new ArrayList<Output>();
		
		for(int i=0;i<result.size()-1;i++) {
			Output solo = new Output();
			String outcome = result.get(i).substring(22, (result.get(i).length())-26);
			String number = result.get(i).substring((result.get(i).length())-8, (result.get(i).length())-1);
			double num = Double.parseDouble(number) * 100;
			double num1 = num*10;
			double num2 = Math.round(num1);
			double finn = num2/10;
			if (finn == 0.0) {
				continue;
			}
			String result1 = outcome + " with " + finn + "% confidence.";
			String[] outcome1 = outcome.split(" ");
			List<String> outcome2 = Arrays.asList(outcome1);
			String fin = "https://www.google.com/search?q=";
			for(int j = 0;j<outcome2.size();j++) {
				fin +=outcome2.get(j);
				if(j != outcome2.size()-1) {
					fin += "+";
				}
			}
			solo.setBreed(result1);
			solo.setLink(fin);
			output.add(solo);
		}return output;
	}
}
