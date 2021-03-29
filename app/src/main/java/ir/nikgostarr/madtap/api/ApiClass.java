package ir.nikgostarr.madtap.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClass {

    @GET("click_fast/version.php")
    Call<VersionModel> getVer();
}
