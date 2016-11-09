package example;

import org.junit.After;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import hu.rozsa.daniel.learningapplication.ninth.ExampleInteractor;
import hu.rozsa.daniel.learningapplication.ninth.SomeGateway;
import hu.rozsa.daniel.learningapplication.ninth.SomePlugin;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class ExampleInteractorTest {
    @Mock
    private SomePlugin somePlugin = Mockito.mock(SomePlugin.class);
    @Mock
    private SomeGateway someGateway = Mockito.mock(SomeGateway.class);
    @Mock
    private SomePlugin.OnCompleteResult callback = Mockito.mock(SomePlugin.OnCompleteResult.class);

    private ExampleInteractor exampleInteractor = new ExampleInteractor(someGateway, somePlugin);


    @After
    public void after() {
        verifyNoMoreInteractions(someGateway);
        verifyNoMoreInteractions(somePlugin);
        verifyNoMoreInteractions(callback);
    }

    @Test
    public void getDataFromServerAndSaveToDBEmptyString_returnsWithAnError() throws Exception {

        exampleInteractor.getDataFromServerAndSaveToDb("", callback);
        verify(callback).onError("Invalid key");
    }

    @Test
    public void getDataFromServerAndSaveToDBServerError_errorDispatched() throws Exception {

        final String errorMsg = "Error at server call";

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                SomePlugin.OnCompleteResult callback = invocation.getArgument(1);
                callback.onError(errorMsg);
                return null;
            }
        }).when(somePlugin)
          .getDataFromServer(anyString(), any(SomePlugin.OnCompleteResult.class));

        exampleInteractor.getDataFromServerAndSaveToDb("someKey", callback);

        verify(callback).onError(errorMsg);
        verify(somePlugin).getDataFromServer(anyString(), any(SomePlugin.OnCompleteResult.class));

    }

    @Test
    public void getDataFromServerAndSaveToDBDownloadSuccessAndDbSuccess_savedToDbAndOnSuccessDispatched() throws Exception {

        final String resultString = "it's the result";

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                SomePlugin.OnCompleteResult callback = invocation.getArgument(1);
                callback.onSuccess(resultString);
                return null;
            }
        }).when(somePlugin)
          .getDataFromServer(anyString(), any(SomePlugin.OnCompleteResult.class));

        when(someGateway.saveDate(anyString())).thenReturn(true);

        exampleInteractor.getDataFromServerAndSaveToDb("someKey", callback);

        verify(somePlugin).getDataFromServer(anyString(), any(SomePlugin.OnCompleteResult.class));
        verify(callback).onSuccess(resultString);
        verify(someGateway).saveDate(resultString);

    }

    @Test
    public void getDataFromServerAndSaveToDBDownloadSuccessAndDbFails_savedToDbAndOnSuccessDispatched() throws Exception {

        final String resultString = "it's the result";

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                SomePlugin.OnCompleteResult callback = invocation.getArgument(1);
                callback.onSuccess(resultString);
                return null;
            }
        }).when(somePlugin)
          .getDataFromServer(anyString(), any(SomePlugin.OnCompleteResult.class));

        when(someGateway.saveDate(anyString())).thenReturn(false);

        exampleInteractor.getDataFromServerAndSaveToDb("someKey", callback);

        verify(somePlugin).getDataFromServer(anyString(), any(SomePlugin.OnCompleteResult.class));
        verify(callback).onError("Error at saving to DB");
        verify(someGateway).saveDate(resultString);

    }
}
