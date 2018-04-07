/**
 * An interface to compute the similarity between two items
 */

package alg.np.similarity.metric;

public interface SimilarityMetric 
{
	/**
	 * computes the similarity between items
	 * @param id1 - the id of the first item 
	 * @param id2 - the id of the second item
	 */
	public double getItemSimilarity(final Integer id1, final Integer id2);
}
