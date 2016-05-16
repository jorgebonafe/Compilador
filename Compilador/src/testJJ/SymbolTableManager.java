package testJJ;

import java.util.ArrayList;
import java.util.HashMap;

public class SymbolTableManager {
	public static final int CATEGORY_TYPE = 0;
	public static final int CATEGORY_PROCEDURE = 1;
	public static final int CATEGORY_FUNCTION = 2;
	public static final int CATEGORY_VAR = 3;
	public static final int CATEGORY_PARAM = 4;
	public static final int CATEGORY_CONST = 5;
	
	public static final int TRANSFER_REF = 0;
	public static final int TRANSFER_VALUE = 1;
	
	
	private HashMap<String,ArrayList<Identifier>> symbolList;
	private ArrayList<ArrayList<Identifier>> symbolListByLevel;
	
	public SymbolTableManager() {
		symbolList = new HashMap<String,ArrayList<Identifier>>();
		symbolListByLevel = new ArrayList<ArrayList<Identifier>>();
		
		Identifier id = new Identifier();
		id.setID("STRING");
		id.setLevel(0);
		id.setCategory(CATEGORY_TYPE);
		addSymbol(id);
		
		id = new Identifier();
		id.setID("BOOLEAN");
		id.setLevel(0);
		id.setCategory(CATEGORY_TYPE);
		
		id = new Identifier();
		id.setID("INTEGER");
		id.setLevel(0);
		id.setCategory(CATEGORY_TYPE);
		
		id = new Identifier();
		id.setID("REAL");
		id.setLevel(0);
		id.setCategory(CATEGORY_TYPE);
		
		id = new Identifier();
		id.setID("CALL");
		id.setLevel(0);
		id.setCategory(CATEGORY_PROCEDURE);
		
		id = new Identifier();
		id.setID("READ");
		id.setLevel(0);
		id.setCategory(CATEGORY_PROCEDURE);
		
		id = new Identifier();
		id.setID("WRITE");
		id.setLevel(0);
		id.setCategory(CATEGORY_PROCEDURE);
		
		id = new Identifier();
		id.setID("SET");
		id.setLevel(0);
		id.setCategory(CATEGORY_PROCEDURE);
	}
	
	public boolean addSymbol(Identifier id) {
		ArrayList<Identifier> idList = null;
		int level = id.getLevel();
		
		if (symbolList.containsKey(id.getID())) {
			idList = symbolList.get(id.getID());
		}
		else {
			idList = new ArrayList<Identifier>();
			symbolList.put(id.getID(),idList);
		}

		int lastIndex = idList.size()-1;
		while (lastIndex<level) {
			idList.add(null);
			lastIndex++;
		}
		if (idList.get(lastIndex)!=null)
			return false;
		
		idList.set(lastIndex,id);
		
		lastIndex = symbolListByLevel.size()-1;
		while (lastIndex < level) {
			symbolListByLevel.add(new ArrayList<Identifier>());
			lastIndex++;
		}
		symbolListByLevel.get(level).add(id);
		
		return true;
	}
	
	public Identifier getIdentifier(String name) {
		if (!symbolList.containsKey(name.toUpperCase()))
			return null;
		
		ArrayList<Identifier> idList = symbolList.get(name.toUpperCase());
		for (int i=idList.size()-1;i>=0;i--) {
			if (idList.get(i) != null)
				return idList.get(i);
		}
		return null;
	}
	
	public void removeLevel(int level) {
		ArrayList<Identifier> idList = symbolListByLevel.get(level);
		for (Identifier identifier : idList) {
			removeSymbol(identifier);
		}
		symbolListByLevel.set(level,new ArrayList<Identifier>());
	}
	
	public void removeSymbol(Identifier id) {
		try {
			if (symbolList.get(id.getID()).get(id.getLevel())!=id) {
				System.out.println("Removendo símbolo não existente!");
				System.exit(1);
			}
			symbolList.get(id.getID()).set(id.getLevel(),null);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Removendo símbolo não existente!");
			System.exit(1);
		}
	}
	
}
