package com.dynatrace.diagnostics.jira;
	import java.io.File;
import java.io.FileWriter;
	import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import sun.tools.jar.CommandLine;
	
	
public class PropertiesWriter {
	private static final Logger log = Logger.getLogger(CreateIssueActionPlugin.class.getName());
	String description;
	String summary;
	String data;
	String projectId;
	String issueType;
	String label;
	
	PropertiesWriter()
	{
		
	}
	
	PropertiesWriter(String summary, String description, String dateiname, String projectId, String issueType, String label)
	{
		this.summary = summary;
		this.description = description;
		this.data = dateiname;
		this.projectId = projectId;
		this.issueType = issueType;
		this.label = label;
	} 
	
	public String createLabel(String label)
	{		
		label = label.replaceAll(",","\",\"");
		label = "\""+ label +"\"";
		return label;
		
	}
	
	
	public void writeProperties()
	{		
		log.info("message=\"Start creation of data\" in=\"/tmp/"+data + "\"");
		PrintWriter pWriter;
		try {
			pWriter = new PrintWriter(new FileWriter(new File("/tmp", data)));
			log.info("message=\"Data created\" in =\"/tmp/" +data + "\"");
			pWriter.println("{\"fields\": {");
			pWriter.println("\t\"project\":");
			pWriter.println("\t{ \"id\": \""+ projectId + "\" },");
			pWriter.println("\t\"labels\": [" + createLabel(label) + "],");
			pWriter.println("\t\"summary\": \""+ summary +"\",");
			pWriter.println("\t\"description\": \""+ description +"\",");
			pWriter.println("\t\"issuetype\":{");
			pWriter.println("\t\t\"id\": \""+ issueType +"\"}");
			pWriter.println("\t}");
			pWriter.println("}");
			
	        pWriter.flush();
		} catch (IOException e) {			
			e.printStackTrace();
			log.warning("exception=\"" +e.getStackTrace().toString()+"\"");
		}        
	}    


}
