package com.vovo.wordgame.model;

import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

public class Word implements Comparable<Word> {
	private final String word;
	
	private final PartsOfSpeech partsOfSpeech;
	
	private final int sequenceNumber;
	
	private Set<String> synonyms;
	
	private Set<String> antonyms;
	
	private final Set<String> usage;
	
	private final String relatedPartsOfSpeech;
	
	/**
	 * priority = -1 => Unspecified
	 * Lower value of priority represents more frequent/popular word.
	 */
	private int priority = -1;
	
	public Word(String word) {
		this.word = word;
		partsOfSpeech = null; 
		sequenceNumber = -1;
		synonyms = null;
		antonyms = null;
		usage = null;
		relatedPartsOfSpeech = null;
	}
	
	public Word(String word,
			PartsOfSpeech partsOfSpeech,
			int sequenceNumber,
			Set<String> synonyms,
			Set<String> antonyms,
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

	public Set<String> getSynonyms() {
		return synonyms;
	}
	
	public void setSynonyms(Set<String> synonyms) {
		this.synonyms = synonyms;
	}

	public void addSynonyms(Set<String> synonyms) {
		if (CollectionUtils.isEmpty(this.synonyms)) {
			this.synonyms = synonyms;
		} else if (CollectionUtils.isNotEmpty(synonyms)) {
			this.synonyms.addAll(synonyms);
		}
	}

	public Set<String> getAntonyms() {
		return antonyms;
	}

	public void addAntonyms(Set<String> antonyms) {
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
	public int compareTo(Word o) {
		return this.getWord().compareTo(o.getWord());
	}
	
}
