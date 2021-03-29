package ir.nikgostarr.madtap.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClass {

    @GET("version.php")
    Call<List<VersionModel>> version();
}
