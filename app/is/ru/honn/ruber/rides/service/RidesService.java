package is.ru.honn.ruber.rides.service;

import is.ru.honn.ruber.domain.Trip;
import java.util.List;

/**
 *
 */
public interface RidesService
{
  public void addTrip(int userId, Trip trip) throws TripException;
  public List<Trip> getTrip(int userId) throws TripException;

}
