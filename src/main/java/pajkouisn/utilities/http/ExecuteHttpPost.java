package pajkouisn.utilities.http;


import java.util.Calendar;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.json.simple.JSONObject;

import pajkouisn.utilities.constants.Constants;

import static pajkouisn.utilities.json.JSONOperations.toJSONObject;

/**	
 * 	This class is used to execute HTTP requests.
 *	It also has methods to provide response, status and time for execution.
 * 
 * 	@author kartiklaw@gmail.com (www.kartik-reddy.com)
 */

public class ExecuteHttpPost 
{
	/*	Input parameters: 
	 * 	url	-->	endpoint url where request will be sent.
	 * 	request	-->	Request to be sent to the endpoint.
	 */
	private String request;
	private String url;
	
	/*	Output Values:
	 * 	output	-->	Stores the response object.
	 * 	status 	-->	Status code of request.
	 * 	runTime	-->	How long the request took to execute.
	 * 	complexOutput	-->	Returns a combination of all the parameters. 
	 * 	unknownFormatOutput	-->	If the response is not a JSON, then use this format.
	 */
	private JSONObject output;
	private JSONObject complexOutput;
	private int statusCode;
	private long runTime;
	private String unformattedOutput;
	
	
	/**	
	 * 	Default parameterized constructor.	
	 * 
	 * 	@param request
	 * 	@param preprocess
	 * 	@param url
	 */
	public ExecuteHttpPost(String request, String url)
	{
		this.request = request;
		this.url = url;
		
		output = new JSONObject();
		complexOutput = new JSONObject();
		statusCode = 0;
		runTime = 0;
		unformattedOutput = null;
	}

	
	/**
	 * 	Invoke this function to make a post HTTP call.
	 */
	@SuppressWarnings("unchecked")
	public void executeRequest()
	{
		// 	HTTP Client and post method for sending request to the URL.
		HttpClient httpClient = new HttpClient();
		PostMethod mPost = new PostMethod(url);
		Header methodHeader = new Header();
		
		try
		{
			methodHeader.setName("content-type");
		    methodHeader.setValue("application/json");
		    methodHeader.setName("accept");
		    methodHeader.setValue("application/json");
		    mPost.addRequestHeader(methodHeader);
				
			//	Set the response as the entity value.
			RequestEntity entity = new StringRequestEntity(request, "application/json", "ISO-8859-1");
		    mPost.setRequestEntity(entity);
		      
		    /* 	Execute the request.
		     * 	Record the start and end time so that the total time to execute request may be calculated. 
		     */
		    final long startTime = Calendar.getInstance().getTimeInMillis();
			statusCode = httpClient.executeMethod(mPost);
			final long endTime = Calendar.getInstance().getTimeInMillis();
			
			runTime = endTime - startTime;
			
			// 	If request was successful, then store it in the output JSON.
			if (statusCode == 200 || statusCode == 201) 
			{
				unformattedOutput = mPost.getResponseBodyAsString();
				
				try
				{	
					output = toJSONObject(unformattedOutput);
				}
				
				catch(Exception exception)	{}
			} 
			
			complexOutput.put(Constants.ResponseKeys.STATUS, statusCode);
			complexOutput.put(Constants.ResponseKeys.OUTPUT, output);
			complexOutput.put(Constants.ResponseKeys.RUN_TIME, runTime);
			complexOutput.put(Constants.ResponseKeys.UNFORMATTED_OUTPUT, unformattedOutput);
		}
		
		//	If the call cannot be made, add the exception reason to output JSON.
		catch(Exception exception)
		{	
			complexOutput.put(Constants.ResponseKeys.ERROR_MESSAGE, exception.getMessage());
			complexOutput.put(Constants.ResponseKeys.ERROR_REASON, exception.toString());
		}   	
	}
	
	
	/************************************************
	 * 	Boolean Operations							*
	 ************************************************/
	
	//	Check if output is empty.
	public boolean isOutputEmpty()
	{
		return output.isEmpty();
	}
	
	//	Check if unformatted output is empty.
	public boolean isUnformattedOutputEmpty()
	{
		if(unformattedOutput == null)
			return true;
		return false;
	}
	
	//	Check if complex output is empty.
	public boolean isComplexOutputEmpty()
	{
		return complexOutput.isEmpty();
	}
	
	//	Checks if the request was executed successfully.
	public boolean executionHasError()
	{
		if(complexOutput.containsKey("ERROR_MESSAGE"))	return true;
		return false;
	}
	
	//	Checks if the output is correct response or error response.
	public boolean outputHasError()
	{
		if(output.containsKey("ERROR_MESSAGE"))	return true;
		return false;
	}
	
	
	
	
	/************************************************
	 * 	Get Operations								*
	 ************************************************/
	
	//	Get output as JSONObject.
	public JSONObject getOutputAsJSONObject()
	{
		return output;
	}
	
	//	Get complex output as JSONObject.
	public JSONObject getComplexOutputAsJSONObject()
	{
		return complexOutput;
	}
	
	//	Get output as String.
	public String getOutputAsString()
	{
		return output.toJSONString();
	}
	
	//	Get complex output as String.
	public String getComplexOutputAsString()
	{
		return complexOutput.toJSONString();
	}
	
	//	Get status code as Integer.
	public int getStatusCodeAsInteger()
	{
		return statusCode;
	}
	
	//	Get status code as String.
	public String getStatusCodeAsString()
	{
		return Integer.toString(statusCode);
	}
	
	//	Get Runtime as a Long value.
	public long getRuntimeAsLong()
	{
		return runTime;
	}
	
	//	Get Runtime as a String value.
	public String getRuntimeAsString()
	{
		return Long.toString(runTime);
	}
	
	//	Get the raw output
	public String getUnformattedOutput()
	{
		return unformattedOutput;
	}
	
	/************************************************
	 * 	Set Operations								*
	 ************************************************/
	
	//	Set request.
	public void setRequest(String request)
	{
		this.request = request; 
	}
	
	//	Set endpoint URL
	public void setUrl(String url)
	{
		this.url = url; 
	}
}
