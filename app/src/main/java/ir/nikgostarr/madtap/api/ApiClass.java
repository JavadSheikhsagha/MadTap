package ir.nikgostarr.madtap.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClass {

    @GET("version.php?i=1")
    Call<String> getVer();
}
