package com.vovo.wordgame.api;

import java.util.Date;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;

import com.vovo.wordgame.model.PopulatedWord;
import com.vovo.wordgame.utils.WordPopulator;
 
@Path("/api")
public class WordGameResource
{
	@GET
	@Path("ping")
	@Produces("text/html")
	public Response getStartingPage()
	{
		String output = "<h1>Hello World!<h1>" +
				"<p>RESTful Service is running ... <br>Ping @ " + new Date().toString() + "</p<br>";
		return Response.status(200).entity(output).build();
	}
	
	
	@GET
    @Produces({MediaType.APPLICATION_JSON})  
	public List<PopulatedWord> getAllPopulatedWords() throws Exception{
        
        List<PopulatedWord> populatedWords = WordPopulator.getInstance().getAllPopulatedWords();
        
        System.out.println("getAllPopulatedWords(): found "+ populatedWords.size()+" word(s) on DB");
        
        //return populatedWords.stream().map(populatedWord -> populatedWord.getWord())
          //      .collect(Collectors.toList());
        
        return populatedWords;
	}

	
	@GET
	@Path("/{word}")
    @Produces({MediaType.APPLICATION_JSON})  
	public PopulatedWord getPopulatedWord(@PathParam("word") String word) throws Exception{
        
        PopulatedWord populatedWord = WordPopulator.getInstance().getPopulatedWord(word);
        
        System.out.println("getPopulatedWord(): found for word "+ word +" : synonyms " + StringUtils.join(populatedWord.getSynonyms()));
        
        return populatedWord;
	}
}
