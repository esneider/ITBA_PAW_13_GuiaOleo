UPDATE foodtypes SET ammount = (SELECT COUNT(*) FROM restaurants WHERE restaurants.foodTypeId = foodtypes.id);
UPDATE restaurants SET avgscore = (SELECT COALESCE(AVG(score), 0) FROM ratings WHERE ratings.restaurantId = restaurants.id);
UPDATE restaurants SET cantRatings = (SELECT COUNT(*) FROM ratings WHERE ratings.restaurantId = restaurants.id);