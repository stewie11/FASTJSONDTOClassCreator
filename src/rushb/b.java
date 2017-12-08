package rushb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class b {

	private static String patternStr = "[\\w<>]* [\\w_]*;";
	private static String classStr = "[\\w<>]*";
	private static String attStr = "[\\w_]*;";
	private static Pattern pattern = Pattern.compile(patternStr);
	private static Pattern classPattern = Pattern.compile(classStr);
	private static Pattern attPattern = Pattern.compile(attStr);
	
	private static String fromCamelToUnderLine(String str) {
		StringBuilder sb = new StringBuilder();
		char[] chars = new char[222];
		str.getChars(0, str.length(), chars, 0);
		for(int i=0 ;chars[i]!=0;++i) {
			if(Character.isUpperCase(chars[i])) {
				sb.append('_');
				sb.append(Character.toLowerCase(chars[i]));
			}
			else
				sb.append(chars[i]);
		}
		return sb.toString();
	}
	private static String fromUnderLineToCamel(String str) throws Exception {
		StringBuilder sb = new StringBuilder();
		char[] chars = new char[222];
		str.getChars(0, str.length(), chars, 0);
		boolean nextToUpperCase = false;
		for(int i=0 ;chars[i]!=0;++i) {
			if(Character.isUpperCase(chars[i])) {
				throw new Exception("表列名字不合法");
			} else if(chars[i] =='_') {
				nextToUpperCase = true;
				continue;
			} else {
				char temp = chars[i];
				if(Character.isLetter(temp)) {
					if(nextToUpperCase) {
						temp = Character.toUpperCase(temp);
					}
				}
				sb.append(temp);
				nextToUpperCase = false;
			}
		}
		return sb.toString();
		
	}
	private static String getFastJsonAnnotationFromAttributeString(String str,String type) {
		StringBuilder sb = new StringBuilder();
		sb.append("@JSONField(name=\"" + fromCamelToUnderLine(str)+"\"");
		switch(type) {
		case"qq":
			break;
		case "Date":
			sb.append(", format=\"yyyy-MM-dd HH:mm:ss\"");
		default:
			
		}
		sb.append(")");
		return sb.toString();
	}
	
	
	public static String getFirstUpperCaseString(String key) {
		return Character.toUpperCase(key.charAt(0)) + (key.length()<2 ? "":key.substring(1));
	}
	
	public static String fromJavaTypeToJDBCType(String javaType) {
		
		switch (javaType) {
		case "String":
			return "VARCHAR";
		case "Date":
			return "TIMESTAMP";
		}
		return "???";
	}
	
	public static void main(String []arg) throws Exception {
//		while(true) {
			Scanner sc = new Scanner(System.in);
			List<String> lines = new ArrayList<>();
			LinkedHashMap<String,String> handledLines = new LinkedHashMap<>();
			while(sc.hasNext()) {
				String str = sc.nextLine();
				if(str != null && !str.isEmpty()) {
					lines.add(str);
				}
			}
			boolean convertionFailure = false;
			boolean isColumnName  = false;
			for(String line : lines) {
				if(line.equals("ccc")) {
					isColumnName = true;
					continue;
				}
				Matcher matcher = pattern.matcher(line);
				
				if (matcher.find()) {
					Matcher classMatcher = classPattern.matcher(line);
					String clazzString=null;
					if(classMatcher.find()) {
						clazzString = classMatcher.group();
						if(clazzString == null || clazzString.isEmpty()) break;
						line = line.substring(line.indexOf(clazzString)+clazzString.length());
					}
					Matcher attMatcher = attPattern.matcher(line);
					String attString;
					if(attMatcher.find()) {
						attString = attMatcher.group();
						if(attString == null || attString.isEmpty()) break;
						attString = attString.substring(0, attString.length()-1);
						if(isColumnName) {
							attString = fromUnderLineToCamel(attString);
						}
						handledLines.put(attString,clazzString);
					}
				} else {
					convertionFailure = true;
					break;
				}
			}
			if(convertionFailure) {
				System.out.println("error");
				//continue;
			}
			System.out.println("");
			Iterator it = handledLines.keySet().iterator(); 
			if(it.hasNext()) {
				System.out.println("<resultMap type=\"\" id=\"ResultMap\">");
			}
			while(it.hasNext()){
				String key = (String)it.next();
				System.out.println("\t<result property=\""+ key +"\" column=\""+ fromCamelToUnderLine(key) + "\" jdbcType=\"" + fromJavaTypeToJDBCType(handledLines.get(key)) +"\"/>");
				if(!it.hasNext()) {
					System.out.println("</resultMap>");
				}
			}
			
			
			
			System.out.println("================================================");
			System.out.println("");
			System.out.println("");
			it = handledLines.keySet().iterator(); 
			while(it.hasNext()){  
				String key = (String)it.next();
				System.out.println(getFastJsonAnnotationFromAttributeString(key, handledLines.get(key)));
				System.out.println("private " + handledLines.get(key) + " "+ key+";");
				System.out.println("");
			}
			it = handledLines.keySet().iterator(); 
			while(it.hasNext()){  
				String key = (String)it.next();
				System.out.println("public void set"+ getFirstUpperCaseString(key) + "(" + handledLines.get(key) +" "+ key +") {");
				System.out.println("\tthis."+key+" = " + key+";");
				System.out.println("}");
				System.out.println("");
			}
			it = handledLines.keySet().iterator(); 
			while(it.hasNext()){  
				String key = (String)it.next();
				System.out.println("public "+ handledLines.get(key) +" get"+ getFirstUpperCaseString(key) + "() {");
				System.out.println("\treturn " + key + ";");
				System.out.println("}");
				System.out.println("");
			}
			
			
			
		}
//	}
}
