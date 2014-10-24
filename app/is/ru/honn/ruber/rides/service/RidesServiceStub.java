package is.ru.honn.ruber.rides.service;


import is.ru.honn.ruber.domain.Trip;
import is.ru.honn.ruber.rides.data.RidesDataGateway;
import is.ru.honn.ruber.rides.service.RidesService;

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
        //theTrips.add(trip);
        ridesDataGateway.addTrip(trip);
  }

  @Override
  public List<Trip> getTrip(int userId)
  {
    return theTrips;
  }

}

