# HTTP Library

## This library provides an easy interface to execute HTTP requests. 

### Usage:
	
```
    1. Initialize the default parameterized constructor with the sample request and endpoint where request should be sent.
    2. Execute the post request to the desired URL.
    3. You can check the status code, whether any errors exist or get the outputs.
```

### Example:

```java
		ExecuteHttpPost postman = new ExecuteHttpPost("", "https://postman-echo.com/post");
		postman.executeRequest();
        postman.getStatusCodeAsInteger()
```

### Functions included:

* ExecuteHttpPost (String request, String url)

```
	Default constructor that sets the.
	EXAMPLE:
    	ExecuteHttpPost postman = new ExecuteHttpPost("", "https://postman-echo.com/post");
```

* executeRequest()

```
	Executes the POST request to the desired URL.
```

* Boolean Operations
```
	isOutputEmpty() - Check if output is empty.
    isUnformattedOutputEmpty() - Check if unformatted output is empty.
	isComplexOutputEmpty() - Check if complex output is empty.
    executionHasError() - Checks if the request was executed successfully.
    outputHasError() - Checks if the output is correct response or error response.
```

* Set Operations
```
	setRequest(String request) - Set input to endpoint.
    setUrl(String url) - Set endpoint URl.
```

* Get Operations
```
	getOutputAsJSONObject() - Get output as JSONObject.
    getOutputAsString() - Get output as String.
    
    getComplexOutputAsJSONObject() - Get complex output as JSONObject.
    getComplexOutputAsString() - Get complex output as String.
    
    getStatusCodeAsInteger() - Get Status Code As Integer.
	getStatusCodeAsString() - Get status code as String.
   
	getRuntimeAsLong() - Get Runtime as a Long value.
    getRuntimeAsString() - Get Runtime as a String value.
    
    getUnformattedOutput() - Get the raw output.
```