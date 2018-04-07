/**
 * A class that implements the "ThresholdNeighbourhood" technique (user-based CF)
 * 
 * Michael O'Mahony
 * 20/01/2011
 */

package alg.ub.neighbourhood;

import similarity.SimilarityMap;
import util.ScoredThingDsc;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Map;

import profile.Profile;

public class ThresholdNeighbourhood extends Neighbourhood 
{
	private double threshold = Integer.MAX_VALUE ; // restric neighbourhood to the highest value neighbours
	
	/**
	 * constructor - creates a new NearestNeighbourhood object
	 * @param k - the number of neighbours in the neighbourhood
	 */
	public ThresholdNeighbourhood(final int threshold)
	{
		super();
		this.threshold = threshold;
	}
	
	/**
	 * stores the neighbourhoods for all users in member Neighbour.neighbourhoodMap
	 * @param simMap - a map containing user-user similarities
	 */
	public void computeNeighbourhoods(final SimilarityMap simMap)
	{
		for (Integer userId: simMap.getIds()) { // iterate over all users
			SortedSet<ScoredThingDsc> ss = new TreeSet<ScoredThingDsc>(); // for the current user, store all similarities in order of descending similarity in a sorted set

			Profile profile = simMap.getSimilarities(userId); // get the user similarity profile
			if (profile != null) {
				for (Integer id: profile.getIds()) { // iterate over each user in the profile
					double sim = profile.getValue(id);
					if (sim > threshold) //users whose similarities with the active user exceed a threshold are selected as neighbourhood
						ss.add(new ScoredThingDsc(sim, id));
				}
			}
			
			// get the neighbours
			Iterator<ScoredThingDsc> iter = ss.iterator();
			while(iter.hasNext()) {
				ScoredThingDsc st = iter.next();
				Integer id = (Integer)st.thing;
				this.add(userId, id);
			}
		}	
	}
}
