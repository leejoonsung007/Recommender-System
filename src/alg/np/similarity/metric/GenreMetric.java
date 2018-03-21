/**
 * Compute the similarity between two items based on genres
 */

package alg.np.similarity.metric;

import java.util.Set;

import util.reader.DatasetReader;

public class GenreMetric implements SimilarityMetric
{	
	private DatasetReader reader; // dataset reader
	
	/**
	 * constructor - creates a new GenreMetric object
	 * @param reader - dataset reader
	 */
	public GenreMetric(final DatasetReader reader)
	{
		this.reader = reader;
	}
	
	/**
	 * computes the similarity between items
	 * @param id1 - the id of the first item 
	 * @param id2 - the id of the second item
	 */
	public double getItemSimilarity(final Integer X, final Integer Y)
	{
		// calculate similarity using Jaccard
		
		// get the sets of genres for both items
		Set<String> genresX = reader.getItem(X).getGenres();
		Set<String> genresY = reader.getItem(Y).getGenres();
		
		// calculate the number of common genres
		int count = 0;
		for (String s: genresX)
			if (genresY.contains(s))
				count++;
		
		double Jaccard = (genresX.size() + genresY.size() - count > 0) ? count * 1.0 / (genresX.size() + genresY.size() - count) : 0;

		// return the similarity
		return Jaccard;
	}
}
