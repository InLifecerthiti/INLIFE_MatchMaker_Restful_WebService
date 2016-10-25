package ontologyClasses;

import java.util.ArrayList;

public class ToolCategory {
	
	private String toolCategory;
	private ArrayList<Tool> tools;
	public ToolCategory(String toolCategory, ArrayList<Tool> tools) {
		super();
		this.toolCategory = toolCategory;
		this.tools = tools;
	}
	public String getToolCategory() {
		return toolCategory;
	}
	public void setToolCategory(String toolCategory) {
		this.toolCategory = toolCategory;
	}
	public ArrayList<Tool> getTools() {
		return tools;
	}
	public void setTools(ArrayList<Tool> tools) {
		this.tools = tools;
	}
	@Override
	public String toString() {
		return "ToolCategory [toolCategory=" + toolCategory + ", tools="
				+ tools + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((toolCategory == null) ? 0 : toolCategory.hashCode());
		result = prime * result + ((tools == null) ? 0 : tools.hashCode());
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
		ToolCategory other = (ToolCategory) obj;
		if (toolCategory == null) {
			if (other.toolCategory != null)
				return false;
		} else if (!toolCategory.equals(other.toolCategory))
			return false;
		if (tools == null) {
			if (other.tools != null)
				return false;
		} else if (!tools.equals(other.tools))
			return false;
		return true;
	}
	

}
