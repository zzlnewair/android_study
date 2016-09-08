package zzl.retrofitdemo;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * PhoneService
 *
 * http://apistore.baidu.com/apiworks/servicedetail/709.html
 */
public interface PhoneService {

   // @GET("showapi_open_bus/mobile/find")
  //  Call<PhoneResult> getResult(@Header("apikey") String apikey, @Query("num") String phone);
    @GET("showapi_open_bus/mobile/find")
    Observable <PhoneResult> getResult(@Header("apikey") String apikey, @Query("num") String phone);
}