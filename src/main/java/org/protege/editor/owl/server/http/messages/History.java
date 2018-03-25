package org.protege.editor.owl.server.http.messages;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class History implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4521102352676041770L;
	
	private String start_date = null;
	private String end_date = null;
	
	public String getStartDate() { return start_date; }
	public String getEndDate() { return end_date; }
	
	
	private String date;
	public String getDate() { return date; }
	private String user_name = null;
	public String getUser_name() { return user_name; }
	private String code = null;
	public String getCode() { return code; }
	private String name;
	public String getName() { return name; }
	private String operation = null;
	public String getOperation() { return operation; }
	public String getOp() { return operation; }
	private String reference;
	public String getReference() { return reference; }
	
	public enum HistoryType {EVS, CONCEPT};
	
	public void setQueryArgs(String start, String end, String user, String code, String op) {
		start_date = start;
		end_date = end;
		user_name = user;
		this.code = code;
		operation = op;		
	}
	
	public static History createConHist(String[] tokens) {
		// ignore date when using EVS history for concept history
		String un = tokens[1];
		String code = tokens[2];
		String name = tokens[3];
		String op = tokens[4];
		String ref = null;
		if (tokens.length == 6) {		
			ref = tokens[5];
		}
		return new History(un, code, name, op, ref);
		
	}
	
	public static History createEvsHist(String[] tokens) {
		String date = tokens[0];
		String un = tokens[1];
		String code = tokens[2];
		String name = tokens[3];
		String op = tokens[4];
		String ref = null;
		if (tokens.length == 6) {		
			ref = tokens[5];
		}
		return new History(date, un, code, name, op, ref);
		
	}
	
	public History(String un, String c, String n, String op, String ref) {
		this.date = formatNow();
		user_name = un;
		code = c;
		name = n;
		operation = op;
		reference = ref;
		
	}
	
	public History(String date, String un, String c, String n, String op, String ref) {
		this.date = date;
		user_name = un;
		code = c;
		name = n;
		operation = op;
		reference = ref;
		
	}
	
	
	
	public History() {
		// TODO Auto-generated constructor stub
	}
	private String formatNow() {
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return date.format(formatter);
	}
	
	
	
	public String toRecord(HistoryType type) {
		if (type == HistoryType.EVS) {
			return date + "\t" +
					user_name + "\t" +
					code + "\t" +
					name + "\t" +
					operation + "\t" +
					reference;
		} else {
			return code + "\t" +
					operation + "\t" +
					date + "\t" +
					reference;
		}

	}

}
