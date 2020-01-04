package com.vovo.wordgame.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.vovo.wordgame.model.PartsOfSpeech;
import com.vovo.wordgame.model.Word;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CSVFileParser {
	private static final String SEMICOLON_SEPARATOR = ";";
	private static final String DOT_SEPARATOR = ".";
	
	public Map<String, Word> read(String fileName) throws IOException {
		Map<String, Word> wordsMap = new TreeMap<>();  
        try (
        	Reader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/" + fileName)));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim());
        ) {
        	int sequenceNumber = 0;
        	//Word	PartsOfSpeech	Synonyms	Usage	Antonyms	RelatedPartsOfSpeech
            for (CSVRecord csvRecord : csvParser) {
                // Accessing values by Header names
                String wordValue = csvRecord.get("Word").trim();
                if (!wordsMap.containsKey(wordValue)) {
	                String partsOfSpeechValue = csvRecord.get("PartsOfSpeech");
	                PartsOfSpeech partsOfSpeech = PartsOfSpeechMapper.parse(partsOfSpeechValue);
	                String synonymValues = csvRecord.get("Synonyms");
	                Set<String> synonyms = Arrays.stream(StringUtils.split(synonymValues, SEMICOLON_SEPARATOR)) 
	                		.map(s -> s.trim())
	                        .collect(Collectors.toSet());
	                String usageVales = csvRecord.get("Usage");
	                Set<String> usages = Arrays.stream(StringUtils.split(usageVales, DOT_SEPARATOR))
	                		.map(u -> u.trim())
	                        .collect(Collectors.toSet());
	                String antonymValues = csvRecord.get("Antonyms");
	                Set<String> antonyms = Arrays.stream(StringUtils.split(antonymValues, SEMICOLON_SEPARATOR)) 
	                		.map(a -> a.trim())
	                        .collect(Collectors.toSet());
	                String relatedPartsOfSpeech = csvRecord.get("RelatedPartsOfSpeech");
	                
	                Word word = new Word (wordValue, partsOfSpeech, sequenceNumber++, synonyms, 
	                		antonyms, usages, relatedPartsOfSpeech, -1);
	                wordsMap.put(wordValue, word); 
                }
            }
        }
		return wordsMap;
    }

}
