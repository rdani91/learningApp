package hu.rozsa.daniel.learningapplication.ninth;

public class ExampleInteractor {


    private final SomeGateway someGateway;
    private final SomePlugin somePlugin;

    public ExampleInteractor(SomeGateway someGateway, SomePlugin somePlugin) {
        this.someGateway = someGateway;
        this.somePlugin = somePlugin;
    }


    public void getDataFromServerAndSaveToDb(String key, final SomePlugin.OnCompleteResult callback) {
        if (key.isEmpty()) {
            callback.onError("Invalid key");
            return;
        }

        somePlugin.getDataFromServer(key, new SomePlugin.OnCompleteResult() {
            @Override
            public void onSuccess(String result) {
                boolean success = someGateway.saveDate(result);
                if (success) {
                    callback.onSuccess(result);
                } else {
                    callback.onError("Error at saving to DB");
                }
            }

            @Override
            public void onError(String errorMsg) {
                callback.onError(errorMsg);
            }
        });
    }


    public String getDataFromCache() {
        return someGateway.getData();

    }
}
