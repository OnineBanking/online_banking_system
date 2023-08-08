package com.dxc.obs.api.exceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dxc.obs.api.exception.BadRequestException;
import com.dxc.obs.api.payload.response.Response;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	 private final Logger logger= 
		    	LoggerFactory.getLogger(GlobalExceptionHandler.class);

		    @ExceptionHandler({BadRequestException.class})
		    public ResponseEntity<Response> 
		    		handleBadRequestException(BadRequestException e)
		    {
		      logger.info("Bad Request Found {} ",e.getMsg(),e);
		      return new ResponseEntity<>(
		      	Response.builder().
		        	responseMsg(e.getMsg()).build(),
					HttpStatus.BAD_REQUEST);
		    }

		    @ExceptionHandler({Exception.class})
		    public ResponseEntity<Response> 
		    			handleException(Exception e)
		    {
		      logger.info("Unknown error occur {} ",
		      	e.getMessage(),e);
		      
		      return new ResponseEntity<>(
		      		Response.builder()
		            	.responseMsg(e.getMessage()).build(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		    }

}
