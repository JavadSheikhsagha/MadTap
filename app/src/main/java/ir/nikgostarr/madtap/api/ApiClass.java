package ir.nikgostarr.madtap.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiClass {

    @GET("updater.php")
    Call<List<VersionModel>> version(@Query("title")String appTitle);
}
