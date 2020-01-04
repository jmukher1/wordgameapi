package com.vovo.wordgame.model;

import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

public class PopulatedWord implements Comparable<PopulatedWord> {
	private final String word;
	
	private final PartsOfSpeech partsOfSpeech;
	
	private final int sequenceNumber;
	
	private Set<Word> synonyms;
	
	private Set<Word> antonyms;
	
	private final Set<String> usage;
	
	private final String relatedPartsOfSpeech;
	
	/**
	 * priority = -1 => Unspecified
	 * Lower value of priority represents more frequent/popular word.
	 */
	private int priority = -1;
	
	public PopulatedWord(String word) {
		this.word = word;
		partsOfSpeech = null; 
		sequenceNumber = -1;
		synonyms = null;
		antonyms = null;
		usage = null;
		relatedPartsOfSpeech = null;
	}
	
	public PopulatedWord(String word,
			PartsOfSpeech partsOfSpeech,
			int sequenceNumber,
			Set<Word> synonyms,
			Set<Word> antonyms,
			Set<String> usage,
			String relatedPartsOfSpeech,
			int priority) {
		this.word = word;
		this.partsOfSpeech = partsOfSpeech;
		this.sequenceNumber = sequenceNumber;
		this.synonyms = synonyms;
		this.antonyms = antonyms;
		this.usage = usage;
		this.relatedPartsOfSpeech = relatedPartsOfSpeech;
		this.priority = priority;
	}

	public Set<Word> getSynonyms() {
		return synonyms;
	}
	
	public void setSynonyms(Set<Word> synonyms) {
		this.synonyms = synonyms;
	}

	public void addSynonyms(Set<Word> synonyms) {
		if (CollectionUtils.isEmpty(this.synonyms)) {
			this.synonyms = synonyms;
		} else if (CollectionUtils.isNotEmpty(synonyms)) {
			this.synonyms.addAll(synonyms);
		}
	}

	public Set<Word> getAntonyms() {
		return antonyms;
	}

	public void addAntonyms(Set<Word> antonyms) {
		if (CollectionUtils.isEmpty(this.antonyms)) {
			this.antonyms = antonyms;
		} else if (CollectionUtils.isNotEmpty(antonyms)) {
			this.antonyms.addAll(antonyms);
		}
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getWord() {
		return word;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public Set<String> getUsage() {
		return usage;
	}

	public PartsOfSpeech getPartsOfSpeech() {
		return partsOfSpeech;
	}

	public String getRelatedPartsOfSpeech() {
		return relatedPartsOfSpeech;
	}

	@Override
	public int compareTo(PopulatedWord o) {
		return this.getWord().compareTo(o.getWord());
	}
}
