package com.vovo.wordgame.utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.vovo.wordgame.model.PopulatedWord;
import com.vovo.wordgame.model.Word;

public class WordPopulator {
	
	// static variable wordPopulatorInstance of type Singleton 
    private static WordPopulator wordPopulatorInstance = null; 
    
    private static final String WORD_CSV_FILE = "ArrangedGREMostCommonWords.csv";

    private static Map<String, PopulatedWord> populatedWordMap = new TreeMap<>();
  
    // private constructor restricted to this class itself 
    private WordPopulator() {
    	CSVFileParser csvFileParser = new CSVFileParser();
		try {
			Map<String, Word> wordsMap = csvFileParser.read(WORD_CSV_FILE);
			for(String wordValue: wordsMap.keySet()) {
				Word word = wordsMap.get(wordValue);
				Set<String> synonyms = word.getSynonyms();
				Set<Word> synonymWords = new TreeSet<>();
				for (String synonym : synonyms) {
					if (wordsMap.containsKey(synonym)) {
						synonymWords.add(wordsMap.get(synonym));
					} else {
						synonymWords.add(new Word(synonym));
					}
				}
				
				Set<String> antonyms = word.getAntonyms();
				Set<Word> antonymWords = new TreeSet<>();
				for (String antonym : antonyms) {
					if (wordsMap.containsKey(antonym)) {
						antonymWords.add(wordsMap.get(antonym));
					} else {
						antonymWords.add(new Word(antonym));
					}
				}
				
				PopulatedWord populatedWord = new PopulatedWord(wordValue,
						word.getPartsOfSpeech(),
						word.getSequenceNumber(),
						synonymWords,
						antonymWords,
						word.getUsage(),
						word.getRelatedPartsOfSpeech(),
						word.getPriority());
				
				populatedWordMap.put(wordValue, populatedWord);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
  
    // static method to create instance of Singleton class 
    synchronized public static WordPopulator getInstance() { 
        if (wordPopulatorInstance == null) 
        	wordPopulatorInstance = new WordPopulator(); 
  
        return wordPopulatorInstance; 
    } 
    
    public List<PopulatedWord> getAllPopulatedWords() {
    	return populatedWordMap.entrySet().stream()
                .map(x -> x.getValue())
                .collect(Collectors.toList());
    }
    
    public PopulatedWord getPopulatedWord(String wordValue) {
    	return populatedWordMap.get(wordValue);
    }

}
