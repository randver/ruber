package is.ru.honn.ruber.rides;


import is.ru.honn.ruber.domain.Trip;

import java.util.ArrayList;
import java.util.List;

public class RidesServiceStub implements RidesService
{
  List<Trip> theTrips = new ArrayList<Trip>();
  private RidesDataGateway ridesDataGateway;

    public RidesServiceStub(){

    }
  public RidesServiceStub(RidesDataGateway ridesDataGateway)
  {
    this.ridesDataGateway = ridesDataGateway;
  }

  @Override
  public void addTrip(int userId, Trip trip)
  {
    theTrips.add(trip);
    int id = ridesDataGateway.addTrip(trip);
    trip.setId(id);

  }

  @Override
  public List<Trip> getTrip(int userId)
  {
    return theTrips;
  }

}

