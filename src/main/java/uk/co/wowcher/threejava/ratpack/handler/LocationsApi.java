package uk.co.wowcher.threejava.ratpack.handler;

import ratpack.handling.Context;
import ratpack.handling.Handler;

public class LocationsApi extends Handler {
    @Override
    public void handle(Context context) throws Exception {
        final String location = context.getPathTokens().get("location");



        context.getResponse().send();
    }
}
