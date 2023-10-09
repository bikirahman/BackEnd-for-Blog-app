package com.blog.api.exception;

public class ResourceNotFoundException extends RuntimeException {

	String resourceNameString;
	String fieldNameString;
	long fieldValue;

	public ResourceNotFoundException(String resourceNameString, String fieldNameString, long fieldValue) {
		super(String.format("%s not found with %s : %s", resourceNameString, fieldNameString, fieldValue));
		this.resourceNameString = resourceNameString;
		this.fieldNameString = fieldNameString;
		this.fieldValue = fieldValue;
	}

	public String getResourceNameString() {
		return resourceNameString;
	}

	public void setResourceNameString(String resourceNameString) {
		this.resourceNameString = resourceNameString;
	}

	public String getFieldNameString() {
		return fieldNameString;
	}

	public void setFieldNameString(String fieldNameString) {
		this.fieldNameString = fieldNameString;
	}

	public long getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(long fieldValue) {
		this.fieldValue = fieldValue;
	}
	
	

}
