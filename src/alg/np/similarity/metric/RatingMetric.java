/**
 * Compute the similarity between two items based on the Cosine between item ratings
 */ 

package alg.np.similarity.metric;

import java.util.Set;

import profile.Profile;
import util.reader.DatasetReader;

public class RatingMetric implements SimilarityMetric
{
	private DatasetReader reader; // dataset reader
	
	/**
	 * constructor - creates a new RatingMetric object
	 * @param reader - dataset reader
	 */
	public RatingMetric(final DatasetReader reader)
	{
		this.reader = reader;
	}
	
	/**
	 * computes the similarity between items
	 * @param id1 - the id of the first item 
	 * @param id2 - the id of the second item
	 */
	public double getItemSimilarity(final Integer id1, final Integer id2)
	{
		// calculate similarity using Cosine
		
		return 0;
	}
}