package org.fife.ui.rsyntaxtextarea.modes;

import java.util.HashMap;
import java.util.Set;

import org.fife.ui.rsyntaxtextarea.TokenMap;
import org.fife.ui.rsyntaxtextarea.TokenTypes;

public class LEGAL3TokenMakerCustom extends LEGAL3TokenMaker{
	public static TokenMap extraTokens;

	public LEGAL3TokenMakerCustom() {
		extraTokens = getKeywords();
	}

	@Override
	public void addToken(char[] array, int start, int end, int tokenType, int startOffset, boolean hyperlink) {
		// This assumes all of your extra tokens would normally be scanned as IDENTIFIER.
		if (tokenType == TokenTypes.IDENTIFIER) {
			char[] array2 = new char[array.length];
			
			for (int i=0;i<array2.length; i++) {
				array2[i] = Character.toUpperCase(array[i]);
			}
			
			int newType = extraTokens.get(array2, start, end);
			if (newType>-1) {
				tokenType = newType;
			}
		}
		super.addToken(array, start, end, tokenType, startOffset, hyperlink);
	}

	public static void addKeyword(String keyword, int type) {
		extraTokens.put(keyword, type);
	}

	public void clear() {
		extraTokens = new TokenMap();
	}

	/**
	 * Handles loading of keywords file.
	 * <P>
	 * It is recommended that a # sign be used for comments
	 * inside keywords.txt.
	 */
	static public TokenMap getKeywords() {
		if (extraTokens == null) {
			try {
				extraTokens = new TokenMap(false);

				HashMap<String, Integer> keywords = new HashMap<String, Integer>();
				Set<String> keys = keywords.keySet();
				for (String key : keys) {
					extraTokens.put(key, keywords.get(key));
				}

			} catch (Exception e) {}
		}
		return extraTokens;
	}
}