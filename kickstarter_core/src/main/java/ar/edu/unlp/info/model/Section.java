package ar.edu.unlp.info.model;

public class Section extends PersistentEntity implements Cloneable {

	private String content;
	private String name;
	
	public Section() {}
	
	public Section(String name) {
		this.name = name;
	}
	
	public Section(String name, String content) {
		this.name = name;
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Object clone() {
		Section sectionClone = null;
		
	    try {
	    	sectionClone = (Section)super.clone();
	    } 
	    catch(CloneNotSupportedException e) {
	        e.printStackTrace();
	    }
	    
	    return sectionClone;
	}
	
}
