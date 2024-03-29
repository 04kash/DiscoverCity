package app;

import interface_adapter.ViewManagerModel;

import interface_adapter.apiReturns.ApiController;
import interface_adapter.apiReturns.ApiPresenter;
import interface_adapter.apiReturns.ApiViewModel;

import interface_adapter.displayingLabels.DisplayingLabelsController;
import interface_adapter.displayingLabels.DisplayingLabelsPresenter;
import interface_adapter.displayingLabels.DisplayingLabelsViewModel;
import interface_adapter.displayingLocations.DisplayingLocationsController;
import interface_adapter.displayingLocations.DisplayingLocationsPresenter;
import interface_adapter.displayingLocations.DisplayingLocationsViewModel;

import use_case.apiReturns.ApiInputBoundary;
import use_case.apiReturns.ApiInteractor;
import use_case.apiReturns.ApiOutputBoundary;
import use_case.apiReturns.ApiUserDataAccessInterface;
import use_case.displayingLabels.DisplayingLabelsInputBoundary;
import use_case.displayingLabels.DisplayingLabelsInteractor;
import use_case.displayingLabels.DisplayingLabelsOutputBoundary;
import use_case.displayingLabels.DisplayingLabelsUserDataAccessInterface;
import use_case.displayingLocations.DisplayingLocationsInputBoundary;
import use_case.displayingLocations.DisplayingLocationsInteractor;
import use_case.displayingLocations.DisplayingLocationsOutputBoundary;
import use_case.displayingLocations.DisplayingLocationsUserDataAccessInterface;
import view.SearchView;

public class SearchUseCaseFactory {

    private SearchUseCaseFactory() {
    }

     /**
     * This method creates a Search view where the users can choose the city of their choice and a filter for which they
     * want to discover locations.
     *
     * @param viewManagerModel this is the view manager model that is needed to create both the controllers
     *                         (displayingLocations and apiReturns)
     * @param displayingLabelsViewModel this is the ViewModel that is specific to the displayingLabels use case and
     *                                  is needed to create the search view as well as the displaying labels controller
     * @param userDataAccessInterface this is the data access interface that is used by the displaying labels use case
     *                                and is required to create that use cases' controller
     * @param apiViewModel this is the ViewModel that is specific to the apiReturns use case and is needed to create the
     *                     search view as well as the apiReturns controller
     * @param userDataAccessInterface2 this is the data access interface that is used by the apiReturns use case and is
     *                                 required to create that use cases' controller
     * @param displayingLocationsViewModel this is the ViewModel that is specific to the displayingLocations use case
     *                                     and is needed to create the search view as well as the displaying locations
     *                                     controller
     * @param displayingLabelsUserDataAccessInterface this is the data access interface that is used by the displaying
     *                                                locations use case and is required to create that use cases'
     *                                                controller
     * @return a Search view
     */
    public static SearchView create(ViewManagerModel viewManagerModel,
                                    ApiViewModel apiViewModel,
                                    DisplayingLocationsViewModel displayingLocationsViewModel,
                                    ApiUserDataAccessInterface userDataAccessInterface,
                                    DisplayingLocationsUserDataAccessInterface userDataAccessInterface2,
                                    DisplayingLabelsViewModel displayingLabelsViewModel,
                                    DisplayingLabelsUserDataAccessInterface displayingLabelsUserDataAccessInterface) {
        ApiController apiController =
                createApi(viewManagerModel, apiViewModel, displayingLocationsViewModel, userDataAccessInterface);
        DisplayingLocationsController displayingLocationsController =
                createDisplayingLocation(viewManagerModel, displayingLocationsViewModel, userDataAccessInterface2);
        DisplayingLabelsController displayingLabelsController =
                createDisplayingLabel(viewManagerModel,displayingLabelsViewModel,displayingLabelsUserDataAccessInterface);

        return new SearchView(apiViewModel, apiController, displayingLocationsController, displayingLabelsController);
    }


    private static ApiController createApi(ViewManagerModel viewManagerModel, ApiViewModel apiViewModel, DisplayingLocationsViewModel displayingLocationsViewModel, ApiUserDataAccessInterface userDataAccessInterface) {
        ApiOutputBoundary apiOutputBoundary = new ApiPresenter(apiViewModel, viewManagerModel, displayingLocationsViewModel);
        ApiInputBoundary apiInputBoundary = new ApiInteractor(userDataAccessInterface, apiOutputBoundary);

        return new ApiController(apiInputBoundary);
    }
;

    private static DisplayingLocationsController createDisplayingLocation(ViewManagerModel viewManagerModel, DisplayingLocationsViewModel displayingLocationsViewModel, DisplayingLocationsUserDataAccessInterface userDataAccessInterface) {
        DisplayingLocationsOutputBoundary displayingLocationsOutputBoundary = new DisplayingLocationsPresenter(displayingLocationsViewModel, viewManagerModel);
        DisplayingLocationsInputBoundary displayingLocationsInputBoundary = new DisplayingLocationsInteractor(userDataAccessInterface, displayingLocationsOutputBoundary);

        return new DisplayingLocationsController(displayingLocationsInputBoundary);

    }

    private static DisplayingLabelsController createDisplayingLabel(ViewManagerModel viewManagerModel,DisplayingLabelsViewModel displayingLabelsViewModel,DisplayingLabelsUserDataAccessInterface displayingLabelsUserDataAccessInterface){
        DisplayingLabelsOutputBoundary displayingLabelsOutputBoundary = new DisplayingLabelsPresenter(displayingLabelsViewModel,viewManagerModel);
        DisplayingLabelsInputBoundary displayingLabelsInputBoundary = new DisplayingLabelsInteractor(displayingLabelsUserDataAccessInterface,displayingLabelsOutputBoundary);
        return new DisplayingLabelsController(displayingLabelsInputBoundary);
    }
}
