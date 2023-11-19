package use_case.api_returns;

import entity.Location;

import java.util.ArrayList;

public class ApiOutputData {
    public final ArrayList<Location> locations;
    private boolean useCaseFailed;
    public ApiOutputData(ArrayList<Location> locations, boolean useCaseFailed) {
        this.locations = locations;
        this.useCaseFailed = useCaseFailed;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }
}
