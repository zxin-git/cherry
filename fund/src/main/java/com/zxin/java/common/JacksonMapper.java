package com.zxin.java.common;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
//import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public enum JacksonMapper{
	
	JSON(new ObjectMapper()), 
//	XML(new XmlMapper()),
//	YMAL(new YamlMapper()),
	;
	
	private final ObjectMapper mapper;

	JacksonMapper(ObjectMapper mapper) {
		this.mapper = mapper;
		JavaTimeModule timeModule = new JavaTimeModule();
		timeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		timeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		timeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		timeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
				.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true) //大小写不敏感
				.setSerializationInclusion(JsonInclude.Include.NON_NULL)
				.registerModule(timeModule)
				.registerModule(new ParameterNamesModule())
				.registerModule(new Jdk8Module());
	}
	
	public <T> String toString(T t){
		try{
			return this.mapper.writeValueAsString(t);
		}catch (IOException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public <T> T toObject(String content, TypeReference<T> typeReference){
		try{
			return this.mapper.readValue(content, typeReference);
		}catch (IOException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public ObjectMapper getMapper() {
		return mapper;
	}
};