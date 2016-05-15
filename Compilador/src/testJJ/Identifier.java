package testJJ;

public class Identifier {
	private String ID;
	private int level;
	private int category;
	private int type;
	private int address;
	private int nBytes;
	private int value;
	private int transferType;
	private int nParameters;
	private int lower;
	private int upper;
	
	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}
	
	public String getCategoryName() {
		switch (category) {
		case SymbolTableManager.CATEGORY_TYPE:
			return "Tipo";
		case SymbolTableManager.CATEGORY_PROCEDURE:
			return "Procedimento";
		case SymbolTableManager.CATEGORY_FUNCTION:
			return "Função";
		case SymbolTableManager.CATEGORY_VAR:
			return "Variável";
		case SymbolTableManager.CATEGORY_PARAM:
			return "Parâmetro";
		}
		return "";
	}
	
	public int getType() {
		return type;
	}
	
	public String getTypeName() {
		switch (type) {
		case JASONConstants.STRING:
			return "String";
		case JASONConstants.INTEGER:
			return "Integer";
		case JASONConstants.BOOLEAN:
			return "Boolean";
		case JASONConstants.REAL:
			return "Real";
		}
		return "";
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getAddress() {
		return address;
	}

	public void setAddress(int address) {
		this.address = address;
	}

	public int getNBytes() {
		return nBytes;
	}

	public void setNBytes(int nBytes) {
		this.nBytes = nBytes;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getTransferType() {
		return transferType;
	}
	
	public String getTransferTypeName() {
		switch (transferType) {
		case SymbolTableManager.TRANSFER_REF:
			return "Referência";
		case SymbolTableManager.TRANSFER_VALUE:
			return "Valor";
		}
		return "";
	}

	public void setTransferType(int tranferType) {
		this.transferType = tranferType;
	}

	public int getNParameters() {
		return nParameters;
	}

	public void setNParameters(int nParameters) {
		this.nParameters = nParameters;
	}

	public int getLower() {
		return lower;
	}

	public void setLower(int lower) {
		this.lower = lower;
	}

	public int getUpper() {
		return upper;
	}

	public void setUpper(int upper) {
		this.upper = upper;
	}
}
