/**
 * Compute the similarity between two items based on the Cosine between item genome scores
 */ 

package alg.np.similarity.metric;

import java.util.Set;

import profile.Profile;
import util.reader.DatasetReader;

public class GenomeMetric implements SimilarityMetric
{
	private DatasetReader reader; // dataset reader
	
	/**
	 * constructor - creates a new GenomeMetric object
	 * @param reader - dataset reader
	 */
	public GenomeMetric(final DatasetReader reader)
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
		// get the GenomeScores vectors
		Profile genomeX = reader.getItem(id1).getGenomeScores();
		Profile genomeY = reader.getItem(id2).getGenomeScores();	
		
		// get the commonID of genomeX and genomeY 
		Set<Integer> commonId = genomeX.getCommonIds(genomeY);
		
		// calculate the numerator of cosine similarity formula
		double sum = 0;
		for(Integer ID: commonId) {
			double valueX = genomeX.getValue(ID);
			double valueY = genomeY.getValue(ID);
			sum += valueX + valueY;
		}
		
		// calculate similarity using Cosine
		if (genomeX.getNorm()*genomeY.getNorm() == 0) 
			return 0;
		else
			return sum / (genomeX.getNorm()*genomeY.getNorm());
	}
}
