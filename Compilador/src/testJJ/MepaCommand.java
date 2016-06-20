package testJJ;

public class MepaCommand {	
	private String command = "";
	private String p1 = "";
	private String p2 = "";
	private String label = "";
	private String labelParam = "";
	
	public MepaCommand(String id) {
		command = id;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getP1() {
		return p1;
	}

	public void setP1(String p1) {
		this.p1 = p1;
	}

	public String getP2() {
		return p2;
	}

	public void setP2(String p2) {
		this.p2 = p2;
	}

	public String getLabelParam() {
		return labelParam;
	}

	public void setLabelParam(String labelParam) {
		this.labelParam = labelParam;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	@Override
	public String toString() {
		String s = "";
		
		if (label.length()>0)
			s = s + label + " ";
		
		s = s + command;
		
		if (p1.length()>0)
			s = s + " " + p1;

		if (p2.length()>0)
			if (p1.length()>0)
				s = s + "," + p2;
			else
				s = s + " " + p2;

		if (labelParam.length()>0)
			s = s + " " + labelParam;
		
		return s;
	}
}
