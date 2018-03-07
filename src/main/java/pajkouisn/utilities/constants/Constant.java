package pajkouisn.utilities.constants;

public class Constant
{
	/**
	 * The caller references the constants using <tt>Consts.EMPTY_STRING</tt>, 
	 * and so on. Thus, the caller should be prevented from constructing objects of 
	 * this class, by declaring this private constructor. 
	 */
	private Constant()
	{
		//	this prevents even the native class from 
		//	calling this ctor as well :
		throw new AssertionError();
	}
	
	/*
	 * 	Response keys
	 */
	public static class ResponseKeys
	{
		public static final String STATUS = "STATUS";		
		public static final String OUTPUT = "OUTPUT";		
		public static final String RUN_TIME = "RUN_TIME";		
		public static final String UNFORMATTED_OUTPUT = "UNFORMATTED_OUTPUT";	
		
		public static final String ERROR_MESSAGE = "ERROR_MESSAGE";		
		public static final String ERROR_REASON = "ERROR_REASON";	
	}
	
}
