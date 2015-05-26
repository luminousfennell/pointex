package proglang.students.his;

import java.util.OptionalInt;


/**
 * Students as considered by HIS. They have a real name
 * (e.g., Luminous Fennell), a "degree program", and a group.
 * 
 * @author fennell
 *
 */
public class HisStudent {

	private final String realname;
	private final DegreeProgram program;
	private final OptionalInt group;
	
	
	public HisStudent(String realname, DegreeProgram program, OptionalInt group) {
		super();
		this.realname = realname;
		this.program = program;
		this.group = group;
	}


	public String getRealname() {
		return realname;
	}


	public DegreeProgram getProgram() {
		return program;
	}
	
	public OptionalInt getGroup() {
		return group;
	}


	@Override
	public String toString() {
		return "HisStudent [realname=" + realname + ", program=" + program
				+ ", group=" + group + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((program == null) ? 0 : program.hashCode());
		result = prime * result
				+ ((realname == null) ? 0 : realname.hashCode());
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
		HisStudent other = (HisStudent) obj;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (program == null) {
			if (other.program != null)
				return false;
		} else if (!program.equals(other.program))
			return false;
		if (realname == null) {
			if (other.realname != null)
				return false;
		} else if (!realname.equals(other.realname))
			return false;
		return true;
	}

}

