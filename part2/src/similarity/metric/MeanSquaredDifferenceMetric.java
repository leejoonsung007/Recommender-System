package similarity.metric;

import java.util.Set;

import profile.Profile;

public class MeanSquaredDifferenceMetric implements SimilarityMetric {

	/**
	 * constructor - creates a new PearsonMetric object
	 */
	public MeanSquaredDifferenceMetric()
	{
	}
	
	/**
	 * computes the similarity between profiles
	 * @param profile 1 
	 * @param profile 2
	 */
	
	public double getSimilarity(final Profile p1, final Profile p2)
	{
		Set<Integer> common = p1.getCommonIds(p2);
		double numerator = 0;
		double denominator = 0;
		for(Integer id: common) {
			double user1_rating = p1.getValue(id);
			double user2_rating = p2.getValue(id);
			numerator += Math.pow((user1_rating-user2_rating),2);
			denominator++;
		}
		double above = (common.size() > 0) ? numerator : 0;
		double below = (common.size() > 0) ? denominator : 0;
		return (below > 0) ? above /below : 0;
	}

}