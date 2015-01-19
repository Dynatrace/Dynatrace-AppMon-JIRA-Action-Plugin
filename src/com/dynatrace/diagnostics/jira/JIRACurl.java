package com.dynatrace.diagnostics.jira;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;


public class JIRACurl {	
	private static final Logger log = Logger.getLogger(CreateIssueActionPlugin.class.getName());
	
	String data;
	String user;
	String password;
	String url;
		
	
	JIRACurl(String data, String user, String password, String url)
	{		
		this.data = data;
		this.password = password;
		this.user = user;
		this.url = url;
	}	
	
	Process process;	
     
     public void createTicket()
     {    
    	BufferedReader in;    	
    	String text;
     	try {		
     		
         	String command = "curl -D- -u " + user +":" + password + " -k  -X POST --data @/tmp/" + data + " -H Content-Type:application/json " + url;
         	String logCommand = "curl -D- -u " + user +": **********" + " -k  -X POST --data @/tmp/" + data + " -H Content-Type:application/json " + url;
         	log.info("curlcommand=\""+logCommand+"\"");
         	process = Runtime.getRuntime().exec(command);	
         	 in = new BufferedReader(
         	           new InputStreamReader(process.getInputStream()));
         	        while ((text = in.readLine()) != null) {
         	          log.info(text);
         	        }
         	
 
         } catch (IOException e) {  
             e.printStackTrace();
             log.warning("message=\""+e.getStackTrace().toString()+"\"");
         }  
     }

}
