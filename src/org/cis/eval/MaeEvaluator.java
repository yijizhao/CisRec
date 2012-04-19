package org.cis.eval;

import org.cis.cf.algorithm.RatingPredictor;
import org.cis.data.Ratings;

public class MaeEvaluator implements Evaluator {

	@Override
	public double evaluate(RatingPredictor rp, Ratings ratings) {
		int count = ratings.getCount();
		
		double mae = 0;
		int index, user_id, item_id, rating;
		double rating_hat, err;
		for( index = 0; index != count; ++index){
			user_id = ratings.getUser(index);
			item_id = ratings.getItem(index);
			rating  = ratings.getRating(index);
			
			rating_hat = rp.predict(user_id, item_id, true);
			
			err = rating_hat - rating;
			mae += Math.abs(err);
		}
		
		mae /= count;
		return mae;
	}

}