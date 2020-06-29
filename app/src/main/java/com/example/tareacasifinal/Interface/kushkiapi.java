package com.example.tareacasifinal.Interface;
import com.example.tareacasifinal.Bancos.transfersubscriptionbanklist;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
public interface kushkiapi{
  @GET("bankList")
  Call<List<transfersubscriptionbanklist>> gettransfersubscriptionbanklist(@Header("Public-Merchant-Id")String id);
}