package midas;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * 클래스 다이어그램의 클래스 정보를 저장하는데 사용하는 클래스
 * 
 * @author dnjsd
 *
 */
public class ClassObject implements Serializable{
	private static final long serialVersionUID = 1L;
	private String className;
	private ArrayList<String> attributes;
	private ArrayList<String> operations;
	private Location classLocation;
	private RelationshipArrow myRelationshipArrow;
	private ClassObject myRelationClassObject;
	private int width = 0, height = 0;
	private String classType;
	/**
	 * 맨 처음 빈 클래스 생성시 이름과 Location class만 받아서 생성한다.
	 * 
	 * @param className
	 * @param classLocation
	 */

	public ClassObject() {
		
	}
	
	
	public ClassObject(Location classLocation) {

		this("Object", new ArrayList<String>(), new ArrayList<String>(), classLocation, null, null, "Class");
	}

	public ClassObject(String className, Location classLocation, String classType) {
		this(className, new ArrayList<String>(), new ArrayList<String>(), classLocation, null, null, classType);
		width = className.length();
		attributes.add(" ");
		operations.add(" ");
		calWidthAndHeight();
	}

	public ClassObject(String className, ArrayList<String> attributes, ArrayList<String> operations,
			Location classLocation, RelationshipArrow myRelationshipArrow, ClassObject myRelationClassObject, String classType) {
		setClassName(className);
		setAttributes(attributes);
		setOperations(operations);
		setClassLocation(classLocation);
		setMyRelationshipArrow(myRelationshipArrow);
		setMyRelationClassObject(myRelationClassObject);
		setClassType(classType);
		calWidthAndHeight();
	}
	
	private void setClassType(String classType) {
		this.classType = classType;
	}
	
	public String getClassType() {
		return classType;
	}

	private void calWidthAndHeight() {
		width = className.length();
		if(attributes != null) {
			for(int i = 0; i < attributes.size(); i ++) {
				if(width < attributes.get(i).length()) {
					width = attributes.get(i).length();
				}
			}
		}
		if(operations != null) {
			for(int i = 0; i < operations.size(); i++) {
				if(width < operations.get(i).length()) {
					width = operations.get(i).length();
				}
			}
		}
		if(attributes != null && operations != null){
			height = attributes.size() + operations.size() + 1;
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
		calWidthAndHeight();
	}

	public ArrayList<String> getAttributes() {
		return attributes;
	}

	public void setAttributes(ArrayList<String> attributes) {
		this.attributes = attributes;
		calWidthAndHeight();
	}

	public ArrayList<String> getOperations() {
		return operations;
	}

	public void setOperations(ArrayList<String> operations) {
		this.operations = operations;
		calWidthAndHeight();
	}

	public RelationshipArrow getMyRelationshipArrow() {
		return myRelationshipArrow;
	}

	public void setMyRelationshipArrow(RelationshipArrow myRelationshipArrow) {
		this.myRelationshipArrow = myRelationshipArrow;
	}

	public ClassObject getMyRelationClassObject() {
		return myRelationClassObject;
	}

	public void setMyRelationClassObject(ClassObject myRelationClassObject) {
		this.myRelationClassObject = myRelationClassObject;
	}

	public Location getClassLocation() {
		return classLocation;
	}

	public void setClassLocation(Location classLocation) {
		this.classLocation = classLocation;
	}

	@Override
	public String toString() {
		return "ClassObject [className=" + className + ", classLocation=" + classLocation + ", myRelationshipArrow="
				+ myRelationshipArrow + ", myRelationClassObject=" + myRelationClassObject + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classLocation == null) ? 0 : classLocation.hashCode());
		result = prime * result + ((className == null) ? 0 : className.hashCode());
		result = prime * result + ((myRelationClassObject == null) ? 0 : myRelationClassObject.hashCode());
		result = prime * result + ((myRelationshipArrow == null) ? 0 : myRelationshipArrow.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassObject other = (ClassObject) obj;
		if (classLocation == null) {
			if (other.classLocation != null)
				return false;
		} else if (!classLocation.equals(other.classLocation))
			return false;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (myRelationClassObject == null) {
			if (other.myRelationClassObject != null)
				return false;
		} else if (!myRelationClassObject.equals(other.myRelationClassObject))
			return false;
		if (myRelationshipArrow == null) {
			if (other.myRelationshipArrow != null)
				return false;
		} else if (!myRelationshipArrow.equals(other.myRelationshipArrow))
			return false;
		return true;
	}

}
