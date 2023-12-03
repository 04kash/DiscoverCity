package interactors;

import data_access.JsonDataAccessObject;
import entity.CommonUserFactory;
import org.junit.Test;
import use_case.login.*;

import java.io.IOException;

import static junit.framework.TestCase.*;

public class LoginInteractorTest {
    @Test
    public void successTest() {
        JsonDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new JsonDataAccessObject("./usersTest.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LoginInputData inputData = new LoginInputData("akshaya", "password");
        LoginUserDataAccessInterface userRepository = userDataAccessObject;
        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertTrue(userRepository.existsByName("akshaya"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected");
            }
        };
        LoginInputBoundary interactor = new LoginInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    public void failTest() {
        JsonDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new JsonDataAccessObject("./usersTest3.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LoginInputData inputData = new LoginInputData("akshaya", "password");
        LoginUserDataAccessInterface userRepository = userDataAccessObject;
        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals( "akshaya: Account does not exist.", error);
            }
        };
        LoginInputBoundary interactor = new LoginInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }
}
