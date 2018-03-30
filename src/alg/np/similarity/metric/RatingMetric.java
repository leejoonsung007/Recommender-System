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
		// get the profile of id1 and id2
		Profile itemProfile1 = reader.getItemProfiles().get(id1);
		Profile itemProfile2 = reader.getItemProfiles().get(id2);
		
		// get the commonID of genomeX and genomeY 
		Set<Integer> common = itemProfile1.getCommonIds(itemProfile2);
		
		// calculate the numerator of cosine similarity formula
		double sum = 0;
		for(Integer ID: common) {
			double valueX = itemProfile1.getValue(ID);
			double valueY = itemProfile2.getValue(ID);
			 sum += valueX + valueY;
		}		
		
		// calculate similarity using Cosine
		if (itemProfile1.getNorm()*itemProfile2.getNorm() == 0)
			return 0;
		else
			return sum/itemProfile1.getNorm()*itemProfile2.getNorm();
	}
}
