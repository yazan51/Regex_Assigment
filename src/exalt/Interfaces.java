package exalt;
import java.util.regex.*;

public class Interfaces {
	private StringBuilder inputText;		//Attributes
	private String iName;
	private String ipAddr;
	private String iDescription;
	private String ifSpeed;
	private String duplexMode;
	private String adminStatus;
	private String opStatus;
	private String macAddr;
	private String mtu;
	
	public Interfaces() {				//Constructors
		super();
	}

	public Interfaces(String iName, String ipAddr, String iDescription, String ifSpeed, String duplexMode,
			String adminStatus, String opStatus, String macAddr, String mtu) {
		super();
		this.iName = iName;
		this.ipAddr = ipAddr;
		this.iDescription = iDescription;
		this.ifSpeed = ifSpeed;
		this.duplexMode = duplexMode;
		this.adminStatus = adminStatus;
		this.opStatus = opStatus;
		this.macAddr = macAddr;
		this.mtu = mtu;
	}

	public String getiName() {				//Setters and getters
		return iName;
	}

	public void setiName(String iName) {
		this.iName = iName;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public String getiDescription() {
		return iDescription;
	}

	public void setiDescription(String iDescription) {
		this.iDescription = iDescription;
	}

	public String getIfSpeed() {
		return ifSpeed;
	}

	public void setIfSpeed(String ifSpeed) {
		this.ifSpeed = ifSpeed;
	}

	public String getDuplexMode() {
		return duplexMode;
	}

	public void setDuplexMode(String duplexMode) {
		this.duplexMode = duplexMode;
	}

	public String getAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(String adminStatus) {
		this.adminStatus = adminStatus;
	}

	public String getOpStatus() {
		return opStatus;
	}

	public void setOpStatus(String opStatus) {
		this.opStatus = opStatus;
	}

	public String getMacAddr() {
		return macAddr;
	}

	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}

	public String getMtu() {
		return mtu;
	}

	public void setMtu(String mtu) {
		this.mtu = mtu;
	}
	public StringBuilder getInputText() {
		return inputText;
	}
	public void setInputText(StringBuilder inputText) {
		this.inputText = inputText;
	}
	public void appendInputText(String input) {
		this.inputText.append(input);
	}
	

	public String toString(int id) {
		return "Interface #" + (id+1) + ":\n\n\tinterface name: " + iName + "\n\tip address: " + ipAddr + "\n\tinterface description: " + iDescription + "\n\tifSpeed: "
				+ ifSpeed + "\n\tduplex mode: " + duplexMode + "\n\tadmin status: " + adminStatus + "\n\toperation status: " + opStatus
				+ "\n\tmac address: " + macAddr + "\n\tmtu: " + mtu;
	}
	
	public void divideInputString() {
		String text = inputText.toString();
		String pattern = "^.*?(?= is)";			//Interface name extraction
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(text);
		m.find();
		try {
			iName = m.group();
		}
		catch (IllegalStateException e) {
			iName = " ";
		}
		
		pattern = "(?<=Internet address is )(\\d*.\\d*.\\d*.\\d*)";  //IP address
		p = Pattern.compile(pattern);
		m = p.matcher(text);
		m.find();
		try {
			ipAddr = m.group();
		}
		catch (IllegalStateException e) {
			ipAddr = " ";
		}
		
		pattern = "(?<=  Description: ).*(?=####)";		//Description
		p = Pattern.compile(pattern);
		m = p.matcher(text);
		m.find();
		try {
			iDescription = m.group();
		}
		catch (IllegalStateException e) {
			iDescription = " ";
		}
		
		pattern = "(?<=  )([a-zA-Z]*\\-duplex)";		//Duplex mode
		p = Pattern.compile(pattern);
		m = p.matcher(text);
		m.find();
		try {
			duplexMode = m.group(1);
		}
		catch (IllegalStateException e) {
			duplexMode = " ";
		}
		
		pattern = "(?<=  )([a-zA-Z]*\\-duplex)(, )(.*?),";		//if Speed
		p = Pattern.compile(pattern);
		m = p.matcher(text);
		m.find();
		try {
			ifSpeed = m.group(3);
		}
		catch (IllegalStateException e) {
			ifSpeed = " ";
		}
		
		pattern = "(?<=is )([a-z\\s]*),";		//Admin status
		p = Pattern.compile(pattern);
		m = p.matcher(text);
		m.find();
		try {
			adminStatus = m.group(1);
		}
		catch (IllegalStateException e) {
			adminStatus = " ";
		}
		
		pattern = "(?<=is )[a-z]*";		//Operation status
		p = Pattern.compile(pattern);
		m = p.matcher(text);
		m.find();
		m.find();
		try {
			opStatus = m.group();
		}
		catch (IllegalStateException e) {
			opStatus = " ";
		}
		
		pattern = "(?<=address is )[0-9a-f]{4}.[0-9a-f]{4}.[0-9a-f]{4}";		//MAC address
		p = Pattern.compile(pattern);
		m = p.matcher(text);
		m.find();
		try {
			StringBuilder temp = new StringBuilder("");
			temp.append(m.group().toCharArray()[0] + m.group().toCharArray()[1]);
			for (int i =2;i<14;i+=2) {
				if (m.group().toCharArray()[i] == '.')
					i++;
				temp.append(":" + m.group().toCharArray()[i]);
				if (m.group().toCharArray()[i+1] == '.')
					i++;
				temp.append( m.group().toCharArray()[i+1]);
			}
			macAddr = temp.toString();
		}
		catch (IllegalStateException e) {
			macAddr = " ";
		}
		
		pattern = "(?<=MTU )\\d*";		//MTU
		p = Pattern.compile(pattern);
		m = p.matcher(text);
		m.find();
		try {
			mtu = m.group();
		}
		catch (IllegalStateException e) {
			mtu = " ";
		}
		
	}
	
}
