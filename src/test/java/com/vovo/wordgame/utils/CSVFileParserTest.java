/**
 * 
 */
package com.vovo.wordgame.utils;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author jmukherj
 *
 */
public class CSVFileParserTest {
	
	private static final String TEST_CSV_FILE = "ArrangedGREMostCommonWords.csv";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Test method for {@link com.vovo.wordgame.utils.CSVFileParser#read(java.lang.String)}.
	 * @throws IOException 
	 */
	@Test
	public void testRead() throws IOException {
		CSVFileParser csvFileParser = new CSVFileParser();
		csvFileParser.read(TEST_CSV_FILE);
	}

}
