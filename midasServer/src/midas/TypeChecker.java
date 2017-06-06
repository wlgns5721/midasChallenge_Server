package midas;
/**
 * 속성과 변수의 입력이 정확한지 검사하기 위한 검사기 클래스
 * @author dnjsd
 *
 */
public class TypeChecker {
	private static String errorCode = "";
	
	public static boolean arrtibuteTypeCheck(String attr) {
		char ch[] = attr.toCharArray();
		if(ch[0] == '+' || ch[0] == '-' || ch[0] == '#') {
			if(attr.contains(":")) {
				String splitString[] = attr.split(":");
				if(splitString[1].matches(".*int") || splitString[1].matches(".*long") || splitString[1].matches(".*float")
						|| splitString[1].matches(".*double") || splitString[1].matches(".*byte") || splitString[1].matches(".*short")
						|| splitString[1].matches(".*boolean") || splitString[1].matches(".*ch")) {
					return true;
				}else {
					errorCode = "Primitive type Mismatch Error";
				}
			}else {
				errorCode = "No Primitive type Error";
			}
		}else {
			errorCode = "Access Modifier Error";
		}
		return false;
	}
	
	public static boolean operationTypeCheck(String op) {
		char ch[] = op.toCharArray();
		if(ch[0] == '+' || ch[0] == '-'|| ch[0] == '#') {
			if(op.contains(":")) {
				String splitString[] = op.split(":");
				if(splitString[1].matches(".*int") || splitString[1].matches(".*long") || splitString[1].matches(".*float")
						|| splitString[1].matches(".*double") || splitString[1].matches(".*byte") || splitString[1].matches(".*short")
						|| splitString[1].matches(".*boolean") || splitString[1].matches(".*ch")) {
					if(splitString[0].matches(".*(.*).*")) {
						return true;
					}else {
						errorCode = "Has No ()";
					}
				}else {
					errorCode = "Return type Mismatch Error";
				}
			}else {
				errorCode = "No Return type Error";
			}
		}else {
			errorCode = "Access Modifier Error";
		}
		return false;
	}
	
	public static String getErrorCode() {
		return errorCode;
	}
}
