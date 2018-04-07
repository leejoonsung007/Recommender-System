/**
 * Compute the similarity between two items based on increase in confidence
 */ 

package alg.np.similarity.metric;

import java.util.Map;
import java.util.Set;

import profile.Profile;
import util.reader.DatasetReader;

public class IncConfidenceMetric implements SimilarityMetric
{
	private static double RATING_THRESHOLD = 4.0; // the threshold rating for liked items 
	private DatasetReader reader; // dataset reader
	
	/**
	 * constructor - creates a new IncConfidenceMetric object
	 * @param reader - dataset reader
	 */
	public IncConfidenceMetric(final DatasetReader reader)
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
		double supportX = 0;
		double supportXY = 0;
		double supportNotX= 0;
		double supportNotXY = 0;
		
		// get the User profile
		Map<Integer, Profile> UserProfiles = reader.getUserProfiles();
		
		//get the key set of user profile - userId
		Set<Integer> user = UserProfiles.keySet();
				
		for (Integer userId : user) {
			if (UserProfiles.get(userId).contains(X) && UserProfiles.get(userId).getValue(X) >= 4.0) {
				supportX++;
				if (UserProfiles.get(userId).contains(Y) && UserProfiles.get(userId).getValue(Y) >= 4.0) supportXY++;
			} 
			if (UserProfiles.get(userId).contains(X) && UserProfiles.get(userId).getValue(X) < 4.0) {
				supportNotX++;
				if (UserProfiles.get(userId).contains(Y) && UserProfiles.get(userId).getValue(Y) >= 4.0) supportNotXY++; 
			}
		}
		
		// calculate similarity using conf(X => Y) / conf(!X => Y)
		if ((supportNotXY/supportNotX) == 0)
			return 0;
		else
			return (supportXY/supportX)/(supportNotXY/supportNotX);
	}
}
