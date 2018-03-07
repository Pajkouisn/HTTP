package pajkouisn.unit;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pajkouisn.utilities.http.ExecuteHttpPost;


/**
 * 
 * 	JUnit tests for ExecuteHttpPost.
 * 	
 * 	@author kartiklaw@gmail.com (www.kartik-reddy.com)
 *
 */
public class TestExecuteHttpPost
{	
	/**
	 * 	Execute Http request.
	 */
	@Test
	public void testExecutePost()
	{
		boolean check = true;

		ExecuteHttpPost postman = new ExecuteHttpPost("", "https://postman-echo.com/post");
		postman.executeRequest();
		
		check &= postman.getStatusCodeAsInteger() == 200;
		check &= !postman.executionHasError();
		
		assertTrue(check);
	}
}
