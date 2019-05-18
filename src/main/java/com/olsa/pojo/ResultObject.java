
package com.olsa.pojo;


import java.io.Serializable;
import java.util.HashMap;

/**
* This class is the interface between struts action classes and the
* request handlers.
* Action classes will be calling methods of the handler classes.
* All the handler class' methods will return this object.
*/
public class ResultObject implements Serializable
{
	// Private variables
	private Object object;
	private Object object1;
	private Object object2;
	private Object object3;
	private Object object4;
	private Object object5;
	private Object error;
	private Object bingoBean;	
	private boolean success;

	/*
	   This parameter is required so that the business layer
	   can instruct/override the flow of screens.
	   Action classes should check first if this variable is set.
	   If its set, then it should find the forward with this name,
	   else it should check for usual success flag to go to success
	   or failure forward.
	 */
	private String nextPage;

	/*
	 	This parameter stores any error codes if some error occured.
	 	In this case, success will be set to false.
	 	Action class should check for errors, if <code>isSuccess<code> returns false.
	 	The key for this HashMap will be the error code
	 	The value for the HashMap will be a String Array containg the place holders if any.
	 */
	private HashMap errors;

	/**
	 * Default constructor for the class.
	 */
	public ResultObject ()
	{
		this.object = null;
		// Default is true
		success = true;
		nextPage = null;
	}

	/**
	 * Constructor a ResultObject with the values passed
	 * @param object Object
	 * @param success boolean
	 */
	public ResultObject (Object object, boolean success)
	{
		this.object = object;
		this.success = success;
	}

	/**
	 * Constructor a ResultObject with the values passed
	 * @param object Object
	 * @param success boolean
	 * @param String nextPage
	 */
	public ResultObject (Object object, boolean success, String nextPage)
	{
		this.object = object;
		this.success = success;
		this.nextPage = nextPage;
	}

	/**
	 * Method to return the value of object
	 * @return object Object
	 */
	public Object getObject ()
	{
		return this.object;
	}

	/**
	 * Method to return the value of object1
	 * @return object1 Object
	 */
	public Object getObject1 ()
	{
		return this.object1;
	}

	/**
	 * Method to return the value of object2
	 * @return object2 Object
	 */
	public Object getObject2 ()
	{
		return this.object2;
	}

	/**
	 * Method to return the value of object3
	 * @return object3 Object
	 */
	public Object getObject3 ()
	{
		return this.object3;
	}

	/**
	 * Method to return the value of object4
	 * @return object4 Object
	 */
	public Object getObject4 ()
	{
		return this.object4;
	}

	/**
	 * Method to return the value of object5
	 * @return object5 Object
	 */
	public Object getObject5 ()
	{
		return this.object5;
	}

	/**
	 * Method to return the value of success whether the call was a success or
	 * a failure
	 * @return success boolean
	 */
	public boolean isSuccess ()
	{
		return this.success;
	}

	/**
	 * Method to return the value of nextPage
	 * @return String nextPage
	 */
	public String getNextPage ()
	{
		return this.nextPage;
	}

	/**
	 * Method to return the value of errors
	 * @return errors HashMap containg error codes
	 */
	public HashMap getErrors ()
	{
		return this.errors;
	}

	/**
	 * Method to set the value of object
	 * @param object Object
	 */
	{
		this.object = object;
	}

	/**
	 * Method to set the value of object1
	 * @param object1 Object
	 */
	public void setObject1 (Object object1)
	{
		this.object1 = object1;
	}

	/**
	 * Method to set the value of object2
	 * @param object2 Object
	 */
	public void setObject2 (Object object2)
	{
		this.object2 = object2;
	}

	/**
	 * Method to set the value of object3
	 * @param object3 Object
	 */
	public void setObject3 (Object object3)
	{
		this.object3 = object3;
	}

	/**
	 * Method to set the value of object4
	 * @param object4 Object
	 */
	public void setObject4 (Object object4)
	{
		this.object4 = object4;
	}

	/**
	 * Method to set the value of object5
	 * @param object5 Object
	 */
	public void setObject5 (Object object5)
	{
		this.object5 = object5;
	}

	/**
	 * Method to set the value of success
	 * @param success boolean
	 */
	public void setSuccess (boolean success)
	{
		this.success = success;
	}

	/**
	 * Method to set the value of nextPage
	 * @param nextPage The next page where the flow should go
	 */
	public void setNextPage (String nextPage)
	{
		this.nextPage = nextPage;
	}

	/**
	 * Method to set the value of error HashMap
	 * @param errors The HashMap containg error codes and their placeholders
	 */
	public void setErrors (HashMap errors)
	{
		this.errors = errors;
	}

	public Object getError() {
		return error;
	}

	public void setError(Object error) {
		this.error = error;
	}

	public Object getBingoBean() {
		return bingoBean;
	}

	public void setBingoBean(Object bingoBean) {
		this.bingoBean = bingoBean;
	}
}
