package com.example.nichetech.expandablelistexample;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.nichetech.expandablelistexample.comman.RestApi;
import com.example.nichetech.expandablelistexample.comman.Utility;
import com.example.nichetech.expandablelistexample.comman.WajabatyApi;
import com.example.nichetech.expandablelistexample.object.Restaurant;
import com.example.nichetech.expandablelistexample.object.req.ReqAdvanceSearch;
import com.example.nichetech.expandablelistexample.object.resp.RespAdvanceSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<? extends Restaurant>> expandableListDetail;

    private ProgressDialog mLoader;
    List<RespAdvanceSearch.WCFGetDetailForAdvancedSearch.Area> areas1;
    List<RespAdvanceSearch.WCFGetDetailForAdvancedSearch.Cuisine> cuisines;
    List<RespAdvanceSearch.WCFGetDetailForAdvancedSearch.SpecialOffer> specialOffers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getAdvanceSearch();

        if(areas1!=null)
        {
            Log.e("SIZE",areas1.size()+"");
        }
        else
        {
            Log.e("SIZE","NULL Object");
        }



        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);


        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });

    }

    public void getAdvanceSearch()
    {

        mLoader = new ProgressDialog(MainActivity.this);
        mLoader.setMessage("Loading....");
        mLoader.setCancelable(false);
        if (!isFinishing()) mLoader.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestApi.WAJA_WS_PREFIX)
                .addConverterFactory(GsonConverterFactory.create())
                .client(Utility.getRetrofitClient())
                .build();

        WajabatyApi wajabatyApi = retrofit.create(WajabatyApi.class);

        ReqAdvanceSearch.ObjWCFGetSearch advancedSearch=new ReqAdvanceSearch.ObjWCFGetSearch(120);
        Call<RespAdvanceSearch> call= wajabatyApi.getAdvance_Search(new ReqAdvanceSearch(advancedSearch));

        call.enqueue(new Callback<RespAdvanceSearch>() {
            @Override
            public void onResponse(Call<RespAdvanceSearch> call, Response<RespAdvanceSearch> response) {

                try
                {
                    int errorCode=response.body().WCFGetDetailForAdvancedSearch.getErrorCode();
                    Log.e("errorCode = ",errorCode+"");
                    List<RespAdvanceSearch.WCFGetDetailForAdvancedSearch.Area>  areas=response.body().WCFGetDetailForAdvancedSearch.getListArea();



                    Iterator<RespAdvanceSearch.WCFGetDetailForAdvancedSearch.Area> i=areas.iterator();

                    while (i.hasNext())
                    {
                        RespAdvanceSearch.WCFGetDetailForAdvancedSearch.Area area=i.next();
                        Log.e("AREA Name",area.getAreaName()+"");
                    }
                    cuisines=response.body().WCFGetDetailForAdvancedSearch.getListCuisine();
                    specialOffers=response.body().WCFGetDetailForAdvancedSearch.getListSpecialOffer();

                    areas1=areas;

                    expandableListDetail = ExpandableListDataPump.getData(areas1,cuisines,specialOffers);

                    expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());

                    expandableListAdapter = new CustomExpandableListAdapter(MainActivity.this, expandableListTitle, expandableListDetail);
                    expandableListView.setAdapter(expandableListAdapter);
                }catch (Exception e)
                {

                }finally {

                    mLoader.dismiss();
                    Toast.makeText(getApplicationContext(),"Task have been finished",Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<RespAdvanceSearch> call, Throwable t) {

                if (mLoader != null && mLoader.isShowing()) mLoader.dismiss();


                Toast.makeText(getApplicationContext(),"Task have been finished",Toast.LENGTH_SHORT).show();

            }
        });


    }

}
