package com.coo8.btoc.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public final class PatternUtil {
	public static final String BLOCK_TAG_NAME = "coo8publishblock";
	private  static Logger logger = LoggerFactory.getLogger(PatternUtil.class);
	public static final String ATTRIBUTE_NAME_MATCH_STRING = "[-_.A-Za-z0-9]+";
	public static final String ATTRIBUTE_TYPE_MATCH_STRING = "[0-9]+";
	
	//∆•≈‰<coo8publishblock name='blockname495' type='0'/>
	public static final Pattern BLOCK_PATTERN = makeBlockTagPattern
		(ATTRIBUTE_NAME_MATCH_STRING, ATTRIBUTE_TYPE_MATCH_STRING);
	
	//∆•≈‰name='blockname495'
	public static final Pattern BLOCK_NAME_PATTERN = Pattern.compile(
		"name\\s*=\\s*(\"|'|)\\s*[-_.A-Za-z0-9]+\\s*(\"|'|)");
	
	//∆•≈‰type='0'
	public static final Pattern BLOCK_TYPE_PATTERN = Pattern.compile(
		"type\\s*=\\s*(\"|'|)\\s*[0-9]+\\s*(\"|'|)");

	private PatternUtil(){}
	
	public static Matcher blockPattern(String content) {
		return BLOCK_PATTERN.matcher(content);
	}
	
	public static Matcher blockNamePattern(String blockTag) {
		return BLOCK_NAME_PATTERN.matcher(blockTag);
	}
	
	public static Matcher blockTypePattern(String blockTag) {
		return BLOCK_TYPE_PATTERN.matcher(blockTag);
	}
	
	public static String makeBlockTag(String blockName, int blockType) {
		return "<" + BLOCK_TAG_NAME + " name=\"" + blockName + "\" type=\"" + blockType + "\"/>";
	}
	
	public static Pattern makeBlockTagPattern(
			String attributeNameMatchString, String attributeTypeMatchString) {
		
		return Pattern.compile(
				"(<\\s*"+ BLOCK_TAG_NAME + "\\s+name\\s*=\\s*(\"|'|)\\s*("
				+ attributeNameMatchString + ")\\s*(\"|'|)\\s" +
				"type\\s*=\\s*(\"|'|)\\s*(" + attributeTypeMatchString 
				+ ")\\s*(\"|'|)+\\s*/>)");
	}
	
	public static String replaceBlockTagName(String oldName, String newName, int blockType, String content) {
		Pattern pattern = makeBlockTagPattern(oldName, (blockType + ""));
		Matcher matcher = pattern.matcher(content);
		return matcher.replaceAll(makeBlockTag(newName, blockType));
	}
	
	public static void main(String arg[]) {
		String b = "<html><body> <coo8publishblock name='blo_ckname495' type='0'/>nihao <coo8publishblock name='blockname660' type=' 1'/>ah</body></html>";
		String c = replaceBlockTagName("blo_ckname495","test" , 0, b);
		logger.info(c);
	}
}
