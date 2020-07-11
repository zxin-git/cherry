package com.zxin.java.http;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * 响应结果封装类
 * @param <T>
 */
@Getter
@ToString
@AllArgsConstructor
public class Result<T> {

	private String dateTime = LocalDateTime.now().toString();

	private String uri;

	private HttpStatus httpStatus;

	private T body;

	public Result(String uri, HttpStatus httpStatus, T body){
		this.uri = uri;
		this.httpStatus = httpStatus;
		this.body = body;
	}

	public static <T> Result<T> success(String uri, T body){
		return new Result<T>(uri, HttpStatus.OK, body);
	}

	public static Result<String> error(String uri, HttpStatus httpStatus, String message){
		return new Result<String>(uri, httpStatus, message);
	}


}