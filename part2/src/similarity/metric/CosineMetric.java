package similarity.metric;

import java.util.Set;

import profile.Profile;

public class CosineMetric implements SimilarityMetric{
	/**
	 * constructor - creates a new PearsonMetric object
	 */
	public CosineMetric(){
	}
	
	/**
	 * computes the similarity between profiles
	 * @param profile 1 
	 * @param profile 2
	 */
	public double getSimilarity(final Profile p1, final Profile p2)
	{	
		Set<Integer> common = p1.getCommonIds(p2);
		double denominator =  p1.getNorm()*p2.getNorm();
		double numerator = 0;
		for(Integer id: common) {
			double user1_rating = p1.getValue(id).doubleValue();
			double user2_rating = p2.getValue(id).doubleValue();
			numerator += user1_rating * user2_rating;
		} 
		double above = (common.size() > 0) ? numerator : 0;
		double below = (common.size() > 0) ? denominator : 0;
		return (below > 0) ? above/below : 0;
	}

}

