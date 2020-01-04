package com.vovo.wordgame.utils;

import org.apache.commons.lang3.StringUtils;

import com.vovo.wordgame.model.PartsOfSpeech;

public class PartsOfSpeechMapper {

	public static PartsOfSpeech parse(String part) {
		if (StringUtils.isEmpty(part)) {
			return null;
		}
		
		if (part.equalsIgnoreCase("v.")) {
			return PartsOfSpeech.VERB;
		} else if (part.equalsIgnoreCase("adj.")) {
			return PartsOfSpeech.ADJECTIVE;
		} else if (part.equalsIgnoreCase("n.")) {
			return PartsOfSpeech.NOUN;
		} else if (part.equalsIgnoreCase("adv.")) {
			return PartsOfSpeech.ADVERB;
		} else if (part.equalsIgnoreCase("conj.")) {
			return PartsOfSpeech.CONJUNCTION;
		} 
		
		return null;
	}
}
